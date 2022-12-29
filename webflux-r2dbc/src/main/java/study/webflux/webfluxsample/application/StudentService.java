package study.webflux.webfluxsample.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import study.webflux.webfluxsample.domain.*;

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
                .flatMap(school -> {
                    var student = new Student(command.name(), command.age(), command.gender(), school);
                    return studentRepository.save(student)
                            .map(s -> StudentInfo.from(s, school.getName()));
                });
    }

    @Transactional(readOnly = true)
    public Flux<StudentInfo> findStudents() {
        return studentRepository.findStudentInfos();
    }

    @Transactional(readOnly = true)
    public Mono<StudentInfo> findStudent(Long studentId) {
        return studentRepository.findStudentInfoById(studentId);
    }
}
