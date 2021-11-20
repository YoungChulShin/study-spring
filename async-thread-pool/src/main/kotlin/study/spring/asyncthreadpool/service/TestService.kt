package study.spring.asyncthreadpool.service

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class TestService {

    @Async
    fun testWithSleep(sleep: Int = 5000, index: Int) {
        val threadName = Thread.currentThread().name
        println("[서브-시작-${index}] ${threadName}")
        Thread.sleep(sleep.toLong())
    }
}