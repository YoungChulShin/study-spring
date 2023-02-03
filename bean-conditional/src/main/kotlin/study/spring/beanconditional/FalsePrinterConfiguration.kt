package study.spring.beanconditional

import org.springframework.context.annotation.*
import org.springframework.core.type.AnnotatedTypeMetadata

@Configuration
@Conditional(FalsePrinterCondition::class)
class FalsePrinterConfiguration {

    @Bean
    fun falsePrinter(): BooleanPrinter = FalsePrinter()
}

class FalsePrinterCondition: Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        return false
    }

}