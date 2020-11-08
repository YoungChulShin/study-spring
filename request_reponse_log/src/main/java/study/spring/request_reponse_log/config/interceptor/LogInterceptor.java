package study.spring.request_reponse_log.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {

    private final ObjectMapper objectMapper;
    private final LogProperties logProperties;

    public LogInterceptor(ObjectMapper objectMapper, LogProperties logProperties) {
        this.objectMapper = objectMapper;
        this.logProperties = logProperties;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("request log : " + request);
        log.info("response log : " + response);
    }
}
