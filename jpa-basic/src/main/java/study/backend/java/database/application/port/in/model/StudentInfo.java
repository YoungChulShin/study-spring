package study.backend.java.database.application.port.in.model;

public record StudentInfo(
    Long id,
    String name,
    Integer age,
    Long schoolName) {
}
