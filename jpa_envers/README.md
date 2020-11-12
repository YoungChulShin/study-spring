## 목표
Hibernate Envers를 이용한 Audit Table 생성 및 조회 기능을 테스트 해본다

## 준비 사항
의존성
- 기본적인 의존성 외에, `implementation 'org.hibernate:hibernate-envers'`를 추가해준다

엔티티
- 간단하게 `Member`엔티티를 만들어서 테스트해본다
   ~~~java
   // 필드 정보
   private Long id;
   private String name;
   private int age;
   private String phone; // Not Audited
   ~~~
 
## 테스트 내용
Member엔티티에 Audited 적용
- member_aud, revinfo 테이블이 생성된다
- member_aud는 meber table의 P.K인 id와 revinfo table의 P.K인 rev로 구성된다

Insert 쿼리 반영 순서
1. member 테이블에 insert
2. revinfo 테이블에 insert
3. member_aud 테이블에 insert

Audit 반영
- Audit 대상 필드의 값이 변경되면 revinfo가 생성된다
- Audit 대상 필드의 값이 변경되면 revinfo는 생성되지 않는다

## Audit 조회
AuditReader를 이용해서 Revision 정보를 조회했다. 일단 간단하게 되는지만 확인을 했는데, 조금 더 알아봐도 될 것 같다.

아래와 같이 aud 테이블에서 조회하는 쿼리가 생성된다. 
~~~
  select
        member_aud0_.id as id1_1_,
        member_aud0_.rev as rev2_1_,
        member_aud0_.revtype as revtype3_1_,
        member_aud0_.age as age4_1_,
        member_aud0_.name as name5_1_ 
    from
        member_aud member_aud0_ 
    where
        member_aud0_.id=? 
    order by
        member_aud0_.rev asc
~~~ 


### 참고 사이트
- https://github.com/dlxotn216/spring-data-envers
- https://github.com/dlxotn216/spring-data-envers/blob/master/src/main/java/me/strong/report/domain/impl/CustomRevisionRepositoryImpl.java
- https://haviyj.tistory.com/40