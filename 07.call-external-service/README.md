# 설명
스프링에서 외부 서비스를 호출하기 위한 방법을 설명합니다. 

# 사용
## 등장 서비스 설명
Callee
- `SystemInfo` 라는 시스템 정보를 응답하는 서비스입니다.
- 의도적으로 에러를 만들기 위해서 5번째 호출마다 500 에러를 응답합니다. 
- `Caller`에 의해서 호출됩니다. 

Caller
- `사용자`에게 시스템 정보를 응답하는 요청을 받는 서비스입니다. 
- 하지만 `Caller`는 시스템 정보를 가지고 있지 않기 때문에, `Callee`를 호출해서 시스템 정보를 가져옵니다. 
- Caller는 3개의 방법으로 Callee를 호출합니다. 
   1. RestTemplate을 이용한 호출.
   2. WebClient를 이용한 호출.
   3. OpenFeign을 이용한 호출.

## 아키텍처
헥사고날 아키텍처를 적용했습니다. 구현체의 변경은 `SystemInfoPort`의 구현체에 따라서 달라집니다. 

구현체를 선택하는 방법은 `ConditionalOnProperty`를 이용해서 반영합니다. 
```yaml
system-info:
  external-call:
    type: resttemplate  # resttemplate, webcline, openfeign
```
```java
@Configuration
@ConditionalOnProperty(value = "system-info.external-call.type", havingValue = "resttemplate")
class RestTemplateConfiguration {
```

## RestTemplate
RestTemplate을 이용하면 동기적인 방식으로 외부 서비스를 호출할 수 있습니다.

`RestTemplateSystemInfo` 클래스에서 RestTemplate을 이용한 외부 서비스 호출 예시가 있습니다. 
```java
public SystemInfo getSystemInfo() {
    String url = "http://localhost:8080/api/v1/system";

    ResponseEntity<SystemInfo> response = restTemplate.exchange(
        url,
        HttpMethod.GET,
        null,
        SystemInfo.class);

    return response.getBody();
}
```

### ObjectMapper Converter
RestTemplate을 이용해서 데이터를 가져오고 응답 모델로 역직렬화 할 때, ObjectMapper의 설정이 영향을 줄 수 있습니다. 예를 들어서 요청모델에는 있는 필드가 응답모델에는 없다면 ObjectMapper의 기본 설정으로 인해서 에러가 발생합니다. 

이때는 ObjectMapper의 설정을 변경해줘야합니다. `RestTemplateConfiguration`에 관련 코드가 있습니다. 
```java
 ObjectMapper objectMapper = new ObjectMapper();
// 요청 데이터에는 있지만, 역질렬화 대상이 되는 클래스에는 없을 경우 에러 발생 여부 설정
objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
objectMapper.disable(FAIL_ON_NULL_FOR_PRIMITIVES);
objectMapper.enable(READ_UNKNOWN_ENUM_VALUES_AS_NULL);

var converter = new MappingJackson2HttpMessageConverter();
converter.setObjectMapper(objectMapper);
```

### Exception Handler
RestTemplate에서 에러가 발생했을 때, 에러를 핸들링할 수 있습니다. 

`ResponseErrorHandler`의 구현체를 생성하면 됩니다. 인터페이스는 2개의 메서드를 가지고 있습니다. 
- hasError: 어떤 응답을 에러로 처리할지 결정합니다. 
- handleError: 에러가 발생했을 때 어떻게 처리할지 정의합니다. 

## WebClient

## OpenFeign
OpenFeign을 이용하면 직접 요청하는 코드를 작성하지 않고, interface에 요청과 응답을 정의하는 방법으로 외부 호출을 할 수 있다. 

### 참고 문서
- 전체적인 feign 사용법: https://www.baeldung.com/spring-cloud-openfeign
- fallback 설정 확인: https://velog.io/@minwest/Spring-Cloud-OpenFeign%EC%9C%BC%EB%A1%9C-API-%ED%98%B8%EC%B6%9C%ED%95%B4%EB%B3%B4%EA%B8%B0
- timeout 설정 확인: https://pamyferret.tistory.com/72

### 의존성 설정
```groovy
ext {
    set('springCloudVersion', "2022.0.3")
}

// Check dependency management at https://spring.io/projects/spring-cloud
dependencyManagement {
    imports {
        mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
    }
}

dependencies {
    implementation "org.springframework.cloud:spring-cloud-starter-openfeign"
}
```

