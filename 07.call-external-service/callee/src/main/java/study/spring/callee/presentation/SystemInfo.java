package study.spring.callee.presentation;

public record SystemInfo(
    String name,
    String description,
    String version,
    String company,
    Integer createdYear,
    Integer createdMonth) {

}
