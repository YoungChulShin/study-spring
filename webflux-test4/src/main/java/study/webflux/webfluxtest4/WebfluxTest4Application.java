package study.webflux.webfluxtest4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class WebfluxTest4Application {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxTest4Application.class, args);
    }

}
