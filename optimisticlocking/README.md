### 저장소 내용
Optimistic Locking 과 동시성에 대한 테스트 코드

### 동시성 테스트 방법
`ExecutorService`를 이용해서 테스트 할 수만큼의 ThreadPool을 생성하고 동시에 실행하면 동시성에 대한 테스트를 할 수 있다.

검증 방법은 Optimistic Locking이 발생했을 때 재시도하는 코드를 작성해서 재시도가 동작하도록 시나리오를 구현하고, Assert 구문에서 해당 함수가 몇번 수행되었는지를 체크하면 된다. 예제에서는 Locking 발생할 수 있는 함수를 2개의 스레드에서 실행했고, 1회 Locking 발생하기 때문에 Assert 구분에서 총 3번의 함수가 실행되었는지 확인했다.  

대상 함수가 Async로 구현되어 있다면 `awaitTermination`을 이용해서 일정 시간동안 비동기 코드가 끝나는 것을 기다리고 결과를 체크하면 된다. 

### 참고 자료
https://blog.mimacom.com/testing-optimistic-locking-handling-spring-boot-jpa/