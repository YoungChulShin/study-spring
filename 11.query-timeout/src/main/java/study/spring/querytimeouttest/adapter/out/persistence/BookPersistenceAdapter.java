package study.spring.querytimeouttest.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import study.spring.querytimeouttest.application.domain.Book;
import study.spring.querytimeouttest.application.port.out.BookWriter;

@Repository
@RequiredArgsConstructor
public class BookPersistenceAdapter implements BookWriter {

  private final BookJpaRepository repository;

  @Override
  public Book save(Book book) {
    repository.save(book);
    return book;
  }
}
