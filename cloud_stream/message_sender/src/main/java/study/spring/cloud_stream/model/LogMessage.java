package study.spring.cloud_stream.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LogMessage implements Serializable {

    private static final long serialVersionUID = -5857383701708275796L;

    private String message;

    public LogMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
