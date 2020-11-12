## 저장소 설명
Async와 Transactional을 함께 사용했을 때 데이터 테스트

## Async - Transactional 확인 내용
Async 없이 2개의 서비스를 연동 실행
- 2번째 서비스에서 에러가 발생하면 Transaction이 이어지기 때문에 모두 롤백 됨

2번째 서비스가 Async, Transactional로 실행
- 2번째 서비스에서 에러가 발생해도 Transaction이 이어지기 않기 때문에 1번은 저장되고, 2번 내용만 롤백 됨

2번째 서비스가 Async로 실행
- 2번째 서비스에서 에러가 발생해도 Transaction이 이어지기 않기 때문에 1번은 저장됨
- 2번 서비스는 Transacti다on이 없기 때문에 저장 이후에 에러가 발생하면 저장 내용은 남아있다. 

## Event - Async - Transactional 확인 내용
아무것도 없이 Event Listener만 사용
- Thread 는 동일한 것을 사용한다. 데이트 흐름도 동기로 동작한다. 
- 이벤트리스너가 2개 있으면 2개가 순차적으로 수행된다. 

2개중 한개의 EventListener에만 Async를 사용
- 동기로 실행되는 리스너가 완료되는 시점에 비동기 함수 실행 및 호출 클래스에 리턴이 발생한다
- 비동기로 실행된 리스너는 다른 스레드에서 실행된다
- 동기 리스너에서 에러가 발생하면 호출한 클래스와 스레드가 같기 때문에 호출한 클래스의 트랜잭션도 롤백된다.

2개의 EventListener를 모두 Async로 변경하면
- 각각의 스레드가 할당되어 실행된다
- 리스너에서 에러가 발생해도 별도의 트랜잭션에서 실행되기 때문에 호출한 클래스의 트랜잭션은 정상 종료 된다. 

### 아래부터는 1개의 EventListener로만 테스트
async, transaction을 설정
- 비동기이기 때문에 별도의 트랜잭션을 생성
- 중간에 에러가 발생하면 비동기 함수 수행 범위 내에서 롤백이 수행된다

## TransactionEventListener 사용
TransactionEventListener는 호출한 클래스의 트랜잭션이 완료된 이후에 호출되는 함수이기 때문에 TransactionEventListener를 사용하는 곳에서 단순히 Transactional만 선언하면 트랜잭션이 동작하지 않는다. 

이 경우 리스너에서 새로운 트랜잭션을 만들어줘야하는데 `@Async` 를 사용하거나 '@Transactional(propagation = Requires_New)'로 새로운 트랜잭션을 선언해주는 방법이 있다.  


### 기타 - Transactional 선언할 때 Propagation 설정
기본 값은 Required인데, `Requires_New`로 설정해줄 수 있다. 이렇게 설정하면 `Requires_New`로 설정된 범위 내에서 별도의 트랜잭션이 만들어진다. 




