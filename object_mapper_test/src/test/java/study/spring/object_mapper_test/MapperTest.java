package study.spring.object_mapper_test;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapperTest {

  @Test
  void serialize_and_deserialize() throws JsonProcessingException {
    // given
    School school = new School("ycshin", "gangdong", 30);

    ObjectMapper objectMapper = new ObjectMapper();
    String valueAsString = objectMapper.writeValueAsString(school);

    // when
    School deserializedSchool = objectMapper.readValue(valueAsString, School.class);

    // then
    Assertions.assertThat(deserializedSchool).isEqualTo(school);
  }

  @Test
  void deserialize_unknownField_shouldThrowException() {
    // given
    ObjectMapper objectMapper = new ObjectMapper();
    String valueAsString = "{\"name\":\"ycshin\",\"location\":\"gangdong\",\"age\":30,\"type\":\"highschool\"}";

    // when
    Throwable thrown = catchThrowable(() -> objectMapper.readValue(valueAsString, School.class));

    // then
    Assertions.assertThat(thrown).isNotNull();
    Assertions.assertThat(thrown.getClass()).isEqualTo(UnrecognizedPropertyException.class);
  }

  @Test
  void deserialize_unknownField_withOptions_FAIL_ON_UNKNOWN_PROPERTIES_shouldNotThrowException() {
    // givenddd
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
    String valueAsString = "{\"name\":\"ycshin\",\"location\":\"gangdong\",\"age\":30,\"type\":\"highschool\"}";

    // when
    Throwable thrown = catchThrowable(() -> objectMapper.readValue(valueAsString, School.class));

    // then
    Assertions.assertThat(thrown).isNull();
  }

  @Test
  void serialize_and_deserialize_withType() throws JsonProcessingException {
    // given
    Student student = new Student("ycshin", Gender.MAIL);

    ObjectMapper objectMapper = new ObjectMapper();
    String valueAsString = objectMapper.writeValueAsString(student);

    // when
    Student serializedStudent = objectMapper.readValue(valueAsString, Student.class);

    // then
    Assertions.assertThat(serializedStudent).isEqualTo(student);
  }

  @Test
  void deserialize_unknownType_shouldThrowException() {
    // given
    ObjectMapper objectMapper = new ObjectMapper();
    String valueAsString = "{\"name\":\"ycshin\",\"gender\":30}";

    // when
    Throwable thrown = catchThrowable(() -> objectMapper.readValue(valueAsString, Student.class));

    // then
    Assertions.assertThat(thrown).isNotNull();
    Assertions.assertThat(thrown.getClass()).isEqualTo(InvalidFormatException.class);
  }

  @Test
  void deserialize_unknownType_with__options_shouldThrowException() throws JsonProcessingException {
    // given
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(READ_UNKNOWN_ENUM_VALUES_AS_NULL);
    String valueAsString = "{\"name\":\"ycshin\",\"gender\":30}";

    // when
    Student deserializedStudent = objectMapper.readValue(valueAsString, Student.class);

    // then
    Assertions.assertThat(deserializedStudent).isNotNull();
    Assertions.assertThat(deserializedStudent.getGender()).isEqualTo(null);
  }
}
