package study.webflux.webfluxsample.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;
import study.webflux.webfluxsample.domain.*;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

    public StudentService(StudentRepository studentRepository, SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }

    @Transactional
    public Mono<StudentInfo> createStudent(CreateStudentCommand command) {
        return schoolRepository.findByName(command.schoolName())
                .switchIfEmpty(Mono.error(new RuntimeException("학교 정보를 찾을 수 없습니다")))
                .map(school -> Tuples.of(school, new Student(
                        command.name(),
                        command.age(),
                        command.gender(),
                        school.getId()
                )))
                .flatMap(t -> studentRepository.save(t.getT2()))
                .map(t -> StudentInfo.from(t, new School("test")));
    }

    @Transactional(readOnly = true)
    public Flux<StudentInfo> findStudents() {
        return studentRepository.findAllWithSchool();
    }

}
