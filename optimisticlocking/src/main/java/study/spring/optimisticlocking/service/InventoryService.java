package study.spring.optimisticlocking.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class InventoryService {

    private final ItemService itemService;

    @Transactional(readOnly = true)
    public void incrementProductAmount(String itemId, int amount) {
        log.info("incrementProductAmount start : thread - " + Thread.currentThread().getId());
        try {
            itemService.incrementAmount(itemId, amount);
        } catch (ObjectOptimisticLockingFailureException e) {
            log.info("Error catch : thread - " + Thread.currentThread().getId());
        }

        log.info("incrementProductAmount end : thread - " + Thread.currentThread().getId());
    }
}
