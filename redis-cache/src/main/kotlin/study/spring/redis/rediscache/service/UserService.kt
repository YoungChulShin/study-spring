package study.spring.redis.rediscache.service

import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.stereotype.Service
import study.spring.redis.rediscache.config.CacheKey

@Service
class UserService(
    private val cacheManager: RedisCacheManager,
) {

    companion object {
        private val users = mutableSetOf<String>()
    }

    @Cacheable(
        value = [CacheKey.FIND_USERS],
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

    @CacheEvict(
        value = [CacheKey.FIND_USERS],
        allEntries = true,
    )
    fun addUser(name: String) {
        users.add(name)
    }

    @Caching(
        evict = [
            CacheEvict(
                value = [CacheKey.FIND_USERS],
                allEntries = true,
            ),
            CacheEvict(
                value = [CacheKey.FIND_USER],
                key = "#name",
            )
        ]
    )
    fun removeUser(name: String) {
        users.remove(name)
    }
}