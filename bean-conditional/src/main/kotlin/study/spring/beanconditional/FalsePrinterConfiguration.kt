package study.spring.beanconditional

import org.springframework.context.annotation.*

@Configuration
@BooleanConditional(false)
class FalsePrinterConfiguration {

    @Bean
    fun falsePrinter(): BooleanPrinter = FalsePrinter()
}