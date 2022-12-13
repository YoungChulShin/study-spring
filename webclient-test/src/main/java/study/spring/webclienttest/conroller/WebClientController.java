package study.spring.webclienttest.conroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.spring.webclienttest.dto.UserInfo;
import study.spring.webclienttest.service.WebClientService;

import java.time.Duration;

@RestController
@RequestMapping("/web")
public class WebClientController {

    private final WebClientService webClientService;

    public WebClientController(WebClientService webClientService) {
        this.webClientService = webClientService;
    }

    @GetMapping(value = "/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<UserInfo> findAllUsers() {
        return webClientService.findUsers().log();
    }

    @GetMapping("/users/{id}")
    public Mono<UserInfo> findUser(@PathVariable Long id) {
        return webClientService.findUser(id).log();
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserInfo user) {
        webClientService.saveUser(user);
    }

    @PutMapping("/users")
    public Mono<UserInfo> updateUser(@RequestBody UserInfo user) {
        return webClientService.updateUser(user);
    }

    @DeleteMapping("/users/{id}")
    public Mono<Void> deleteUser(@PathVariable Long id) {
        return webClientService.deleteUser(id);
    }
}
