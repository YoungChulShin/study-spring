package study.java.openapispecgeneratetest.service.exception;

public class MemberNotFoundException extends RuntimeException {

    private Long memberId;

    public MemberNotFoundException(String message, Long memberId) {
        super(message);
        this.memberId = memberId;
    }
}
