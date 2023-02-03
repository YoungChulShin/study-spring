# Bean Conditional
빈이 충돌할 때 어떻게 이를 피해서 사용할 수 있는 지를 알아본다

### Conditional
`Conditional`애노테이션을 이용해서 빈의 등록 여부를 설정한다
```java
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

