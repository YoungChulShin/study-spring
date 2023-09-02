package study.spring.delivery.domain.persistentevent;

public enum PersistentEventStatus {

  CREATED,    // 이벤트 생성
  PUBLISHED,  // 이벤트 발행
  COMPLETED,  // 발행 후, 응답 콜백을 받아서 완료
}
