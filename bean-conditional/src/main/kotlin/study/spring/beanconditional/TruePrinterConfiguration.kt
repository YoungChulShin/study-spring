package study.spring.beanconditional

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TruePrinterConfiguration {

    @Bean
    fun booleanPrinter(): BooleanPrinter = TruePrinter()
}