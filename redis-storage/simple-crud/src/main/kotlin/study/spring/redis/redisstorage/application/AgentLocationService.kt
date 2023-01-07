package study.spring.redis.redisstorage.application

import org.springframework.stereotype.Service
import study.spring.redis.redisstorage.domain.AgentLocation

@Service
class AgentLocationService {

    fun saveLocation(agentId: Long, request: AgentLocation): AgentLocation {

        return request
    }

    fun getLocation(agentId: Long): AgentLocation {
        return AgentLocation(1.0, 1.0)
    }
}