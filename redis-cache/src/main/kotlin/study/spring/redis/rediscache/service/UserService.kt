package study.spring.redis.rediscache.service

import org.springframework.stereotype.Service

@Service
class UserService {

    companion object {
        private val users = mutableSetOf<String>()
    }

    fun findUsers(): Set<String> {
        Thread.sleep(1000)
        return users
    }

    fun addUser(name: String) {
        users.add(name)
    }

    fun removeUser(name: String) {
        users.remove(name)
    }
}