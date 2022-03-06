package study.spring.caller.service;

import org.springframework.stereotype.Service;
import study.spring.caller.infra.HelloApiClient;

@Service
public class HelloService {

  private final HelloApiClient helloApiClient;

  public HelloService(HelloApiClient helloApiClient) {
    this.helloApiClient = helloApiClient;
  }

  public String hello(String name) {
    return helloApiClient.hello(name);
  }
}
