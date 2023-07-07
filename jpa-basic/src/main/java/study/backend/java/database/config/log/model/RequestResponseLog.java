package study.backend.java.database.config.log.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import lombok.Getter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

@Getter
public class RequestResponseLog {

  private HttpMethod method;
  private String url;
  private HttpStatus status;
  private String queryParams;
  private Long elapsedTime;
  private final RequestLog request;
  private final ResponseLog response;

  public RequestResponseLog(
      HttpServletRequest request,
      HttpServletResponse response,
      ObjectMapper objectMapper) throws IOException {
    this.method = HttpMethod.valueOf(request.getMethod());
    this.url = request.getRequestURI();
    this.queryParams = request.getQueryString();
    if (StringUtils.isEmpty(this.queryParams)) {
      this.queryParams = "{}";
    }
    this.status = HttpStatus.valueOf(response.getStatus());
    try {
      this.elapsedTime = System.currentTimeMillis() - (long)request.getAttribute("requestTime");
    } catch (Exception ex) {
      this.elapsedTime = 0L;
    }
    this.request = new RequestLog(request, objectMapper);
    this.response = new ResponseLog(response, objectMapper);
  }

  @Getter
  private static class RequestLog {

    @JsonProperty("headers")
    private HttpHeaders header;

    @JsonProperty("remoteAddress")
    private String remoteAddress;

    @JsonIgnore
    private String body;

    @JsonProperty("body")
    private JsonNode bodyNode;

    public RequestLog(HttpServletRequest request, ObjectMapper objectMapper) throws IOException {
      this.header = getRequestHeaders(request);
      this.remoteAddress = request.getRemoteAddr();
      this.body = IOUtils.toString(request.getReader());
      if (StringUtils.isEmpty(this.body)) {
        this.body = "{}";
      }
      this.bodyNode = objectMapper.readTree(this.body);
    }

    private static HttpHeaders getRequestHeaders(HttpServletRequest request) {
      HttpHeaders requestHeaders = new HttpHeaders();
      Enumeration<String> headerNames = request.getHeaderNames();

      while (headerNames.hasMoreElements()) {
        String headerName = headerNames.nextElement();
        requestHeaders.add(headerName, request.getHeader(headerName));
      }
      return requestHeaders;
    }
  }

  @Getter
  private static class ResponseLog {

    @JsonProperty("headers")
    private HttpHeaders headers;

    @JsonIgnore
    private String body;

    @JsonProperty("body")
    private JsonNode bodyNode;

    private ResponseLog(HttpServletResponse response, ObjectMapper objectMapper)
        throws IOException {
      this.headers = getResponseHeaders(response);
      if (response instanceof BufferedResponseWrapper) {
        body = IOUtils.toString(
            ((BufferedResponseWrapper) response).getContentInputStream(),
            response.getCharacterEncoding());
      } else {
        body = "{}";
      }
      this.bodyNode = objectMapper.readTree(this.body);
    }

    private static HttpHeaders getResponseHeaders(HttpServletResponse response) {
      HttpHeaders responseHeaders = new HttpHeaders();
      for (String headerName : response.getHeaderNames()) {
        responseHeaders.add(headerName, response.getHeader(headerName));
      }
      return responseHeaders;
    }
  }
}
