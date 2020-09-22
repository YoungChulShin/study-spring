package study.spring.jpalock;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Book findOne(String name) {

        Book findBook = bookRepository.findByName(name);

        return findBook;
    }
}
