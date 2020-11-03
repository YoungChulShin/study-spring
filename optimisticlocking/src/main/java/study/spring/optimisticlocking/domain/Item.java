package study.spring.optimisticlocking.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@EqualsAndHashCode(callSuper = false)
public class Item extends BaseEntity{

    @Id
    private String id = UUID.randomUUID().toString();

    private int amount = 0;

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
