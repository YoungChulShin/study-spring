package study.spring.food_delivery.common;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import study.spring.food_delivery.domain.listener.AgentLocationExpirationListener;

@Slf4j
@EnableCaching
@Configuration
public class RedisConfig {

  private final String EXPIRE_PATTERN = "__keyevent@*__:expired";

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory
        (new RedisStandaloneConfiguration("localhost", 6379));
  }

  @Bean
  public RedisTemplate<?, ?> redisTemplate() {
    RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory());
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    return redisTemplate;
  }

  @Bean(name = "redisCacheManager")
  public RedisCacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
    RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
        .disableCachingNullValues()
        .entryTtl(Duration.ofSeconds(RedisKey.DEFAULT_TTL_SEC))
        .computePrefixWith(CacheKeyPrefix.simple())
        .serializeKeysWith(
            RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));

    Map<String, RedisCacheConfiguration> cacheConfigurations = new LinkedHashMap<>();
    cacheConfigurations.put(
        RedisKey.KEY_AGENT_LOCATION,
        RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofSeconds(RedisKey.KEY_AGENT_LOCATION_TTL_SEC)));

    return RedisCacheManager.RedisCacheManagerBuilder
        .fromConnectionFactory(redisConnectionFactory)
        .cacheDefaults(configuration)
        .withInitialCacheConfigurations(cacheConfigurations)
        .build();
  }

  @Bean
  public RedisMessageListenerContainer redisMessageListenerContainer(
      RedisConnectionFactory connectionFactory,
      AgentLocationExpirationListener agentLocationExpirationListener) {
    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.addMessageListener(agentLocationExpirationListener, new PatternTopic(EXPIRE_PATTERN));
    container.setErrorHandler(e -> log.error(e.getMessage()));
    return container;
  }

}
