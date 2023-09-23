package study.backend.java.database.common.response;

import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;

public record PageDto(
    int page,
    int size,
    long totalElements,
    int totalPages) {

  public PageDto(@Nonnull Page result) {
    this(
        result.getNumber(),
        result.getSize(),
        result.getTotalElements(),
        result.getTotalPages());
  }
}
