package study.spring.beanconditional

import org.springframework.context.annotation.Conditional

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Conditional(BooleanCondition::class)
annotation class BooleanConditional(
    val value: Boolean,
)