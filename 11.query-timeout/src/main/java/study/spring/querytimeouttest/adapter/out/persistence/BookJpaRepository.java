package study.spring.querytimeouttest.adapter.out.persistence;

import java.time.Instant;
import java.util.List;
import org.hibernate.grammars.hql.HqlParser.InstantiationTargetContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.spring.querytimeouttest.application.domain.Book;

public interface BookJpaRepository extends JpaRepository<Book, Long> {

  @Query("select b from Book b where b.publishedAt between '2023-11-02 10:09:46.133499' and '2023-11-02 10:09:55.099997'")
  List<Book> findBookByPublishedAtBetween();

}
