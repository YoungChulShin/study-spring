package study.spring.jpalock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    Book findByName(String name);
}
