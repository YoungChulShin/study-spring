package study.spring.configurationclassmap

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "student")
class Student(
    val name: String,
    val age: Int,
    val gender: String,
)