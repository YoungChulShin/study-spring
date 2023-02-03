package study.spring.beanconditional

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PrinterController(
    private val printer: BooleanPrinter,
) {

    @GetMapping("/printer")
    fun print() {
        printer.print()
    }
}