package study.spring.caller.application.port.in.model;

public record SystemInfo(
    String name,
    String description,
    String version,
    String company,
    String country,
    Integer createdYear) {

}
