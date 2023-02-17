package study.spring.redis.rediscache.service

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import study.spring.redis.rediscache.config.CacheKey

@Service
class UserService {

    companion object {
        private val users = mutableSetOf<String>()
    }

    @Cacheable(
        value = [CacheKey.FIND_USERS]
    )
    fun findUsers(): Set<String> {
        Thread.sleep(3000)
        return users
    }

    @Cacheable(
        value = [CacheKey.FIND_USER],
        key = "#name",
    )
    fun findUser(name: String): String? {
        Thread.sleep(3000)
        return users.firstOrNull { it == name }
    }

    @CacheEvict("test")
    fun addUser(name: String) {
        users.add(name)
    }

    @CacheEvict("test")
    fun removeUser(name: String) {
        users.remove(name)
    }
}