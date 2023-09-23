# JPA Basic
JPA 사용을 위한 기본 코드를 샘플코드와 함께 설명합니다.
## 내용
- mysql container 동작
- JPA 연동
- querydsl 연동
- native query 연동

## mysql container 동작
### 컨테이너 동작
`docker-compose` 를 이용해서 컨테이너를 동작합니다. `'/docker'` 폴더에서 파일 확인 가능합니다. 

docker-compose 커맨드
```
// 컨테이너 구동
docker-compose up -d

// 컨테이너 종료
docker-compose down
```

### SQL 초기화
mysql 컨테이너가 처음 실행될 때, 필요한 초기화 코드를 추가합니다. `'docker/data'` 폴더에서 확인 가능합니다. 

역할
- root 계정외에 서비스 계정에 대한 권한 부여
- db 생성
- 초기화 테이블 생성 또는 초기화 데이터 구성

생성한 초기화 파일은 docker volumns에서 `/docker-entrypoint-initdb.d` 경로로 설정해서 컨테이너 처음 실행될 때 동작하도록 합니다. 
```yaml
services:
  db:
    volumes:
      - ./data:/docker-entrypoint-initdb.d
```

## JPA 사용
### 라이브러리 구성
라이브러리
- spring-boot-starter-data-jpa: jpa 사용
- mysql-connector-java: mysql 사용
- p6spy-spring-boot-starter: 실제 파라미터가 포함된 쿼리 로그 출력

### 샘플 엔티티 구성
엔티티 구성
- School: 학교 정보
- Student: 학생 정보
- School과 Student가 1:N 양방향 연관관계를 가집니다. 

### N+1 문제 해결
학교를 조회할 때, 학생 정보를 가져오는 과정에서 N+1이 발생할 수 있습니다. 발생 케이스와 fetchJoin, Querydsl로 문제를 해결하는 방법을 설명합니다. 
- `application.service.SchoolService#findSchools()`을 확인합니다.

## Querydsl 사용
### 설정
설정
- Querydsl을 사용하기 위한 설정이 `'build.gradle'` 파일에 들어가 있습니다.
- IntelliJ의 'Preference -> Build.,Execution,Deployment -> Compiler -> Annotation Processors' 메뉴에서 `'Enable annotation processing'`을 활성화합니다. 

### 사용
JpaRepository와 함께 사용
- `StudentJpaRepository`, `StudentJpaRepositoryCustom`, `StudentJpaRepositoryImpl`을 참고합니다. 

QuerydslRepositorySupport
- 복잡한 조회 조건을 처리할 때, 코드 기반으로 쿼리를 편하게 작성할 수 있습니다.

Entity가 아닌 Custom Class를 응답하도록 처리
- `ckend.java.database.adapter.out.persistenceStudentJpaRepositoryImpl#findStudent` 코드를 참고한다

## 기타 Application Code
### 페이징 설정
inbound web adapter
- `size`, `page number` 값을 입력 받는다.

inbound port
- 페이징 쿼리가 필요할 경우 `Pageable` 인터페이스를 전달 받는다.
- `inbound web adapter`에서는 `PageRequest.of` 메서드를 이용해서 Pageable 인터페이스의 구현체 `PageRequest` 클래스를 생성한다.   

outbound persistence adapter
- querydsl에서는 `QuerydslRepositorySupport` 클래스를 상속받으면 `Querydsl` 클래스 인스턴스에 접근할 수 있는데, 이 클래스는 querydsl을 위한 helper 메서드를 제공한다. 
   - `applyPagination` 메서드를 이용한 `Pageable` 인터페이스를 이용해서 페이징 쿼리를 구현할 수 있다.
   - 내부적으로는 query에 limit과 offset을 적용한다.  


### 참고 링크
querydsl 정리: https://github.com/YoungChulShin/TIL/tree/master/Spring/05.JPA/Querydsl

