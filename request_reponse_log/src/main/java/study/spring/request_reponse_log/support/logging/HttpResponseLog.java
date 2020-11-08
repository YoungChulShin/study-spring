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
import org.springframework.http.HttpStatus;
import study.spring.request_reponse_log.support.servlet.BufferedResponseWrapper;

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
        JsonNode body = getResponseBody(response);

        return new HttpResponseLog(status, headers, body);
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
<<<<<<< HEAD
            // HttpServeletResponse로 InputStream을 만들 수 없으니까
            //
            String json = IOUtils.toString(response.get, response.getCharacterEncoding());
        } catch (IOException e) {
=======
            String json = IOUtils.toString(
                    ((BufferedResponseWrapper)response).getContentInputStream(),
                    response.getCharacterEncoding());
>>>>>>> c8c159fca40b0a195d69d293361558b837c217fe

            if (StringUtils.isNotEmpty(json)) {
                body = (new ObjectMapper()).readTree(json);
            }
        } catch (IOException e) {
            // do nothing
        }
        return body;
    }
}
