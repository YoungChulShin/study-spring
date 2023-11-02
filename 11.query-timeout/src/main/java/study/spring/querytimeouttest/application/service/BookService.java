package study.spring.querytimeouttest.application.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.spring.querytimeouttest.application.domain.Book;
import study.spring.querytimeouttest.application.port.in.BookUseCase;
import study.spring.querytimeouttest.application.port.out.BookReader;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService implements BookUseCase {

  private final BookReader bookReader;

  @Override
  @Transactional(readOnly = true)
  public List<Book> findAllBooks() {
    log.info("조회를 시작합니다");
    try {
      bookReader.findAll();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    log.info("조회를 완료했습니다.");
    return new ArrayList<>();
  }
}
