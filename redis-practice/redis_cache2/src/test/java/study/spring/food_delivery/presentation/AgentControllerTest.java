package study.spring.food_delivery.presentation;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import study.spring.food_delivery.presentation.model.RegisterAgentRequest;

@WebMvcTest(value = AgentController.class)
class AgentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  protected ObjectMapper objectMapper = new ObjectMapper();

  @Test
  void sut_correctly_save_agent() throws Exception {
    // given
    RegisterAgentRequest request = new RegisterAgentRequest("ycshin", 39);

    // when, then
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/agents")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request)))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$.id").value(1L));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidAgentSource")
  void sut_does_not_save_invalid_agent(String name, int age) throws Exception {
    // given
    RegisterAgentRequest request = new RegisterAgentRequest(name, age);

    // when, then
    mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/agents")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  private static Stream<Arguments> provideInvalidAgentSource() {
    return Stream.of(
        Arguments.of(null, 10),
        Arguments.of("", 10),
        Arguments.of(" ", 10),
        Arguments.of("testName", -1),
        Arguments.of("testName", 0)
    );
  }


}