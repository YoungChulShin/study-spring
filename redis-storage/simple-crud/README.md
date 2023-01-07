# 저장소 설명
Redis를 이용한 심플 Create, Read 기능 구현

# 구현
1. 의존성 설정
   ```
   implementation("org.springframework.boot:spring-boot-starter-data-redis")
   ```
   - redis는 jedis, lettuce 2가지를 사용하는데, 여기서는 lettuce를 사용한다. 

2. 빈 등록. ConnectionFactory, RedisTemplate
   - RedisTemplate를 이용해서 명령어를 전달할 수 있다
      ```kotlin
      @Bean
      fun redisConnectionFactory(): LettuceConnectionFactory {
          return LettuceConnectionFactory(
              RedisStandaloneConfiguration(this.host, this.port)
          )
      }

      @Bean
      fun stringRedisTemplate(connectionFactory: RedisConnectionFactory): StringRedisTemplate {
        val redisTemplate = StringRedisTemplate()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        return redisTemplate
      }
      ```
3. RedisTemplate을 이용한 데이터 저장
   - `redisTemplate.opsForXXX`를 이용해서 커맨드를 전달할 수 있다
   - 예시
      ```
      opsForValue: value 타입 데이터 관리
      opsForHash: 해시타입 데이터 저장
      opsForSet: set 데이터 타입
      opsForZSet zset 데이터 타입
      ```
   - 해시타입 예시
      ```kotlin
      val opsForHash = redisTemplate.opsForHash<String, String>()
        val map = mutableMapOf(
            "longitude" to request.longitude.toString(),
            "latitude" to request.latitude.toString(),
        )
        opsForHash.putAll(KEY_PREFIX_AGENT_LOCATION_HASH + agentId, map)
      ```
4. RedisTemplate을 이용한 데이터 조회
   - `redisTemplate.opsForXXX`를 이용해서 커맨드를 전달할 수 있다
      