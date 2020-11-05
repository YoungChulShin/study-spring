package study.spring.request_reponse_log.support.logging;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpResponseLog {

    private HttpStatus status;
    private HttpHeaders headers;
    private JsonNode body;

    public static HttpResponseLog createFrom(HttpServletResponse response) {
        HttpStatus status = HttpStatus.valueOf(response.getStatus());
        HttpHeaders headers = getResponseHeaders(response);

    }

    private static HttpHeaders getResponseHeaders(HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        for (String headerName : response.getHeaderNames()) {
            headers.add(headerName, response.getHeader(headerName));
        }

        return headers;
    }

    private static JsonNode getResponseBody(HttpServletResponse response) {
        JsonNode body = null;

        try {
            String json = IOUtils.toString(response.get, response.getCharacterEncoding());
        } catch (IOException e) {

        }


        return body;
    }
}
