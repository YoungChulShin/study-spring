package study.webflux.webfluxsample.application;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;
import study.webflux.webfluxsample.domain.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

    public StudentService(StudentRepository studentRepository, SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }

    public Mono<StudentInfo> createStudent(CreateStudentCommand command) {
        return schoolRepository.findByName(command.schoolName())
                .switchIfEmpty(Mono.error(new RuntimeException("학교 정보를 찾을 수 없습니다")))
                .map(school -> new Student(
                        command.name(),
                        command.age(),
                        command.gender(),
                        school.getId()))
                .flatMap(studentRepository::save)
                .map(student -> StudentInfo.from(student, new School("test")));
    }

    public Flux<StudentInfo> findStudents() {
        return studentRepository.findAllWithSchool();
    }

}
