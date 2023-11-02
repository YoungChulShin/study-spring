
# 테스트
## 테스트 케이스 - spring.jpa.properties.jakarta.persistence.query.timeout 설정
동작
- 설정한 시간이 지나면 에러 발생한다. 
- DB에서도 실행되고 있는 쿼리는 종료된다. (`show full processlist`로 모니터링)

에러 정보
- org.springframework.dao.QueryTimeoutException 이 발생한다. 
- 내부적으로는 hibernate에서 에러가 발생했지만, 스프링에서 에러를 추상화해서 보여주는 것 같다. 
- error stack trace
   - org.springframework.dao.QueryTimeoutException: JDBC exception executing SQL [select b1_0.id,b1_0.author,b1_0.name,b1_0.published_at from books b1_0] [Statement cancelled due to timeout or client request] [n/a]; SQL [n/a]
   - org.hibernate.QueryTimeoutException: JDBC exception executing SQL [select b1_0.id,b1_0.author,b1_0.name,b1_0.published_at from books b1_0] [Statement cancelled due to timeout or client request] [n/a]
   - com.mysql.cj.jdbc.exceptions.MySQLTimeoutException: Statement cancelled due to timeout or client request

2.x 버전
- `spring.jpa.properties.javax.persistence.query.timeout` 옵션을 사용한다.

