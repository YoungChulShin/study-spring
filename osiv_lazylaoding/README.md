# 저장소 설명
osiv와 JPA lazy loading 테스트를 해보기 위한 저장소입니다

# 확인 내용 메모
controller에서 조회한 entity를 transactional이 있는 service로 넘기더라도 해당 엔티티는 영속상태가 아니기 때문에 lazy 조회가 되지 않는다. 

