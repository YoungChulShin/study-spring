package study.webflux.webfluxtest4.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.webflux.webfluxtest4.domain.User;
import study.webflux.webfluxtest4.domain.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public Flux<User> findAll() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user).subscribe();
    }

    public Mono<User> updateUser(User user) {
        return userRepository.findById(user.getId())
                .switchIfEmpty(Mono.error(new Exception("User Not Found")))
                .map(oldUser -> {
                    oldUser.update(user);
                    return oldUser;
                })
                .flatMap(userRepository::save);
    }

    public Mono<Void> deleteUser(Long id) {
        return userRepository.deleteById(id)
                .switchIfEmpty(Mono.error(new Exception("User Not Found")));
    }
}
