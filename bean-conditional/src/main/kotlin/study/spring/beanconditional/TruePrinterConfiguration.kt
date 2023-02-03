package study.spring.beanconditional

import org.springframework.context.annotation.*
import org.springframework.core.type.AnnotatedTypeMetadata

@Configuration
@Conditional(TruePrinterCondition::class)
class TruePrinterConfiguration {

    @Bean
    fun booleanPrinter(): BooleanPrinter = TruePrinter()
}

class TruePrinterCondition: Condition {
    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        return true
    }

}