package study.spring.security.jwt_security3.api;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.security.jwt_security3.domain.User;
import study.spring.security.jwt_security3.service.UserService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserResource {

  private final UserService userService;

  @GetMapping("/users")
  public ResponseEntity<List<User>> getUsers() {
    return ResponseEntity.ok().body(userService.getUsers());
  }
}
