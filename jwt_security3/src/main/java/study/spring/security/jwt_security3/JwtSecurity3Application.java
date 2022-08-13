package study.spring.security.jwt_security3;

import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import study.spring.security.jwt_security3.domain.Role;
import study.spring.security.jwt_security3.domain.User;
import study.spring.security.jwt_security3.service.UserService;

@SpringBootApplication
public class JwtSecurity3Application {

  public static void main(String[] args) {
    SpringApplication.run(JwtSecurity3Application.class, args);
  }

  @Bean
  CommandLineRunner run(UserService userService) {
    return args -> {
      userService.saveRole(new Role(null, "ROLE_USER"));
      userService.saveRole(new Role(null, "ROLE_MANAGER"));
      userService.saveRole(new Role(null, "ROLE_ADMIN"));
      userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

      userService.saveUser(new User(null, "shin", "shin", "1323", new ArrayList<>()));
      userService.saveUser(new User(null, "kim", "kim", "1323", new ArrayList<>()));
      userService.saveUser(new User(null, "kang", "kang", "1323", new ArrayList<>()));
      userService.saveUser(new User(null, "min", "min", "1323", new ArrayList<>()));

      userService.addRoleToUser("shin", "ROLE_USER");
      userService.addRoleToUser("shin", "ROLE_MANAGER");
      userService.addRoleToUser("kim", "ROLE_MANAGER");
      userService.addRoleToUser("kang", "ROLE_ADMIN");
      userService.addRoleToUser("min", "ROLE_SUPER_ADMIN");
      userService.addRoleToUser("min", "ROLE_ADMIN");
      userService.addRoleToUser("min", "ROLE_USER");
    };
  }
}
