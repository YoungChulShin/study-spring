package study.spring.osiv_lazylaoding.application;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.spring.osiv_lazylaoding.application.event.StudentEvent;
import study.spring.osiv_lazylaoding.application.model.CreateStudentCommand;
import study.spring.osiv_lazylaoding.application.model.StudentInfo;
import study.spring.osiv_lazylaoding.domain.School;
import study.spring.osiv_lazylaoding.domain.SchoolRepository;
import study.spring.osiv_lazylaoding.domain.Student;
import study.spring.osiv_lazylaoding.domain.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService {

  private final ApplicationEventPublisher eventPublisher;
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

  @Transactional
  public StudentInfo findStudent(Long studentId) {
    Student student = studentRepository.findById(studentId)
        .orElse(null);

    return student != null
        ? new StudentInfo(student)
        : null;
  }

  @Transactional
  public String updateStudentName(Long studentId, String name) {
    Student student = studentRepository.findById(studentId).orElse(null);
    if (student == null) {
      return null;
    }

    student.updateName(name);
    eventPublisher.publishEvent(new StudentEvent(student));

    return student.getName();
  }

  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public Student selfTestMethod(Long studentId, Student student) {
    Student selfStudent = studentRepository.findById(studentId)
        .orElse(null);

    return student;
  }
}
