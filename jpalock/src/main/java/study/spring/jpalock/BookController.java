package study.spring.jpalock;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(path = "/book")
@RestController
public class BookController {

    private final BookService bookService;

    @GetMapping(path = "/{name}")
    public Book findBook(@PathVariable String name) {

        return bookService.findOne(name);
    }

}
