package study.spring.cloud_stream.stream.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import study.spring.cloud_stream.stream.channel.TestMessageConsumerChannel;

@Component
@Slf4j
public class TestEventConsumer {

    @StreamListener(value = TestMessageConsumerChannel.CHANNEL)
    public void onEvent(String message) {

      log.info("Message received: {}", message);
    }
}
