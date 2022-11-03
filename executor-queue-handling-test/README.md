# 저장소 설명
Executor를 실행할 때, QueueSize를 지정할 경우 Queue Size가 다 차면 에러가 발생할 수 있다. 

문제가 발생했을 때 대응방법을 검토한다

# 참고 문서
https://www.baeldung.com/java-rejectedexecutionhandler

# 대응방법
1. 기본 제공되는 Policy를 활용하는 방법
   - AbortPolicy: TaskRejectedException throw
   - DiscardPolicy: Task를 무시한다
   - DiscardOldestPolicy: 가장 오래된 Task를 제외한다
   - CallerRunsPolicy: Caller Thread에서 실행한다
2. CustomPolicy를 활용하는 방법
   - RejectedExecutionHandler 인터페이스를 구현한다

# 샘플 코드
시나리오
- pool size 1개, queue size 1개로 세팅
- 3번의 요청을 보낸다. 3번째 요청은 queue가 가득찬 상태에서 실행된다
API 
- `work/default`: 기본 구현. TaskRejectedException throw
- `work/discard`: Discard Policy 사용. 마지막 요청은 무시된다
- `work/log`: Custom Policy 사용. Queue가 가득차면 로그를 기록한다