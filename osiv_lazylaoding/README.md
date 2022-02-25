# 저장소 설명
osiv와 JPA lazy loading 테스트를 해보기 위한 저장소입니다

# 확인 내용 메모
controller에서 조회한 entity를 transactional이 있는 service로 넘기더라도 해당 엔티티는 영속상태가 아니기 때문에 lazy 조회가 되지 않는다. 

transaction이 readonly라면 변경 감지가 동작하지 않아서 엔티티를 업데이트했더라도 반영되지 않는다. 

transaction -> transaction 으로 호출하면 상위 transaction을 그대로 가져간다.
- 여가서 엔티티가 변경되면 상위 트랜잭션이 커밋될 때 반영된다. 

transaction -> self.transaction 으로 호출하면 2개의 트랜잭션이 만들어진다. 
- 이때 상위 트랜잭션에서 조회한 엔티티의 경우 self 내에서 변경이 생기더라도, 상위 트랜잭션이 커밋될 때 변경이 발생한다. 
- self 라도 requries_new가 아니면 기존 트랜잭션을 이용한다
- requries_new일 때 Self 트랜잭션 내에서 조회 및 변경된 엔티티는 self 트랜잭션이 끝날때 반영된다. 

