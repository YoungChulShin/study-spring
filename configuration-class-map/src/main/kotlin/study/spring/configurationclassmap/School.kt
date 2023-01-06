package study.spring.configurationclassmap

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(value = "school.my")
class Schools (
    val elementrySchool: School,
    val middleSchool: School,
    val highSchool: School,
)

data class School(
    val name: String,
    val age: Int,
)