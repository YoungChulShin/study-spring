# Redis Cache
## 들어가면서
Spring에서 Cache 설정을 할 수 있는 방법은 몇가지가 있다. 여기서는 `'org.springframework.cache.annotation'` 을 이용해서 캐싱을 해보는 방법을 알아본다.  

다른 방법이라면 예를 들어서 Redis를 이용한다고 할 때 RedisTemplate을 이용해서 명시적으로 데이터를 저장/삭제 해줄 수도 있다. 

## 기본 설정
`@EnableCaching` 애노테이션을 이용해서 애노테이션을 이용해서 캐시 관리를 할 수 있도록 활성화한다. 
```kotlin
@Configuration
@EnableCaching
class CacheConfig
```

`CacheManager`의 구현체를 등록해준다. 여기서는 Redis를 사용하기 위해서 `RedisCacheManager`를 구현체로 등록해준다. 
```kotlin
@Bean
fun cacheManager(connectionFactory: RedisConnectionFactory): RedisCacheManager {
    return RedisCacheManager.create(connectionFactory)
}
```

## 캐시 사용
### `@Cacheable` 
기능: 응답 값을 캐시로 저장할 수 있다

파라미터:
- value: cache의 이름
- key: cache의 키. SPEL으로 관리된다.

redis에 저장될 때, `value::key` 로 저장된다. key를 지정하지 않으면 parameter의 hash값으로 자동지정되고, 파라미터가 없으면 'SimpleKey'로 지정된다.

key의 구현체
- `KeyGenerator` 인터페이스의 구현체로 커스텀 키를 생성할 수 있다
- 기본은 `SimpleKeyGenerator`를 사용한다

샘플 코드
```kotlin
@Cacheable(
    value = [CacheKey.FIND_USER],
    key = "#name",
)
fun findUser(name: String): String? {
```


### `@CacheEvict`
기능: 키를 삭제할 수 있다

파라미터: 
- value: cache의 이름
- key: cache의 키
- allEntries: 모든 항목을 삭제할 지 여부. list 형식으로 저장된 cache를 삭제할 때 쓸 수 있다

샘플 코드
```kotlin
@CacheEvict(
    value = [CacheKey.FIND_USERS],
    allEntries = true,
)
fun addUser(name: String) {
```

### Multi Cache 옵션 사용
1개의 파라미터에 n개의 애노테이션을 적용하고 싶으면 `@Caching` 애노테이션을 이용한다. 항목별로 array로 값을 받을 수 있다. 
```java
public @interface Caching {
	Cacheable[] cacheable() default {};
	CachePut[] put() default {};
	CacheEvict[] evict() default {};
}
```

## CacheManager 설정
기본 값의 CacheManager는 Serializer, TTL 설정등이 없기 때문에 커스텀하게 관리하기 위해서는 추가적인 설정을 해주면 좋다. 

### RedisCacheConfiguration
RedisCacheConfiguration 설정에 serializer, ttl 등의 설정을 추가해줄 수 있다.
```kotlin
private fun getDefaultConfiguration() =
    RedisCacheConfiguration.defaultCacheConfig()
        .disableCachingNullValues()
        // keySerializer 설정
        .serializeKeysWith( 
            RedisSerializationContext.SerializationPair.fromSerializer(
                StringRedisSerializer()
            )
        )
        // valueSerializer 설정. Readable한 String 값으로 저장하기 위해서 Jackson serializer를 이용했다
        .serializeValuesWith(
            RedisSerializationContext.SerializationPair.fromSerializer(
                Jackson2JsonRedisSerializer(objectMapper, Object::class.java)
            )
        )
        .entryTtl(Duration.ofSeconds(CacheKey.DEFAULT_TTL))
```

### Cache별 개별 설정
Cache별로 설정을 별도로 가기 위해서는 기본 설정되에 추가로 설정을 해줄 수 있다. 

cache name과 configuration을 key/value로 가지는 configuration을 설정해주고, 이 설정을 CacheManager에 `withInitialCacheConfigurations` 메서드로 추가해주면 된다. 

```kotlin
// 'CacheKey.FIND_USER' cache에 ttl 개별 설정 적용
val keyConfiguration = mutableMapOf(
            CacheKey.FIND_USER to getDefaultConfiguration()
                .entryTtl(Duration.ofSeconds(CacheKey.FIND_USER_TTL)),
        )

// 설정 내용을 cacheManager에 반영
RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory)
            .cacheDefaults(configuration)   // 기본 설정 값 적용
            .withInitialCacheConfigurations(keyConfiguration)  // 추가 개별 설정 적용
            .build()
```


### CacheManager를 직접 주입 받아서 사용
CacheManager를 빈으로 등록하기 때문에, 필요에 따라서 주입받아서 사용할 수 있다.

```kotlin
class UserService(
    private val cacheManager: RedisCacheManager,
) {

    fun test() {
        // cacheManager를 이용해서 특정 cache의 항목을 삭제
        cacheManager.getCache("cacheName")?.evict("keyName")
    }
}
```

## Cache 키 만료 설정