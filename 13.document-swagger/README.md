# 저장소 설명
스프링에서 API를 개발할 때, swagger를 이용해서 문서화 하는 방법을 정리합니다.

# 문서화 라이브러리
[springdoc-openapi](https://springdoc.org/#Introduction) 라이브러리를 사용합니다. 
- 보통 [springfox](https://github.com/springfox/springfox)를 많이 사용하는데, 2020년 이후에 더이상 업그레이드가 되지 않는 문제가 있습니다. 

제공 기능 (홈페이지 기준)
- OpenAPI 3
- Spring-boot v3 (Java 17 & Jakarta EE 9)
- JSR-303, specifically for @NotNull, @Min, @Max, and @Size.
- Swagger-ui
- OAuth 2
- GraalVM native images

# 문서화
## 라이브러리 추가
WebMVC 기준으로 아래 라이브러리를 추가합니다. 
```groovy
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0'
```

[`Jakarta Bean Validation`](https://beanvalidation.org/)을 사용하려면 아래 의존성을 함께 추가합니다. 
```groovy
implementation 'org.springframework.boot:spring-boot-starter-validation'
```

## 애플리케이션 코드에 Swagger 정보 추가
Controller 코드를 작성하고, Swagger를 위한 정보를 추가합니다. 

### 자주 사용하는 애노테이션 정보
`@Tag`
- tag 기능을 사용하면, swagger ui에서 tag 기준으로 api들을 볼 수 있습니다.
   ```java
   @Tag(name = "기관 관리")
   ```

`@Operation`
- 메서드에 선언해서 api 설명을 추가할 수 있습니다.
   ```java
   @Operation(summary = "기관 리스트 정보를 조회합니다.")
   ```

`@ApiResponses`, `@ApiResponse`
- API의 응답 정보를 설정할 수 있습니다.
   ```java
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
   ```

`@Parameters`, `@Parameter`
- query parameter 또는 path variable의 속성을 정의해줄 수 있습니다.
   ```java
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
   ```

`@Schema`
- Request Body 등에 포함된 필드에 속성을 정의해줄 수 있습니다. 
   ```java 
   @Schema(name = "userName", description = "사용자 이름")
   @NotEmpty
   String userName;
   ```


## 접속 정보
swagger-ui: `/context-path/swagger-ui.html`

api-docs
- json: `/context-path/v3/api-docs`
- yaml: `/context-path/v3/api-docs.yaml`


## swagger-annotations
참고 문서: https://javadoc.io/doc/io.swagger.core.v3/swagger-annotations/latest/index.html







