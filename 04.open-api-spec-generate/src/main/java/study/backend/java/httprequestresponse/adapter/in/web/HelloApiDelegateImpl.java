package study.backend.java.httprequestresponse.adapter.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import study.backend.java.httprequestresponse.rest.HelloApiDelegate;
import study.backend.java.httprequestresponse.rest.HelloGet200ResponseDto;
import study.backend.java.httprequestresponse.rest.HelloRememberPost200ResponseDto;
import study.backend.java.httprequestresponse.rest.HelloRememberPostRequestDto;

@Component
public class HelloApiDelegateImpl implements HelloApiDelegate {

  @Override
  public ResponseEntity<HelloGet200ResponseDto> helloGet(String name) {
    return HelloApiDelegate.super.helloGet(name);
  }

  @Override
  public ResponseEntity<HelloGet200ResponseDto> helloNameGet(String name) {
    return HelloApiDelegate.super.helloNameGet(name);
  }

  @Override
  public ResponseEntity<HelloRememberPost200ResponseDto> helloRememberPost(
      HelloRememberPostRequestDto helloRememberPostRequestDto) {

    return HelloApiDelegate.super.helloRememberPost(helloRememberPostRequestDto);
  }
}
