package study.spring.redistest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public String create(String id, String name, Student.Gender gender, int grade) {
        Student student = new Student(id, name, gender, grade);
        studentRepository.save(student);

        return student.getId();
    }

    public Student findOne(String id) {
        return studentRepository.findById(id).orElseThrow(() -> new InvalidParameterException());
    }

    public Student updateName(String id, String name) {
        Student findStudent = findOne(id);
        findStudent.updateName(name);

        studentRepository.save(findStudent);

        return findStudent;
    }
}
