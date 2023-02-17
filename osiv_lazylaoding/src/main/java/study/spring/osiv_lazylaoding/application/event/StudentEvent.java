package study.spring.osiv_lazylaoding.application.event;

import java.util.EventObject;
import lombok.Getter;
import study.spring.osiv_lazylaoding.domain.Student;

@Getter
public class StudentEvent extends EventObject {

  private final Student student;

  public StudentEvent(Student student) {
    super(student);
    this.student = student;
  }
}
