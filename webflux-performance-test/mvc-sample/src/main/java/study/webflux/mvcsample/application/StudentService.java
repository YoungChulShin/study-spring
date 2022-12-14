package study.webflux.mvcsample.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.webflux.mvcsample.domain.School;
import study.webflux.mvcsample.domain.SchoolRepository;
import study.webflux.mvcsample.domain.Student;
import study.webflux.mvcsample.domain.StudentRepository;

import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

    public StudentService(
            StudentRepository studentRepository,
            SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }

    public StudentInfo createStudent(CreateStudentCommand command) {
        School school = schoolRepository.findByName(command.schoolName())
                .orElseThrow(() -> new RuntimeException("학교 정보를 찾을 수 없습니다"));

        Student student = new Student(
                command.name(),
                command.age(),
                command.gender(),
                school
        );
        studentRepository.save(student);

        return StudentInfo.from(student);
    }

    @Transactional(readOnly = true)
    public List<StudentInfo> findStudents() {
        return studentRepository.findAll().stream().map(StudentInfo::from).toList();
    }
}
