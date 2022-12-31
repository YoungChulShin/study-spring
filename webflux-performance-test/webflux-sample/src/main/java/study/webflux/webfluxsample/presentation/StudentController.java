package study.webflux.webfluxsample.presentation;

import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/api/students")
    public Mono<StudentInfo> createStudent(@RequestBody CreateStudentDto request) {
        return studentService.createStudent(new CreateStudentCommand(
                request.name(),
                request.age(),
                request.gender(),
                request.schoolName()
        ));
    }

    @GetMapping("/api/students")
    public Mono<List<StudentInfo>> findStudents() {
        return studentService.findStudents().log().collectList();
    }

    @GetMapping("/api/schools/{schoolId}/students")
    public Mono<List<StudentInfo>> findStudentsBySchool(@PathVariable Long schoolId) {
        return studentService.findStudentsBySchool(schoolId).log().collectList();
    }
}
