package study.webflux.webfluxsample.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import study.webflux.webfluxsample.application.CreateStudentCommand;
import study.webflux.webfluxsample.application.StudentService;
import study.webflux.webfluxsample.domain.StudentInfo;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/api/reactive/students")
    public Mono<StudentInfo> createStudent(@RequestBody CreateStudentDto request) {
        return studentService.createStudent(new CreateStudentCommand(
                request.name(),
                request.age(),
                request.gender(),
                request.schoolName()
        ));
    }

    @GetMapping("/api/reactive/students")
    public Mono<List<StudentInfo>> findStudents() {
        return studentService.findStudents().log().collectList();
    }
}
