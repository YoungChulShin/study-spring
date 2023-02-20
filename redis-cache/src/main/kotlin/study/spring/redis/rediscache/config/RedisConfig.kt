package study.spring.redis.rediscache.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.listener.PatternTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import org.springframework.stereotype.Component
import java.time.Duration

@Configuration
class RedisConfig {

    companion object {
        private val logger = LoggerFactory.getLogger(RedisConfig::class.java)
    }

    @Bean
    fun redisConnectionFactory(): LettuceConnectionFactory {
        val config = RedisStandaloneConfiguration("localhost", 6379)
        config.username = "myuser"
        config.setPassword("password")
        return LettuceConnectionFactory(config)
    }

    @Bean
    fun cacheManager(
        connectionFactory: RedisConnectionFactory,
        objectMapper: ObjectMapper,
    ): RedisCacheManager {
        val configuration = getDefaultConfiguration(objectMapper)

        val keyConfiguration = mutableMapOf(
            CacheKey.FIND_USERS to getDefaultConfiguration(objectMapper)
                .entryTtl(Duration.ofSeconds(CacheKey.FIND_USERS_TTL))
                .serializeValuesWith(
                    RedisSerializationContext.SerializationPair.fromSerializer(
                        Jackson2JsonRedisSerializer(objectMapper, Set::class.java)
                    )
                ),
            CacheKey.FIND_USER to getDefaultConfiguration(objectMapper)
                .entryTtl(Duration.ofSeconds(CacheKey.FIND_USER_TTL)),
        )

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory)
            .cacheDefaults(configuration)
            .withInitialCacheConfigurations(keyConfiguration)
            .build()
    }

    private fun getDefaultConfiguration(objectMapper: ObjectMapper) =
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

    @Bean
    fun redisExpiredMessageListenerContainer(
        connectionFactory: RedisConnectionFactory,
        expiredMessageListener: ExpiredMessageListener,
    ): RedisMessageListenerContainer {
        val container = RedisMessageListenerContainer()
        with(container) {
            this.setConnectionFactory(connectionFactory)
            this.addMessageListener(
                expiredMessageListener,
                PatternTopic("__keyevent@*__:expired")
            )
            this.setErrorHandler {
                logger.error("Expire listener error: ${it.message}", it)
            }
        }

        return container
    }
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

@Component
class ExpiredMessageListener : MessageListener {

    companion object {
        private val logger = LoggerFactory.getLogger(ExpiredMessageListener::class.java)
    }

    override fun onMessage(message: Message, pattern: ByteArray?) {
        logger.info(message.toString())
    }

}