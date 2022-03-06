# 설명
WrapperClass를 이용해서 circuit breaker를 구현해 봅니다. 

# 테스트 방법
### 정상 실행
1. callee 프로젝트를 실행합니다. 
2. caller 프로젝트를 실행합니다. 
3. caller에서 'localhost:5000/greeting/hello/{name}' 을 호출해서 정상 응답이 오는지 확인합니다. 

### circuit breaker 동작
1. callee의 controller에서 주석 처리된 `Thread.sleep(10000)` 코드의 주석을 해제합니다. 
2. caller의 readTimeout은 5초이기 때문에 readTimeout이 발생합니다. 
3. 3번 연속 실패하면 circuit breaker가 동작합니다.
   - circuit open 이후에 20초가 지나면 half open 상태로 api를 다시호출합니다. 

### 조건 변경
- caller 프로젝트에서 `HelloApiClientCircuitWrapper` 클래스에서 설정 값들을 변경합니다. 
- 기본 설정 값
   - circuit breaker 동작을 위한 최대 실패 횟수: 3회
   - half open 재시도 시간: 20초
