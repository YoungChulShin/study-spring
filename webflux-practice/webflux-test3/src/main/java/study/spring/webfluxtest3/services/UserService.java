package study.spring.webfluxtest3.services;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.spring.webfluxtest3.domain.UserRepository;
import study.spring.webfluxtest3.domain.Users;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Flux<Users> getUsers() {
        return userRepository.findAll();
    }

    public void addUser(Users users) {
        userRepository.save(users).subscribe();
    }

    public Mono<Users> updateUser(Users user) {
        return userRepository.findById(user.getId())
                .switchIfEmpty(Mono.error(new Exception("User Not Found")))
                .map(oldUser -> {
                    if (user.getSurname() != null) oldUser.
                })
    }

}
