# 저장소 설명
스프링에서 API를 개발할 때, swagger를 이용해서 문서화 하는 방법을 정리합니다.

# 문서화 라이브러리
[springdoc-openapi](https://springdoc.org/#Introduction) 라이브러리를 사용합니다. 

보통 [springfox](https://github.com/springfox/springfox)를 많이 사용하는데, 2020년 이후에 더이상 업그레이드가 되지 않는 문제가 있다. 

제공 기능 (홈페이지 기준)
- OpenAPI 3
- Spring-boot v3 (Java 17 & Jakarta EE 9)
- JSR-303, specifically for @NotNull, @Min, @Max, and @Size.
- Swagger-ui
- OAuth 2
- GraalVM native images

# 문서화
## 접속 정보
swagger-ui: `/context-path/swagger-ui.html`

api-docs
- json: `/context-path/v3/api-docs`
- yaml: `/context-path/v3/api-docs.yaml`

## swagger-annotations
참고 문서: https://javadoc.io/doc/io.swagger.core.v3/swagger-annotations/latest/index.html






