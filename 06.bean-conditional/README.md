# 설명
스프링에서 Bean을 등록할 때, 같은 Bean을 2개 이상 등록하려고 하면 충돌이 발생한다. 이때 어떤 Bean을 등록할지 결정해야하는데, 그 방법을 알아본다. 

# 예제 시나리오
`print()` 라는 메서드를 가지는 `BooleanPrinter` 인터페이스가 있다. 
```java
public interface BooleanPrinterV1 {
  String print();
}
```

그리고 BooleanPrinter의 구현체가 2개 있다. 
1. `TruePrinter`
2. `FalsePrinter`

2개의 구현체를 Bean으로 등록할 때, 상황에 따라서 충돌이 발생하지 않으면서 등록하는 방법을 알아본다. 

# V1 - ConditionalOnProperty 
`ConditionalOnProperty` 애노테이션을 이용하면 application property 값을 이용해서 Bean 활성화 여부를 설정해줄 수 있다.

애노테이션의 `having value` 값에 따라서 조건이 다르게 적용되기 때문에 이 부분은 정확히 알고 있어야한다. 
- empty (default): false일 경우에만 적용이 안된다
- true: true일 경우에만 적용된다
- false: false일 경우에만 적용된다
- 특정 값: 특정 값일 경우에만 적용된다

샘플 코드
```yaml
application:
  printer:
    boolean-printer:
      v1: true
```
```java
@Configuration
@ConditionalOnProperty(value = "application.printer.boolean-printer.v1", havingValue = "true")
public class TruePrinterConfiguration { }

@Configuration
@ConditionalOnProperty(value = "application.printer.boolean-printer.v1", havingValue = "false")
public class FalsePrinterConfiguration { }
```
