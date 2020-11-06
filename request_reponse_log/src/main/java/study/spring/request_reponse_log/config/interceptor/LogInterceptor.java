package study.spring.request_reponse_log.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import study.spring.request_reponse_log.support.logging.HttpRequestLog;
import study.spring.request_reponse_log.support.logging.HttpResponseLog;
import study.spring.request_reponse_log.support.logging.LoggerType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    private Logger httpLogger = LoggerFactory.getLogger(LoggerType.HTTP_AUDIT_TRANSFER.getPath());

    private final ObjectMapper objectMapper;
    private final LogProperties logProperties;

    public LogInterceptor(ObjectMapper objectMapper, LogProperties logProperties) {
        this.objectMapper = objectMapper;
        this.logProperties = logProperties;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String method = request.getMethod();
//        if (logProperties.isExcludeMethod(method)) {
//            return;
//        }

        HttpRequestLog requestLog = HttpRequestLog.createFrom(request, new HashSet<>());
        HttpResponseLog responseLog = HttpResponseLog.createFrom(response);
        writeRequestResponseLog(requestLog, responseLog);
    }

    private void writeRequestResponseLog(HttpRequestLog requestLog, HttpResponseLog responseLog) {
        String logTemplate = "Request-Response Log";
//        if (httpLogger.isDebugEnabled()) {
//            logTemplate = "Request-Response log {} {}";
//        }

        try {
            httpLogger.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestLog));
            httpLogger.info(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseLog));
        } catch (Exception e) {

        }
    }
}
