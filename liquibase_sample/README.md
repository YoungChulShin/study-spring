### 문서 설명
SpringBoot + MySQL + Liquibase 테스트 예제

### 샘플 프로젝트 참고 문서
https://www.baeldung.com/liquibase-refactor-schema-of-java-app

### Liquibase 적용 순서
1. 의존성 추가
   ~~~
   compile group: 'org.liquibase', name: 'liquibase-core'
   ~~~ 
2. application.yml(or application.properties)에서 변경 정보에 대한 경로 설정
   ~~~yml
   spring:
      liquibase:
         change-log: classpath:/db/changelog-master.xml
   ~~~ 
3. 변경 정보 기록 
4. 변경 정보를 master 파일에 include

### 프로그램 실행
1. mysql 실행
   ~~~
   docker-compose -f scripts/mysql.yml up -d
   ~~~
2. 프로그램 실행

### 기타 문서
- Liquibase.org: [Link](https://www.liquibase.org/)
- docker compose: [Link](https://docs.docker.com/compose/compose-file/)
- yaml 설정: [Link](https://github.com/swri-robotics/bag-database/blob/master/src/main/resources/db/changelog/db.changelog-1.0.yaml)
