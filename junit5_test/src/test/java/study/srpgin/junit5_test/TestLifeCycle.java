package study.srpgin.junit5_test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public interface TestLifeCycle {

    @BeforeAll
    default void beforeAllTests() {
        System.out.println("Test Start=================");
    }

    @AfterAll
    default void afterAllTests() {
        System.out.println("Test end=================");
    }
}
