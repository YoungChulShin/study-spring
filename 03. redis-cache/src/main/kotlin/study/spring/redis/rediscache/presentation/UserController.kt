package study.spring.redis.rediscache.presentation

import org.springframework.web.bind.annotation.*
import study.spring.redis.rediscache.service.UserService

@RestController
@RequestMapping(value = ["/users"])
class UserController(
    private val userService: UserService,
) {

    @GetMapping
    fun findUsers() = userService.findUsers()

    @GetMapping("/{name}")
    fun findUser(@PathVariable name: String) = userService.findUser(name)

    @PostMapping
    fun addUser(name: String) = userService.addUser(name)

    @DeleteMapping
    fun deleteUser(name: String) = userService.removeUser(name)
}