package study.spring.cloud_stream.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import study.spring.cloud_stream.model.LogMessage;
import study.spring.cloud_stream.service.stream.MessagePublisher;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessagePublisher messagePublisher;

    @PostMapping("/message")
    public ResponseEntity sendMessage(@RequestBody String message) {

        boolean result = messagePublisher.publishMessage(new LogMessage(message));

        return ResponseEntity.status(200).body(result ? "success" : "fail");
    }
}
