package study.spring.beanconditional

import org.springframework.context.annotation.*

@Configuration
@BooleanConditional(true)
class TruePrinterConfiguration {

    @Bean
    fun booleanPrinter(): BooleanPrinter = TruePrinter()
}