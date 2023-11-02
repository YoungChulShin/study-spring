package study.spring.querytimeouttest.application.port.out;

import java.util.List;
import study.spring.querytimeouttest.application.domain.Book;

public interface BookReader {

  List<Book> findAll();
}
