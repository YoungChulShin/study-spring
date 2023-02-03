package study.spring.beanconditional

import org.springframework.context.annotation.Condition
import org.springframework.context.annotation.ConditionContext
import org.springframework.core.type.AnnotatedTypeMetadata

class BooleanCondition : Condition {

    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        val attributes = metadata.getAllAnnotationAttributes(BooleanConditional::class.java.name)
        return attributes?.get("value")?.get(0) as Boolean
    }
}