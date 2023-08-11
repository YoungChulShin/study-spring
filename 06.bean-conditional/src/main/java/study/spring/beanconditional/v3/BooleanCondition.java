package study.spring.beanconditional.v3;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

public class BooleanCondition implements Condition {

  @Override
  public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
    MultiValueMap<String, Object> attributes =
        metadata.getAllAnnotationAttributes(BooleanConditional.class.getName());
    if (attributes == null) {
      return false;
    }
    Object value = attributes.getFirst("value");
    if (value == null) {
      return false;
    }

    return (Boolean)value;
  }
}
