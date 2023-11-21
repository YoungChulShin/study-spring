# 저장소 설명
Spring에서 `Transactional` 애노테이션을 사용하면 내부적으로 어떻게 동작이 되는지 알아본다. 

# Transactional 애노테이션 특징
## 롤백
custom rollback 설정이 되어있지 않으면, `checked exceptions` 발생 시 롤백하지 않는다. 
```
If no custom rollback rules are configured in this annotation, the transaction will roll back on RuntimeException and Error but not on checked exceptions.
```

custom rollback은 아래 속성 정의를 통해서 처리 할 수 있다. 
1. rollbackFor
2. noRollbackFor
3. rollbackForClassName
4. noRollbackForClassName

## propagation 옵션. (=트랜잭션 전파)
REQUIRED(=Default)
- 현재 트랜잭션이 있으면 신규로 생성하지 않는다. 
- 없으면 새로 생성한다. 

SUPPORTS
- 이미 실행중인 트랜잭션이 있으면 해당 트랜잭션을 사용한다. 
- 없으면 트랜잭션 없이 실행한다. 

MANDATORY
- 이미 실행중인 트랜잭션이 있으면 해당 트랜잭션에 참여하고, 없으면 예외를 던진다. 

REQUIRES_NEW
- 항상 새로운 트랜잭션을 시작한다.

NOT_SUPPORTED
- 트랜잭션 없이 메서드를 실행한다. 

NEVER
- `MANDATORY`의 반대. 
- 트랜잭션이 있으면 예외가 발생한다.  

NESTED
- 이미 실행중인 트랜잭션이 있다면 중첩으로 실행된다. 없으면 새로운 트랜잭션을 시작한다. 
- 중첩 트랜잭션은 부모 트랜잭션이 롤백되면 자체적으로 롤백된다. 부모트랜잭션이 커밋되면 같이 커밋된다. 

## timeout 옵션
트랜잭션의 타임아웃을 설정할 수 있다. 기본 값은 시스템의 설정을 따라간다. 

## readonly 옵션
Reader DB로 트래픽을 전달할 수 있는 옵션.

실제로 트랜잭션을 처리하는 시스템으로 hint를 전달한다. writer로 요청이 간다고 해서 실패하지 않고, 트랜잭션 시스템이 힌트를 해석하지 못하면 무시된다. 

# Transactional 애노테이션 동작
## 클래스 호출 따라가보기
1. `CglibAopProxy` 클래스 호출.
   - `AopProxy` 인터페이스의 구현체.
   - 다른 구현체로는 `JdkDynamicAopProxy`가 있다.
2.`DynamicAdvisedInterceptor` 클래스의 `intercept` 메서드 호출.
   - pointcut
      ```
      org.springframework.transaction.interceptor.TransactionAttributeSourcePointcut: 
      org.springframework.transaction.annotation.AnnotationTransactionAttributeSource@74a8120c
      ```
   - AnnotationTransactionAttributeSource 클래스
      - Transactional 애노테이션을 찾아서 TransactionAttribute를 설정한다. 
3. intercept 메서드에서는 대상 클래스, 메서드를 기준으로 advisor를 가져온다.
   - 애노테이션이 1개만 설정되어있다면 Transactional에 대한 정보만 리턴된다. 
      ```java
      // TransactionInterceptor 객체 1개가 리턴된다. 
      List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
     
      ```
