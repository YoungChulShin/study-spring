package study.spring.caller.application.model;

public record SystemInfo(
    String name,
    String description,
    String version,
    String company,
    String country,
    Integer createdYear) {

}
