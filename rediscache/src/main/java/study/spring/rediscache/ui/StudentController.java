package study.spring.rediscache.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.spring.rediscache.domain.Student;
import study.spring.rediscache.service.StudentService;
import study.spring.rediscache.ui.dto.CreateStudentRequest;
import study.spring.rediscache.ui.dto.StudentResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Long> save(@RequestBody CreateStudentRequest request) {
        Long savedId = studentService.save(request.getName(), request.getAge());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

    @GetMapping("/{name}")
    public ResponseEntity<StudentResponse> find(@PathVariable("name") String name) {
        Student findStudent = studentService.findByName(name);
        StudentResponse response = StudentResponse.from(findStudent);

        return ResponseEntity.ok(response);
    }
}
