### 저장소 설명
JPA에서 PESSIMISTIC Lock을 테스트하기 위한 저장소

참고 문서: [Link](https://www.baeldung.com/jpa-pessimistic-locking)

### 확인 내용
- PESSIMISTIC Lock이 설정되어 있으면 Repository를 조회하는 시점부터 테이블에 Data를 Update, Delete 할 수 없다

### 기타
- Transaction을 생성할 때 readonly 를 적용하면 안된다



