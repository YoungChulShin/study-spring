package study.spring.cloud_stream.config;

import org.springframework.cloud.stream.annotation.EnableBinding;
import study.spring.cloud_stream.service.stream.ProducerChannel;

@EnableBinding(value = ProducerChannel.class)
public class MessagingConfiguration {
}
