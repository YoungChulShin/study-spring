package study.spring.redis.rediscache.config

import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Configuration
class RedisConfig(
    private val objectMapper: ObjectMapper,
) {

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        return LettuceConnectionFactory(RedisStandaloneConfiguration("localhost", 6379))
    }

    @Bean
    fun cacheManager(connectionFactory: RedisConnectionFactory): RedisCacheManager {
        val configuration = getDefaultConfiguration()

        val keyConfiguration = mutableMapOf(
            CacheKey.FIND_USERS to getDefaultConfiguration()
                .entryTtl(Duration.ofSeconds(CacheKey.FIND_USERS_TTL))
                .serializeValuesWith(
                    RedisSerializationContext.SerializationPair.fromSerializer(
                        Jackson2JsonRedisSerializer(objectMapper, Set::class.java)
                    )
                )
            ,
            CacheKey.FIND_USER to getDefaultConfiguration()
                .entryTtl(Duration.ofSeconds(CacheKey.FIND_USER_TTL)),
        )

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory)
            .cacheDefaults(configuration)
            .withInitialCacheConfigurations(keyConfiguration)
            .build()
    }

    private fun getDefaultConfiguration() =
        RedisCacheConfiguration.defaultCacheConfig()
            .disableCachingNullValues()
            .serializeKeysWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    StringRedisSerializer()
                )
            )
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    Jackson2JsonRedisSerializer(objectMapper, Object::class.java)
                )
            )
            .entryTtl(Duration.ofSeconds(CacheKey.DEFAULT_TTL))
}

class CacheKey {
    companion object {
        const val DEFAULT_TTL = 120L
        const val FIND_USERS = "findUsers"
        const val FIND_USERS_TTL = 120L
        const val FIND_USER = "findUser"
        const val FIND_USER_TTL = 120L
    }
}