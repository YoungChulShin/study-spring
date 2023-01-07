package study.spring.redis.redisstorage.application

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Service
import study.spring.redis.redisstorage.domain.AgentLocation
import java.util.concurrent.TimeUnit

@Service
class AgentLocationService(
    private val redisTemplate: StringRedisTemplate,
    private val objectMapper: ObjectMapper,
) {
    companion object {
        private const val KEY_PREFIX_AGENT_LOCATION_HASH = "agent:"
    }

    fun saveLocation(agentId: Long, request: AgentLocation): AgentLocation {
        // Strings 데이터 저장
        val ops = redisTemplate.opsForValue()
        ops.set(
            agentId.toString(),
            objectMapper.writeValueAsString(request),
            10,
            TimeUnit.SECONDS
        )

        // Hash 데이터 저장
        val opsForHash = redisTemplate.opsForHash<String, String>()
        val map = mutableMapOf(
            "longitude" to request.longitude.toString(),
            "latitude" to request.latitude.toString(),
        )
        opsForHash.putAll(KEY_PREFIX_AGENT_LOCATION_HASH + agentId, map)

        return request
    }

    fun getLocation(agentId: Long): AgentLocation {
        return redisTemplate.opsForValue().get(agentId.toString())
            ?.let { objectMapper.readValue(it, AgentLocation::class.java) }
            ?: redisTemplate.opsForHash<String, String>().entries(KEY_PREFIX_AGENT_LOCATION_HASH + agentId)
                .let {
                    if (it.size < 2) throw java.lang.RuntimeException("위치 데이터가 없습니다")
                    AgentLocation(
                        it["latitude"]!!.toDouble(),
                        it["longitude"]!!.toDouble()
                    )
                }
    }
}