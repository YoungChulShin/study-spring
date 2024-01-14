package study.spring.documentswagger.presentation;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.spring.documentswagger.presentation.model.AddUserRequest;
import study.spring.documentswagger.presentation.model.GenderDto;
import study.spring.documentswagger.presentation.model.UpdateUserRequest;
import study.spring.documentswagger.presentation.model.UserDto;

@RestController
public class UserController {

  @PostMapping("/api/users")
  public UserDto add(@RequestBody AddUserRequest request) {
    return new UserDto(1L, request.userName(), request.age(), request.gender());
  }

  @GetMapping("/api/users/{userId}")
  public UserDto get(@PathVariable Long userId) {
    return new UserDto(userId, "testuser", 10, GenderDto.MALE);
  }

  @PutMapping("/api/users/{userId}")
  public UserDto update(
      @PathVariable Long userId,
      UpdateUserRequest request) {
    return new UserDto(userId, request.userName(), request.age(), request.gender());
  }

  @DeleteMapping("/api/users/{userId}")
  public Long delete(@PathVariable Long userId) {
    return userId;
  }
}
