package study.spring.cloud_stream.service.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannel {

    //String CHANNEL = "messageChannel";

    @Output
    MessageChannel messageChannel();
}
