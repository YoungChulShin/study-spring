# Bean Conditional
빈이 충돌할 때 어떻게 이를 피해서 사용할 수 있는 지를 알아본다

### Conditional
`Conditional`애노테이션을 이용해서 빈의 등록 여부를 설정한다
```kotlin
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
```

### Conditional + MetaAnnotation
Conditional이 같은 빈을 구분하기 위한 조건으로 사용된다면, MetaAnnotaion을 만들어서 Annotation의 Property를 이용해서 처리해줄 수 도 있다
```kotlin
// meta annotation
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Conditional(BooleanCondition::class)
annotation class BooleanConditional(
    val value: Boolean,
)

// condition
class BooleanCondition : Condition {

    override fun matches(context: ConditionContext, metadata: AnnotatedTypeMetadata): Boolean {
        val attributes = metadata.getAllAnnotationAttributes(BooleanConditional::class.java.name)
        return attributes?.get("value")?.get(0) as Boolean
    }
}

// 사용
@Configuration
@BooleanConditional(false)
class FalsePrinterConfiguration {
```

