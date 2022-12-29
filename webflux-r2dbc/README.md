# 저장소 설명
Spring + R2DBC 샘플 코드를 작성하고 설명합니다.

# 샘플 코드 설명
학교, 학생을 등록하고 조회하는 API를 구현합니다. 

# 실행
프로그램 실행
1. 도커 컴포즈 실행
   ```
   ❯ docker-compose -f docker/docker-compose.yml up -d
   ```



# 코드 설명
## 의존성 설정
```
// webflux 사용
org.springframework.boot:spring-boot-starter-webflux

// r2dbc 사용
org.springframework.boot:spring-boot-starter-data-r2dbc
com.github.jasync-sql:jasync-r2dbc-mysql:2.0.8
```

특이사항
- 스프링 버전과 `jasync-r2dbc-mysql` 버전이 일치하지 않으면 에러가 발생하는 경우가 있다
- 테스트하는 2.0.8은 스프링 2.6.X 버전에서 테스트 성공

## R2DBC Repository
### Repository 활성화
`'@EnableR2dbcRepositories'`를 이용해서 R2DBC를 활성화한다

### R2dbcRepository
`R2dbcRepository` extends `ReactiveSortingRepository` extends `ReactiveCrudRepository`

## 연관관계 설정
r2dbc는 jpa처럼 연관관계 설정이 지원되지 않는다. 따라서 기본적으로는 id 참고를 해야하고, 객체참조를 하고 싶다면 `@Transient`로 해서 DB와는 상관없도록 해야한다. 
```java
// Student 클래스
private Long schoolId;

@Transient
private School school;
```

## Value 클래스로 매핑
개념
- `DatabaseClient` 로 데이터를 조회
- 조회된 데이터를 row로 접근해서, row 정보를 반환할 객채로 생성하는 방식 사용

샘플 코드
```java
@Override
public Mono<StudentInfo> findStudentInfoById(Long id) {
    var sql = """
            SELECT s.id, s.name, s.age, s.gender, sc.name as schoolName
            FROM  students s
            INNER JOIN  schools sc
                    ON  s.school_id = sc.id
            WHERE s.id = :id
            """;

    return databaseClient.sql(sql)
            .bind("id", id)
            .fetch().one()
            .map(row -> new StudentInfo(
                    (Long) row.get("id"),
                    (String) row.get("name"),
                    (int) row.get("age"),
                    Gender.valueOf((String) row.get("gender")),
                    (String) row.get("schoolName")));
}
```