package study.spring.beanconditional

import org.springframework.context.annotation.*
import org.springframework.core.type.AnnotatedTypeMetadata

@Configuration
class FalsePrinterConfiguration {

    @Bean
    fun falsePrinter(): BooleanPrinter = FalsePrinter()
}
