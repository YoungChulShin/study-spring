package study.spring.cloud_stream.service.stream;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import study.spring.cloud_stream.config.Constants;
import study.spring.cloud_stream.model.LogMessage;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MessagePublisher {

    private final MessageChannel messageChannel;

    public boolean publishMessage(LogMessage logMessage) {

        Message<String> message = MessageBuilder
                .withPayload(logMessage.getMessage())
                .setHeader("uuid", UUID.randomUUID().toString())
                .build();

        return messageChannel.send(message, Constants.MessagePolicy.DEFAULT_TIMEOUT);
    }
}
