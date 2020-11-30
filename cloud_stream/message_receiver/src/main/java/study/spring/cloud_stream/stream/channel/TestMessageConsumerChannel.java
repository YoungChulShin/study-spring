package study.spring.cloud_stream.stream.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface TestMessageConsumerChannel {

    String CHANNEL = "testEventChannel";

    @Input(CHANNEL)
    SubscribableChannel testEventChannel();
}
