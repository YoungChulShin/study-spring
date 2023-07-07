package study.backend.java.database.application.port.out;

import study.backend.java.database.domain.School;

public interface SchoolPort {

  School save(School school);

  School findById(Long id);
}
