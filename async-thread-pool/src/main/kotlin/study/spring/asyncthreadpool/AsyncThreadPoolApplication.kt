package study.spring.asyncthreadpool

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AsyncThreadPoolApplication

fun main(args: Array<String>) {
    runApplication<AsyncThreadPoolApplication>(*args)
}
