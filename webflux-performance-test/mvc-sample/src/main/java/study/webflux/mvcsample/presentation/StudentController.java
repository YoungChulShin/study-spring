package study.webflux.mvcsample.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import study.webflux.mvcsample.application.CreateStudentCommand;
import study.webflux.mvcsample.application.StudentInfo;
import study.webflux.mvcsample.application.StudentService;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/api/students")
    public StudentInfo createStudent(@RequestBody CreateStudentDto request) {
        return studentService.createStudent(new CreateStudentCommand(
                request.name(),
                request.age(),
                request.gender(),
                request.schoolName()
        ));
    }

    @GetMapping("/api/students")
    public List<StudentInfo> findStudents() {
        return studentService.findStudents();
    }
}
