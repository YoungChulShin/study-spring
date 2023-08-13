package study.spring.caller.application;

public class CommonInfo {

  private CommonInfo() { }

  public static class CalleeService {

    public static final String HOST = "http://localhost:8080";
    public static final String URL_SYSTEM_INFO = "/api/v1/system";
  }
}
