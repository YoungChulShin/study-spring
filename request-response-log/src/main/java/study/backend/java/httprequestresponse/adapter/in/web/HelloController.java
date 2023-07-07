package study.backend.java.httprequestresponse.adapter.in.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.backend.java.httprequestresponse.adapter.in.web.model.HelloRememberRequest;
import study.backend.java.httprequestresponse.common.response.CommonResponse;
import study.backend.java.httprequestresponse.application.port.in.HelloUseCase;

@RestController
@RequiredArgsConstructor
public class HelloController {

  private final HelloUseCase helloUseCase;

  @GetMapping("/hello")
  public CommonResponse<String> hello(@RequestParam(name = "name") String name) {
    return CommonResponse.success(helloUseCase.hello(name));
  }

  @PostMapping("/hello/remember")
  public CommonResponse<String> remember(
      @RequestBody @Valid HelloRememberRequest request) {
    return CommonResponse.success(request.toString());
  }
}
