package study.spring.querytimeouttest.config;

import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import study.spring.querytimeouttest.application.domain.Book;
import study.spring.querytimeouttest.application.port.out.BookWriter;

@Configuration
public class DataInitializer implements ApplicationRunner {

  private final BookWriter bookWriter;
  private final boolean createInitData;

  public DataInitializer(
      BookWriter bookWriter,
      @Value("${application.create-init-data}") boolean createInitData) {
    this.bookWriter = bookWriter;
    this.createInitData = createInitData;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    if (!createInitData) {
      return;
    }

    for (int i = 0; i < 200000000; i++) {
      Book book = new Book("test-book-" + i, "test-author-" + i, Instant.now().minusSeconds(i));
      bookWriter.save(book);
    }
  }
}