### OpenFeign 활성화
main 클래스에 `@EnableFeignClients` 애노테이션을 추가해준다. 이를 통해서 OpenFeign을 사용하는 인터페이스를 스캔하게 된다. 

### 요청 양식 작성
Feign의 요청사항은 인터페이스를 이용해서 작성한다. 

```java
// FeignClient를 명시
// - name: feign client name
// - url: base url
@FeignClient(   
    value = "systeminfo",
    url = "http://localhost:8080"
)
public interface OpenFeignSystemInfo extends SystemInfoPort {

  @Override
  // 스프링이 제공하는 애노테이션을 이용해서 요청을 정의해준다
  @RequestMapping(
      method = RequestMethod.GET,
      value = "/api/v1/system")
  SystemInfo getSystemInfo();
}
```

### 설정 - 로그
Feign에서 제공하는 로그를 사용하려면 아래 2개의 설정이 필요하다. 
1. 로그 레벨을 DEBUG로 설정한다. 
   ```groovy
   logging:
      level:
         study.spring.caller.adapter.out.systeminfo.openfeign.OpenFeignSystemInfo: DEBUG
   ```
2. Feign이 제공하는 Log Level을 정의해준다. 
   ```java
   @Bean
   Logger.Level feignLoggerLevel() {
      return Level.BASIC;
   }
   ```

Feign 로그 레벨 정보
- NONE – no logging, which is the default
- BASIC – log only the request method, URL and response status
- HEADERS – log the basic information together with request and response headers
- FULL – log the body, headers and metadata for both request and response

### 설정 - 에러 핸들러
발생하는 에러에 대해서 별도로 처리하고 싶으면 `ErrorDecoder` 인터페이스를 구현한다. 코드에서는 `OpenFeignErrorHandler` 클래스가 인터페이스를 구현한다. 

```java
@Configuration
class OpenFeignErrorHandler implements ErrorDecoder {

  private final Logger logger = LoggerFactory.getLogger(OpenFeignErrorHandler.class);

  @Override
  public Exception decode(String methodKey, Response response) {
    logger.info("RestTemplate: 에러 발생");

    HttpStatus httpStatus = HttpStatus.valueOf(response.status());
    if (httpStatus.is5xxServerError()) {
      throw new RuntimeException("호출 중에 에러가 발생했습니다. 호출 서버에서 에러가 발생했습니다.");
    } else if (httpStatus.is4xxClientError()) {
      throw new RuntimeException("호출 중에 에러가 발생했습니다. 호출 정보가 잘못되었습니다.");
    } else {
      throw new RuntimeException("호출 중에 에러가 발생했습니다.");
    }
  }
}
```

### 설정 - Request Interceptor
요청 정보를 가공하고 싶거나 요청 전에 추가적인 동작을 넣고 싶으면 `RequestInterceptor` 빈을 등록하면 된다. 코드에서는 `OpenFeignRequestInterceptor` 클래스를 보면 된다. 

```java
@Configuration
class OpenFeignRequestInterceptor {

  private final Logger logger = LoggerFactory.getLogger(OpenFeignRequestInterceptor.class);

  @Bean
  RequestInterceptor requestInterceptor() {
    return requestTemplate -> {
      logger.info("OpenFeign: 서비스 호출");    // 요청 전에 로그를 기록하고
      requestTemplate.header("test", "test header");    // Request의 Header에 값을 추가한다
    };
  }
}
```

### 설정 - Timeout 설정
참고한 문서에서는 아래 설정을 통해서 타임아웃 설정을 할 수 있다고 하는데, 이 부분이 동작하지 않았다. 
```yaml
feign:
  client:
    config:
      default:
        connectTimeout: 5000
        readTimeout: 5000
```

수정한 방법은 직접 타임아웃에 대한 빈을 설정해주는 방법으로 처리했다. 
```java
@Bean
Request.Options options() {
    return new Request.Options(
        3L,
        TimeUnit.SECONDS,
        5L,
        TimeUnit.SECONDS,
        false);
}
```

### 설정 - Fallback
`OpenFeignFallback` 클래스에 관련 코드를 작성했는데, 현재 동작하지 않아서 내용을 확인 중이다. 