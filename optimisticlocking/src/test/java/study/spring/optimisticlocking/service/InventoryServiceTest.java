package study.spring.optimisticlocking.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.transaction.annotation.Transactional;
import study.spring.optimisticlocking.domain.Item;
import study.spring.optimisticlocking.domain.ItemRepository;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class InventoryServiceTest {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ItemRepository itemRepository;

    @SpyBean
    private ItemService itemService;

    private final List<Integer> itemAmounts = Arrays.asList(10, 5);

    @Test
    void shouldIncrementItemAmount_withoutConcurrency() {
        // given
        final Item srcItem = itemRepository.save(new Item());
        assertEquals(0, srcItem.getVersion());

        // when
        for (final int amount : itemAmounts) {
            inventoryService.incrementProductAmount(srcItem.getId(), amount);
        }

        // then
        final Item item = itemRepository.findById(srcItem.getId()).get();

        assertAll(  // 테스트 항목이 많으면 이렇게 해도 괜찮을 듯 하네
                () -> assertEquals(2, item.getVersion()),
                () -> assertEquals(15, item.getAmount()),
                () -> verify(itemService, times(2)).incrementAmount(anyString(), anyInt())
        );
    }

    @Test
    void shouldIncrementItemAmount_withOptimisticLockingHandling() throws InterruptedException {
        // given
        final Item srcItem = itemRepository.save(new Item());
        assertEquals(0, srcItem.getVersion());

        // when
        final ExecutorService executor = Executors.newFixedThreadPool(itemAmounts.size());

        for (int amount : itemAmounts) {
            executor.execute(() -> inventoryService.incrementProductAmount(srcItem.getId(), amount));
        }

        executor.awaitTermination(5, TimeUnit.SECONDS);
        executor.shutdown();

        // then
        final Item item = itemRepository.findById(srcItem.getId()).get();

        assertAll(
                () -> assertEquals(2, item.getVersion()),
                () -> assertEquals(15, item.getAmount()),
                () -> verify(itemService, times(3)).incrementAmount(anyString(), anyInt())
        );
    }
}