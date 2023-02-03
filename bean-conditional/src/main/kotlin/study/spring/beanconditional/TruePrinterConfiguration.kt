package study.spring.beanconditional

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.*

@Configuration
@ConditionalOnProperty(value = ["application.printer.boolean-printer"], havingValue = "false")
class TruePrinterConfiguration {

    @Bean
    fun booleanPrinter(): BooleanPrinter = TruePrinter()
}