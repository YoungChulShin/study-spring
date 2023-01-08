## 저장소 설명
Spring Data Redis 테스트. 엔티티의 저장을 Redis를 이용해서 한다. 

## 코드 설명
기능
- Student 객체를 Redis를 이용해서 저장하고, 조회하는 기능을 구현한다. 

Student 엔티티 설정
- 엔티티에 `@RedisHash` 를 선언해주면 대상이 된다. 
   - 옵션 value: redis에 저장될 때, 엔티티의 prefix 정보
   - 옵션 timeToLive: ttl 정보. (seconds)

Redis에 저장되는 값
1. Hash 타입의 엔티티 정보
   - Hash에 엔티티 정보가 저장된다. 
   - ttl 설정이 되어 있으면 만료 시점에 삭제된다
   - 키: `@RedisHash`에서 설정한 value prefix + `id` 값
   - 샘플
      ```
      localhost:6379> hgetall Student:10
        1) "_class"
        2) "study.spring.redistest.Student"
        3) "id"
        4) "10"
        5) "name"
        6) "\xec\x98\x81\xec\xb0\xb01"
        7) "gender"
        8) "FEMALE"
        9) "grade"
        10) "1"
      ``` 
2. Set 타입의 엔티티 키
   - `id` 정보가 set으로 저장된다. 
   - 캐시가 만료되어도 삭제되지 않는다. 
   - 키: `@RedisHash`에서 설정한 value prefix 
   - 샘플
      ```
      localhost:6379> smembers Student
        1) "1"
        2) "3"
        3) "10"
      ```


## 참고 문서
- https://www.baeldung.com/spring-data-redis-tutorial
- https://markheath.net/post/exploring-redis-with-docker
- https://jybaek.tistory.com/711

## 기타
Docker Redis 실행
```
docker run --name rest-test -d -p 6379:6379 redis
``` 
