package study.spring.asyncthreadpool.presentation

import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import study.spring.asyncthreadpool.service.TestService

var index = 1

@RestController
class TestController(val testService: TestService) {

    @PostMapping("/test/sleep/{sleepSecond}")
    fun testWithSleep(@PathVariable sleepSecond: Int): ResponseEntity<String> {
        val currentIndex = index
        index++
        val threadName = Thread.currentThread().name
        println("[메인-시작-${currentIndex}] ${threadName}")
        testService.testWithSleep(sleepSecond, currentIndex)
        return ResponseEntity.ok().build()
    }
}