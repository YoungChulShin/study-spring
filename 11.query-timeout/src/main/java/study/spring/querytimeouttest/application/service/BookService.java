package study.spring.querytimeouttest.application.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import study.spring.querytimeouttest.application.domain.Book;
import study.spring.querytimeouttest.application.port.in.BookUseCase;
import study.spring.querytimeouttest.application.port.out.BookReader;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService implements BookUseCase {

  private final BookReader bookReader;

  @Override
  public List<Book> findAllBooks() {
    log.info("조회를 시작합니다");
    bookReader.findAll();
    log.info("조회를 완료했습니다.");
    return new ArrayList<>();
  }
}
