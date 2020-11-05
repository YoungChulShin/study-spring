package study.spring.request_reponse_log.config.interceptor;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import study.spring.request_reponse_log.support.logging.LoggerType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor extends HandlerInterceptorAdapter {

    private Logger httpLogger = LoggerFactory.getLogger(LoggerType.HTTP_AUDIT_TRANSFER.getPath());

    private final LogProperties logProperties;

    public LogInterceptor(LogProperties logProperties) {
        this.logProperties = logProperties;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String method = request.getMethod();
        if (logProperties.isExcludeMethod(method)) {
            return;
        }

        httpLogger.info(request.toString());
        httpLogger.info(response.toString());
    }
}
