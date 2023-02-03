package study.spring.beanconditional

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.*

@Configuration
@ConditionalOnProperty(value = ["application.printer.boolean-printer"], havingValue = "true")
class FalsePrinterConfiguration {

    @Bean
    fun falsePrinter(): BooleanPrinter = FalsePrinter()
}