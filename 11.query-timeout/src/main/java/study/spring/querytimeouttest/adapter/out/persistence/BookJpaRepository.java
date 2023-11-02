package study.spring.querytimeouttest.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import study.spring.querytimeouttest.application.domain.Book;

public interface BookJpaRepository extends JpaRepository<Book, Long> {

}
