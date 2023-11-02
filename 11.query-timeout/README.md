
# 참고 문서
- naver 타임아웃 설명: https://d2.naver.com/helloworld/1321
- socketTimeout 설명: https://knight76.tistory.com/entry/mysql-jdbc-driver-url%EC%9D%98-connectTimeout%EA%B3%BC-socketTimeout

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

## 테스트 케이스 - socketTimeout 설정
socketTimeout 에러
- 서버와 DB가 연결된 이후에 일정 시간동안 DB에서 서버로 데이터가 전달되지 않으면 발생한다.

socketTimeout 설정
- jdbc url에 `socketTimeout`을 추가해서 설정할 수 있다. 
- 단위는 ms이다. 

에러 정보
- org.springframework.dao.DataAccessResourceFailureException 이 발생한다. 
- error stack trace
   - org.springframework.dao.DataAccessResourceFailureException: JDBC exception executing SQL [select b1_0.id,b1_0.author,b1_0.name,b1_0.published_at from books b1_0 where b1_0.published_at between '2023-11-02 10:09:46.133499' and '2023-11-02 10:09:55.099997'] [Communications link failure 
      - The last packet successfully received from the server was 11,054 milliseconds ago. The last packet sent successfully to the server was 11,056 milliseconds ago.] [n/a]
   - org.hibernate.exception.JDBCConnectionException: JDBC exception executing SQL [select b1_0.id,b1_0.author,b1_0.name,b1_0.published_at from books b1_0 where b1_0.published_at between '2023-11-02 10:09:46.133499' and '2023-11-02 10:09:55.099997'] [Communications link failure 
      - The last packet successfully received from the server was 11,054 milliseconds ago. The last packet sent successfully to the server was 11,056 milliseconds ago.] [n/a]
   - com.mysql.cj.jdbc.exceptions.CommunicationsException: Communications link failure 
      - The last packet successfully received from the server was 11,054 milliseconds ago. The last packet sent successfully to the server was 11,056 milliseconds ago.
   - com.mysql.cj.exceptions.CJCommunicationsException: Communications link failure 
      - The last packet successfully received from the server was 11,054 milliseconds ago. The last packet sent successfully to the server was 11,056 milliseconds ago.
   - java.net.SocketTimeoutException: Read timed out
