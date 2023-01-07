package study.spring.redis.redisstorage.presentation

import org.springframework.web.bind.annotation.*
import study.spring.redis.redisstorage.application.AgentLocationService
import study.spring.redis.redisstorage.domain.AgentLocation

@RestController
class AgentLocationController(private val agentLocationService: AgentLocationService) {

    @PostMapping("/api/v1/agent/{agentId}/location")
    fun saveLocation(
        @PathVariable agentId: Long,
        request: AgentLocation
    ): AgentLocation {
        return agentLocationService.saveLocation(agentId, request)
    }

    @GetMapping("/api/v1/agent/{agentId}/location")
    fun getLocation(
        @PathVariable agentId: Long,
    ): AgentLocation {
        return agentLocationService.getLocation(agentId)
    }
}