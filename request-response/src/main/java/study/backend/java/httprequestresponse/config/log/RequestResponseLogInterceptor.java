package study.backend.java.httprequestresponse.config.log;

import static net.logstash.logback.argument.StructuredArguments.kv;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import study.backend.java.httprequestresponse.config.log.model.RequestResponseLog;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestResponseLogInterceptor implements HandlerInterceptor {

  private final Logger logger = LoggerFactory.getLogger(RequestResponseLogInterceptor.class);
  private final RequestResponseLogProperties logProperties;
  private final ObjectMapper objectMapper;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    if (this.logProperties.isExcludeMethods(request.getMethod())) {
      return true;
    }

    request.setAttribute("requestTime", System.currentTimeMillis());
    return true;
  }

  @Override
  public void afterCompletion(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      Exception ex) throws Exception {
    if (this.logProperties.isExcludeMethods(request.getMethod()) ||
        this.logProperties.isExcludeStatus(response.getStatus())) {
      return;

    }
    writeRequestResponseLog(request, response);
  }

  private void writeRequestResponseLog(HttpServletRequest request, HttpServletResponse response) {
    try {
      RequestResponseLog log = new RequestResponseLog(request, response, objectMapper);
      logger.info("Http request-response",
          kv("method", log.getMethod()),
          kv("url", log.getUrl()),
          kv("status", log.getStatus()),
          kv("queryParams", log.getQueryParams()),
          kv("elapsedTime", log.getElapsedTime()),
          kv("request", log.getRequest()),
          kv("response", log.getResponse()));
    } catch (Exception ignored) { }
  }
}
