package study.spring.request_reponse_log.support.logging;

import lombok.Getter;

@Getter
public enum LoggerType {

    HTTP_AUDIT_TRANSFER("com.study.api");

    private String path;

    LoggerType(String path) {
        this.path = path;
    }
}
