package study.spring.rediscache.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.rediscache.domain.Student;
import study.spring.rediscache.infra.StudentRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    @Transactional
    public Long save(String name, int age) {
        Student student = new Student(name, age);
        repository.save(student);

        return student.getId();
    }

    public Student findByName(String name) {
        return repository.findByName(name);
    }
}
