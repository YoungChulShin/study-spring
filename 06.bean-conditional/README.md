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

# V2 - Condition + Conditional
`Condition` 인터페이스의 구현체와 `Conditional` 애노테이션을 이용하면 Bean의 활성화 여부를 설정해줄 수 있다. 

## Condition 인터페이스
`matches()` 메서드를 1개 가지는 인터페이스다. 
```java
@FunctionalInterface
public interface Condition {
	boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata);
}
```

match라는 메서드의 값을 원하는 조건의 만족 여부로 설정할 수 있다. 여기서는 간단히 true/false로 했다. 
```java
public class FalsePrinterConditionV2 implements Condition {
  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    return true;
  }
}
```

## Conditional 애노테이션
`Conditional` 애노테이션은 `Condtion` 타입의 value를 값으로 입력받는다. 앞에서 구현한 Condition 클래스를 여기에서 사용할 수 있다. 

```java
@Configuration
@Conditional(FalsePrinterConditionV2.class) // Condition 클래스 사용
public class FalsePrinterConfigurationV2 {
  @Bean
  public BooleanPrinterV2 booleanPrinterV2() {
    return new FalsePrinterV2();
  }
}
```

# V3 - Condition + Conditional + MetaAnnotation
## MetaAnnotation
V2에서 Condition은 특정 값으로 반환 값을 선언해주었는데, 이 부분을 조금 더 유연하게 수정할 수 있다. 

Annotation을 이용해서 값을 입력 받고, 그 값을 Condition의 match 함수에서 사용해보자. 

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Conditional(BooleanCondition.class)
public @interface BooleanConditional {
  boolean value();
}
```
BooleanConditional 애노테이션은 value을 입력 값으로 가진다. 그리고 `Conditional` 애노테이션을 가지고 있다. 즉, 다른 클래스에서 Conditional 애노테이션을 대신해서 BooleanConditional을 사용할 수 있다. 

## Condition
`BooleanCondition` 은 matches 메서드에서 BooleanConditional의 `value` 값을 사용할 수 있다. 
```java
public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    // value 가져오기
    MultiValueMap<String, Object> attributes = metadata.getAllAnnotationAttributes(BooleanConditional.class.getName());
    if (attributes == null) {
        return false;
    }
    Object value = attributes.getFirst("value");
    if (value == null) {
        return false;
    }

    // value의 값에 따라서 빈 활성화 여부 설정
    return (Boolean)value;
}
```

## Configuration 클래스
Configuration 클래스는 BooleanCondition을 선언해서 사용한다. value에서 활성화 여부를 설정해준다. 
```java
@Configuration
@BooleanConditional(false)
public class TruePrinterConditionV3 {

  @Bean
  public BooleanPrinterV3 booleanPrinterV3() {
    return new TruePrinterV3();
  }
}
```