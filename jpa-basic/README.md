# JPA Basic
JPA 사용을 위한 기본 코드를 샘플코드와 함께 설명합니다.
## 내용
- mysql container 동작
- JPA 연동
- querydsl 연동
- native query 연동
- n+1 쿼리를 방지하기 위한 bach size 조절

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

### Querydsl 설정
Querydsl을 사용하기 위한 설정이 `'build.gradle'` 파일에 들어가 있습니다.

