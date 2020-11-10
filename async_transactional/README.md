## 저장소 설명
Async와 Transactional을 함께 사용했을 때 데이터 테스트

## 확인 내용
Async 없이 2개의 서비스를 연동 실행
- 2번째 서비스에서 에러가 발생하면 Transaction이 이어지기 때문에 모두 롤백 됨

2번째 서비스가 Async, Transactional로 실행
- 2번째 서비스에서 에러가 발생해도 Transaction이 이어지기 않기 때문에 1번은 저장되고, 2번 내용만 롤백 됨

2번째 서비스가 Async로 실행
- 2번째 서비스에서 에러가 발생해도 Transaction이 이어지기 않기 때문에 1번은 저장됨
- 2번 서비스는 Transacti다on이 없기 때문에 저장 이후에 에러가 발생하면 저장 내용은 남아있