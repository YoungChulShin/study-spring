package study.spring.request_reponse_log.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/v1/hello/{name}")
    public ResponseEntity hello(@PathVariable("name") String name) {

        return ResponseEntity.ok("hi" + name);
    }
}
