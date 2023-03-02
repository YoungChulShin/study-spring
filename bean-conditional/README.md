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
class FalsePrinterConfiguration { }
```

### ConditionalOnProperty 
application property 값을 이용해서 활성화 여부를 설정해줄 수 있다.

`having values` 값의 설정에 따라서 조건이 다르게 적용되니 이 부분은 주의하자
- empty (default): false일 경우에만 적용이 안된다
- true: true일 경우에만 적용된다
- false: false일 경우에만 적용된다
- 특정 값: 특정 값일 경우에만 적용된다

샘플 코드
```properties
application.printer.boolean-printer=true
```
```kotlin
@Configuration
@ConditionalOnProperty(value = ["application.printer.boolean-printer"], havingValue = "true")
class TruePrinterConfiguration { }

@Configuration
@ConditionalOnProperty(value = ["application.printer.boolean-printer"], havingValue = "false")
class FalsePrinterConfiguration { }
```
