package study.spring.request_reponse_log.support.logging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpRequestLog {

    private String url;
    private HttpMethod method;
    private String remoteAddress;
    private HttpHeaders headers;
    private JsonNode body;

    public static HttpRequestLog createFrom(HttpServletRequest request, Set<String> excludeHeaders) {
        String url = request.getRequestURL().toString();
        HttpMethod method = HttpMethod.valueOf(request.getMethod());
        String remoteAddress = request.getRemoteAddr();
        HttpHeaders headers = getRequestHeaders(request, excludeHeaders);
        JsonNode body = getRequestBody(request);

        return new HttpRequestLog(url, method, remoteAddress, headers, body);
    }

    private static HttpHeaders getRequestHeaders(HttpServletRequest request, Set<String> excludeHeaders) {
        HttpHeaders requestHeaders = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            if (excludeHeaders != null && excludeHeaders.contains(headerName.toLowerCase())) {
                continue;
            }
            requestHeaders.add(headerName, request.getHeader(headerName));
        }

        return requestHeaders;
    }

    private static JsonNode getRequestBody(HttpServletRequest request) {
        JsonNode body = null;
        try {
            String json = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            if (StringUtils.isNotEmpty(json)) {
                body = (new ObjectMapper()).readTree(json);
            }
        } catch (IOException e) {
            // do nothing
        }

        return body;
    }
}
