package study.java.executorqueuehandlingtest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkController {

    private final WorkService worker;

    public WorkController(WorkService worker) {
        this.worker = worker;
    }

    @PostMapping("work/default")
    public void workDefault() {
        worker.work(1);
        worker.work(2);
        worker.work(3); // 예외 발생 예상
    }

    @PostMapping("work/discard")
    public void workDiscard() {
        worker.workDiscard(1);
        worker.workDiscard(2);
        worker.workDiscard(3); // 무시 예상
    }

    @PostMapping("work/log")
    public void workLog() {
        worker.workCustom(1);
        worker.workCustom(2);
        worker.workCustom(3); // 로그 기록 예상
    }
}
