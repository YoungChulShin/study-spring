package study.backend.java.database.adapter.out.persistence;

import study.backend.java.database.application.port.in.model.StudentInfo;

interface StudentJpaRepositoryCustom {

  StudentInfo findStudent(Long id);

}
