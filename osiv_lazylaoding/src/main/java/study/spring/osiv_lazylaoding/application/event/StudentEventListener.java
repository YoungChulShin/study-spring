package study.spring.osiv_lazylaoding.application.event;

import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class StudentEventListener {

  @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
  public void handleStudentEvent(StudentEvent event) {
    System.out.println("Student event. school: " + event.getStudent().getSchool().getName());
    throw new RuntimeException("test");
  }
}
