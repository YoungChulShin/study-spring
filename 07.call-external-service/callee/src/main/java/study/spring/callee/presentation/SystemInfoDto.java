package study.spring.callee.presentation;

public record SystemInfoDto(
    String name,
    String description,
    String version,
    String company,
    Integer createdYear) {

}
