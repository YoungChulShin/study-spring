package study.spring.caller.infra;

import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class HelloApiClientCircuitWrapper implements HelloApiClient {

  private static final int MAX_FAIL_COUNT = 3;
  private static final long RETRY_SECONDS = 20;

  private final HelloApiClient actualApiClient;
  private Status circuitStatus = Status.CLOSED;
  private Instant halfOpenTime;
  private int failCount;

  public enum Status {
    CLOSED, OPEN
  }

  public HelloApiClientCircuitWrapper() {
    this.actualApiClient = new HelloApiClientImpl();
  }

  @Override
  public String hello(String name) {
    if (circuitStatus == Status.CLOSED) {
      return executeHelloForClosedStatus(name);
    } else {  // OPEN
      return executeHelloForOpenStatus(name);
    }
  }

  private String executeHelloForClosedStatus(String name) {
    try {
      String result = actualApiClient.hello(name);
      changeStatusToClosed();
      return result;
    } catch (Exception ex) {
      failCount++;
      if (failCount == MAX_FAIL_COUNT) {
        changeStatusToOpen();
        return getCircuitBreakerFailMessage();
      }

      return ex.getMessage();
    }
  }

  private String executeHelloForOpenStatus(String name) {
    if (Instant.now().isAfter(halfOpenTime)) {
      try {
        String result = actualApiClient.hello(name);
        changeStatusToClosed();
        return result;
      } catch (Exception ex) {
        changeStatusToOpen();
        return getCircuitBreakerFailMessage();
      }
    } else {
      return getCircuitBreakerFailMessage();
    }
  }

  private void changeStatusToClosed() {
    failCount = 0;
    halfOpenTime = null;
    circuitStatus = Status.CLOSED;
  }

  private void changeStatusToOpen() {
    halfOpenTime = Instant.now().plusSeconds(RETRY_SECONDS);
    circuitStatus = Status.OPEN;
  }

  private String getCircuitBreakerFailMessage() {
    return "circuit breaker fail";
  }
}
