package study.spring.querytimeouttest.application.port.out;

import study.spring.querytimeouttest.application.domain.Book;

public interface BookWriter {

  Book save(Book book);

}
