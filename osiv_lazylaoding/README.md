# 저장소 설명
osiv와 JPA lazy loading 테스트를 해보기 위한 저장소입니다

# 확인 내용 메모
controller에서 조회한 entity를 transactional이 있는 service로 넘기더라도 해당 엔티티는 영속상태가 아니기 때문에 lazy 조회가 되지 않는다. 

### Transaction
transaction이 readonly라면 변경 감지가 동작하지 않아서 엔티티를 업데이트했더라도 반영되지 않는다. 

transaction -> transaction 으로 호출하면 상위 transaction을 그대로 가져간다.
- 여가서 엔티티가 변경되면 상위 트랜잭션이 커밋될 때 반영된다. 
- 상위 트랜잭션이 readonly이고, 하위 트랜잭션이 아니라도 상위 트랜잭션을 가져가기 때문에 하위 트랜잭션에서 저장을 수행하면 에러가 발생한다. 

transaction -> self.transaction 으로 호출하면 2개의 트랜잭션이 만들어진다. 
- 이때 상위 트랜잭션에서 조회한 엔티티의 경우 self 내에서 변경이 생기더라도, 상위 트랜잭션이 커밋될 때 변경이 발생한다.
- entity는 넘길 수 있고, lazy loading이 동작한다
- self 라도 requries_new가 아니면 기존 트랜잭션을 이용한다
- requries_new일 때 Self 트랜잭션 내에서 조회 및 변경된 엔티티는 self 트랜잭션이 끝날때 반영된다. 

같은 클래스에서 transaction 없음 -> Transaction 으로 호출하면 트랜잭션이 동작하지 않는다. self로 해야지 동작한다.  
- transaction 없음에서 조회한 엔티티를 Transaction으로 넘겨도 영속성 컨텍스트가 동작하지 않아서 lazy loading 에러가 발생한다. 

### TransactionalEventListener
TransactionalEventListener로 이벤트를 처리하면 EventHandler에서 LazyLoading을 사용할 수 있다

TransactionalEventListener의 Phase
- beforeCommit: 커밋 전에 이벤트가 동작. 이벤트에서 에러가 발생하면 커밋이 되지 않았기 때문에 내용이 반영되지 않는다
- afterCommit: 기본 값. 커밋 이후에 이벤트가 동작. 이벤트에서 에러가 발생해도 커밋이 되었기 때문에 내용이 반영된다