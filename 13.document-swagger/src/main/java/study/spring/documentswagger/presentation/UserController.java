package study.spring.documentswagger.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.spring.documentswagger.presentation.model.AddUserRequest;
import study.spring.documentswagger.presentation.model.ErrorDto;
import study.spring.documentswagger.presentation.model.GenderDto;
import study.spring.documentswagger.presentation.model.UpdateUserRequest;
import study.spring.documentswagger.presentation.model.UserDto;

@Tag(name = "사용자 관리")
@RestController
public class UserController {

  @Operation(summary = "'userId'를 이용해서 사용자 정보를 조회합니다.")
  @Parameters(
      value = {
          @Parameter(
              name = "userId",
              description = "사용자 id",
              example = "1",
              required = true
          )
      }
  )
  @GetMapping("/api/users/{userId}")
  public UserDto get(@PathVariable Long userId) {
    return new UserDto(userId, "testuser", 10, GenderDto.MALE);
  }

  @Operation(summary = "사용자 정보를 생성합니다.")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "사용자 생성 성공",
          content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = UserDto.class)) }
      ),
      @ApiResponse(
          responseCode = "400",
          description = "잘못된 요청",
          content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDto.class)) }
      ),
      @ApiResponse(
          responseCode = "500",
          description = "내부 처리중 에러 발생",
          content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = ErrorDto.class)) }
      )
  })
  @PostMapping("/api/users")
  public UserDto add(@RequestBody @Valid AddUserRequest request) {
    return new UserDto(1L, request.getUserName(), request.getAge(), request.getGender());
  }

  @Operation(summary = "사용자 정보를 수정합니다.")
  @PutMapping("/api/users/{userId}")
  public UserDto update(
      @PathVariable Long userId,
      UpdateUserRequest request) {
    return new UserDto(userId, request.userName(), request.age(), request.gender());
  }

  @Operation(summary = "사용자 정보를 삭제합니다.")
  @DeleteMapping("/api/users/{userId}")
  public Long delete(@PathVariable Long userId) {
    return userId;
  }
}
