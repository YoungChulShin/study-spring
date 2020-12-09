package study.spring.redistest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.spring.redistest.dto.CreateStudentRequest;
import study.spring.redistest.dto.FindStudentResponse;
import study.spring.redistest.dto.UpdateStudentNameRequest;
import study.spring.redistest.dto.UpdateStudentResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CreateStudentRequest request) {
        String savedId = studentService.create(request.getId(), request.getName(), request.getGender(), request.getGrade());

        return ResponseEntity.status(HttpStatus.CREATED).body(savedId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindStudentResponse> find(@PathVariable("id") String id) {
        Student findStudent = studentService.findOne(id);
        FindStudentResponse response = FindStudentResponse.from(findStudent);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/name")
    public ResponseEntity<UpdateStudentResponse> updateName(@RequestBody UpdateStudentNameRequest request) {
        Student updatedStudent = studentService.updateName(request.getId(), request.getName());
        UpdateStudentResponse response = UpdateStudentResponse.from(updatedStudent);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
