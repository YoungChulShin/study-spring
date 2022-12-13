package study.spring.webclienttest.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.spring.webclienttest.dto.UserInfo;

@Service
public class WebClientService {

    private final WebClient webClient;

    public WebClientService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8080/api").build();
    }

    public Flux<UserInfo> findUsers() {
        return this.webClient
                .get()
                .uri("/users")
                .retrieve()
                .bodyToFlux(UserInfo.class);
    }

    public Mono<UserInfo> findUser(Long id) {
        return this.webClient
                .get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(UserInfo.class);
    }

    public Mono<ClientResponse> saveUser(UserInfo userInfo) {
        return this.webClient
                .post()
                .uri("/users")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(userInfo), UserInfo.class)
                .exchange();
    }

    public Mono<UserInfo> updateUser(UserInfo userInfo) {
        return this.webClient
                .put()
                .uri("/users")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(userInfo), UserInfo.class)
                .retrieve()
                .bodyToMono(UserInfo.class);
    }

    public Mono<Void> deleteUser(Long id) {
        return this.webClient
                .delete()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(Void.class);
    }
}
