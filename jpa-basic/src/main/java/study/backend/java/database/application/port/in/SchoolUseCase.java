package study.backend.java.database.application.port.in;

import study.backend.java.database.domain.School;

public interface SchoolUseCase {

  Long addSchool(String name);

  School getSchool(Long id);
}
