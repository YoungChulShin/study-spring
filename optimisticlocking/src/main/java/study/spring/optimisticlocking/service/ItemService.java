package study.spring.optimisticlocking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import study.spring.optimisticlocking.domain.Item;
import study.spring.optimisticlocking.domain.ItemRepository;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Retryable(
            maxAttempts = 5,
            backoff = @Backoff(delay = 50, multiplier = 2.0),
            include = {ObjectOptimisticLockingFailureException.class})
    public void incrementAmount(String id, int amount) {
        log.info("incrementAmount start : thread - " + Thread.currentThread().getId());
        Item item = itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        item.setAmount(item.getAmount() + amount);
        log.info("incrementAmount end : thread - " + Thread.currentThread().getId());
    }
}
