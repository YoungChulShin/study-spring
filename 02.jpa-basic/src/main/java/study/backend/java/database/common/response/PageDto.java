package study.backend.java.database.common.response;

public record PageDto(
    int page,
    int size,
    int totalElements,
    int totalPages) {

}
