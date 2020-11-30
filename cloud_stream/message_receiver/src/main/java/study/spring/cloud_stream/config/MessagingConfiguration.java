package study.spring.cloud_stream.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import study.spring.cloud_stream.stream.channel.TestMessageConsumerChannel;

@EnableBinding(value = TestMessageConsumerChannel.class)
public class MessagingConfiguration {
}
