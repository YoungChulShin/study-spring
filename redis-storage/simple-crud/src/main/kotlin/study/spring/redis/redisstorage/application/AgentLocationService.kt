package study.spring.redis.redisstorage.application

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import study.spring.redis.redisstorage.domain.AgentLocation
import java.lang.RuntimeException
import java.time.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

@Service
class AgentLocationService(
    private val redisTemplate: StringRedisTemplate,
    private val objectMapper: ObjectMapper,
) {
    fun saveLocation(agentId: Long, request: AgentLocation): AgentLocation {
        val ops = redisTemplate.opsForValue()
        ops.set(
            agentId.toString(),
            objectMapper.writeValueAsString(request),
            10,
            TimeUnit.SECONDS
        )

        return request
    }

    fun getLocation(agentId: Long): AgentLocation {
        val value = redisTemplate.opsForValue().get(agentId.toString())
            ?: throw RuntimeException("위치 정보가 없습니다")

        return objectMapper.readValue(value, AgentLocation::class.java)
    }
}