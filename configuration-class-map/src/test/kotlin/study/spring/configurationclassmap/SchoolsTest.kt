package study.spring.configurationclassmap

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class SchoolsTest(
    @Autowired val schools: Schools,
) {

    @Test
    fun schoolsBinding() {
        Assertions.assertThat(schools.elementarySchool.name).isEqualTo("고덕초등학교")
        Assertions.assertThat(schools.elementarySchool.age).isEqualTo(10)

        Assertions.assertThat(schools.middleSchool.name).isEqualTo("명일중학교")
        Assertions.assertThat(schools.middleSchool.age).isEqualTo(5)

        Assertions.assertThat(schools.highSchool.name).isEqualTo("고덕고등학교")
        Assertions.assertThat(schools.highSchool.age).isEqualTo(90)
    }
}