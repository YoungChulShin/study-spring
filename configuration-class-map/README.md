# 저장소 설명
application.yml 파일에 설정한 정보를 데이터 클래스에 매핑합니다.

# 매핑 테스트
`StudentTest`: 개발 아이템을 하나의 클래스에 매핑

`SchoolsTest`: 중첩되는 아이템을 매핑

# 매핑 사용 방법
1. application.yml에 속성 값 설정
2. 속성 값에 매핑되는 클래스 생성
   - 예: Student, Schools
3. 클래스에 `@ConfigurationProperties` 정의
   - `value` 옵션에 yml의 prefix 값 입력
4. 설정 클래스에 `@ConfigurationPropertiesScan` 정의
   - 이 예제에서는 main 클래스에 정의
5. `build.gradle`에 dependency 설정
   ```
   annotationProcessor ("org.springframework.boot:spring-boot-configuration-processor")
   ```

# 참고 사이트
https://docs.spring.io/spring-boot/docs/2.7.7/reference/html/configuration-metadata.html#appendix.configuration-metadata.annotation-processor