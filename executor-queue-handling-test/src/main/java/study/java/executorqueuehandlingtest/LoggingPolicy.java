package study.java.executorqueuehandlingtest;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class LoggingPolicy implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        // 여기에 커스텀 핸들링 코드를 넣으면 될 것 같다
        // 링크예시에서는 max connection을 올려주는 방법을 사용하던데 이건 줄일 수가 없으니 쓰면 안될 것 같고
        // 예외 로깅이나 알람이 좋을 것 같은데..
        System.out.println("에러. 큐가 가득찼습니다");
    }
}
