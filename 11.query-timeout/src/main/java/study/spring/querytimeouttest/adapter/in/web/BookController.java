package study.spring.querytimeouttest.adapter.in.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import study.spring.querytimeouttest.application.domain.Book;
import study.spring.querytimeouttest.application.port.in.BookUseCase;

@RestController
@RequiredArgsConstructor
public class BookController {

  private final BookUseCase bookUseCase;

  @GetMapping("/books")
  public List<Book> findAllBooks() {
    return bookUseCase.findAllBooks();
  }

}
