package study.spring.monitoring.java8gcmotniroing;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.spring.monitoring.java8gcmotniroing.model.Gender;
import study.spring.monitoring.java8gcmotniroing.model.Person;

@RestController
public class TestController {

  @PostMapping("/test/create-instance")
  public String createInstance(@RequestParam("times") int times) {
    List<Person> persons = new ArrayList<>();
    for (int i = 0; i < times; i++) {
      persons.add(new Person(
          "name " + i,
          i,
          i % 2 == 0 ? Gender.FEMALE : Gender.MALE));
    }

    return "OK";
  }
}
