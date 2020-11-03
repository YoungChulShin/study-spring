package study.spring.optimisticlocking.domain;

import lombok.Getter;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Version
    private Long version;
}
