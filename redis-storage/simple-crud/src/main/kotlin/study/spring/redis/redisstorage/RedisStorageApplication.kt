package study.spring.redis.redisstorage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RedisStorageApplication

fun main(args: Array<String>) {
    runApplication<RedisStorageApplication>(*args)
}
