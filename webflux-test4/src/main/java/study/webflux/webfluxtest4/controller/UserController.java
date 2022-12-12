package study.webflux.webfluxtest4.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.webflux.webfluxtest4.domain.User;
import study.webflux.webfluxtest4.service.UserService;

import java.time.Duration;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/users", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<User> findAllUsers() {
        return userService.getUsers()
                .delayElements(Duration.ofSeconds(1L));
    }

    @GetMapping("/users/{id}")
    public Mono<User> findUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping("/users")
    public Mono<User> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}
