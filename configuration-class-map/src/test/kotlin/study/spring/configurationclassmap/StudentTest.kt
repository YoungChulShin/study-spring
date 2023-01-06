package study.spring.configurationclassmap

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StudentTest(
    @Autowired val student: Student
) {
    @Test
    fun studentBinding() {
        Assertions.assertThat(student.name).isEqualTo("myname")
        Assertions.assertThat(student.age).isEqualTo(10)
        Assertions.assertThat(student.gender).isEqualTo("male")
    }
}