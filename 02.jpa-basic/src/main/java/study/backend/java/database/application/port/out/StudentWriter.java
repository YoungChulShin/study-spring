package study.backend.java.database.application.port.out;

import study.backend.java.database.domain.Student;

public interface StudentWriter {

  Student save(Student student);
}
