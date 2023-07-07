# 01. Http Request & Response
## 내용
- 공통 응답 처리
- 공통 에러 처리
- Request Validation
- Request & Response Logging

## 공통 응답 처리
설명:
- 클라이언트에게 응답할 공통 양식의 응답 모델을 정의한다.
- 클래스: `CommonResponse<T>`

구성:
- result: 성공/실패 여부
- data: 응답 데이터
- message: 메시지
- errorCode: 에러 코드

## 공통 에러 처리
설명:
- 예외를 처리하는 공통 에러 처리 기능을 추가한다. 
- 클래스: `CommonExceptionHandler`

특징:
- 공통 에러 처리를 통해서 응답 포멧이 공통된 사양으로 전달될 수 있도록 한다
- 예상하지 못한 에러에 대해서 에러 처리 코드에서 로그나 기타 데이터 수집을 진행한다
- `'SPECIFIC_ALERT_TARGET_ERROR_CODE_LIST'` 를 활용해서 알려진 예외에서도 에러 레벨을 조정해서 처리한다.

## Request Validation
설명
- Request 객체가 올바른 양식인지 검증한다.

의존성: `org.springframework.boot:spring-boot-starter-validation`

사용: 
1. Request class에 validation을 위한 의존성을 추가한다.
   ```java
   public record HelloRememberRequest(
    @NotEmpty
    String name,

    @NotNull @Min(0) @Max(100)
    Integer age,

    @Valid @NotNull
    AddressDto address
   )
   ```
2. Controller class에서 `Valid` 애노테이션을 적용한다.
   ```java
   @RequestBody @Valid HelloRememberRequest request
   ```

특징:
- `jakarta.validation.constraints`에서 사용가능한 애노테이션을 확인 가능하다.
- Request 클래스 내에 하위 클래스가 있고, 하위 클래스에도 validation을 적용하고 싶다면, 하위 클래스를 선언하는 곳에 `Valid`를 추가해준다

## Request & Response 로깅
설명
- Request와 Response를 로깅한다.

특징
- Filter를 이용해서 HttpServletRequest와 HttpServeletResponse를 Wrapping 한다. (`CacheServletFilter`)
- Interceptor를 이용해서 Log를 기록한다. (`RequestResponseLogInterceptor`)

로깅 여부 설정
- `interceptor.request-response-log` 설정에서 로깅을 제외할 대상을 지정할 수 있다
