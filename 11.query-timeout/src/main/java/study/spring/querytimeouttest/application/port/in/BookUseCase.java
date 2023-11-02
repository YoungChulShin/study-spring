package study.spring.querytimeouttest.application.port.in;

import java.util.List;
import study.spring.querytimeouttest.application.domain.Book;

public interface BookUseCase {

  List<Book> findAllBooks();

}
