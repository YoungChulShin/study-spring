package study.spring.rediscache.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import study.spring.rediscache.domain.Student;

import javax.persistence.EntityManager;


@Repository
@RequiredArgsConstructor
public class StudentRepository {

    private final EntityManager entityManager;

    public Long save(Student student) {
        entityManager.persist(student);

        return student.getId();
    }

    @Cacheable(value = "StudentName", key = "'StudentName:' + #name")
    public Student findByName(String name) {
        queryDelay(5000);
        return entityManager.createQuery("select s from Student s where s.name = :name", Student.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    private void queryDelay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
