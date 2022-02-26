package study.spring.osiv_lazylaoding.application;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.osiv_lazylaoding.application.model.CreateStudentCommand;
import study.spring.osiv_lazylaoding.application.model.StudentInfo;
import study.spring.osiv_lazylaoding.domain.School;
import study.spring.osiv_lazylaoding.domain.SchoolRepository;
import study.spring.osiv_lazylaoding.domain.Student;
import study.spring.osiv_lazylaoding.domain.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final StudentRepository studentRepository;
  private final SchoolRepository schoolRepository;

  @Lazy
  @Autowired
  private StudentService self;

  @Transactional
  public Long createStudent(CreateStudentCommand command) {
    School school = schoolRepository.findOneByName(command.getSchoolName())
        .orElseThrow(IllegalArgumentException::new);

    Student initStudent = new Student(command.getName(), command.getAge());
    Student student = studentRepository.save(initStudent);
    school.registerStudent(student);

    return student.getId();
  }

  @Transactional(readOnly = true)
  public StudentInfo findStudent(Long studentId) {
    Student student = studentRepository.findById(studentId)
        .orElse(null);

    // self.selfTestMethod(studentId, student);

    return student != null
        ? new StudentInfo(student.getName(), student.getAge(), student.getSchoolName())
        : null;
  }

  @Transactional
  public StudentInfo selfTestMethod(Long studentId, Student student) {
    Student selfStudent = studentRepository.findById(studentId)
        .orElse(null);

    studentRepository.save(selfStudent);

    return selfStudent != null
        ? new StudentInfo(selfStudent.getName(), selfStudent.getAge(), selfStudent.getSchoolName())
        : null;
  }
}
