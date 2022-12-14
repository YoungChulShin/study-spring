package study.webflux.mvcsample.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import study.webflux.mvcsample.domain.*;

@Component
@AllArgsConstructor
public class InitConfig implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

    @Override
    public void run(String... args) throws Exception {
        // create school
        for (int i = 0; i < 10; i++) {
            School school = new School("test " + i);
            schoolRepository.save(school);

            for (int j = 0; j < 100; j++) {
                Student student = new Student(
                        "name" + i + j,
                        j,
                        Gender.MALE,
                        school
                );
                studentRepository.save(student);
            }
        }

    }
}
