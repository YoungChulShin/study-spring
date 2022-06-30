package study.spring.object_mapper_test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapperTest {

  @Test
  void serializeAndDeserialize() throws JsonProcessingException {
    // given
    School school = new School("ycshin", "gangdong", 30);

    ObjectMapper objectMapper = new ObjectMapper();
    String valueAsString = objectMapper.writeValueAsString(school);

    // when
    School deserializedSchool = objectMapper.readValue(valueAsString, School.class);

    // then
    Assertions.assertThat(deserializedSchool).isEqualTo(school);
  }
}
