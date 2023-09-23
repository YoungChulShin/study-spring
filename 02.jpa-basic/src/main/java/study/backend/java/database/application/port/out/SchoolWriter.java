package study.backend.java.database.application.port.out;

import study.backend.java.database.domain.School;

public interface SchoolWriter {

  School save(School school);

}
