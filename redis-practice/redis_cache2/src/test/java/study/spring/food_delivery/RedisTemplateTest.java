package study.spring.food_delivery;

import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

@SpringBootTest
public class RedisTemplateTest {

  @Autowired
  private RedisTemplate redisTemplate;

  @Test
  void testString() {
    ValueOperations valueOperations = redisTemplate.opsForValue();
    String key = "stringKey";

    valueOperations.set(key, "hello");
    String readValue = (String)valueOperations.get(key);

    System.out.println(readValue);
  }

  @Test
  void testSets() {
    SetOperations<String, String> setOperations = redisTemplate.opsForSet();
    String key = "setKey";

    setOperations.add(key, "1", "2", "3", "4");

    Set<String> members = setOperations.members(key);
    System.out.println("size: " + members.size());
    members.stream().forEach(x -> System.out.println(x));
  }

  @Test
  void testSortedSet() {
    ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
    String key = "user_score";

    zSetOperations.add(key, "user1", 99);
    zSetOperations.add(key, "user2", 98);
    Set<String> range = zSetOperations.range(key, 0, 100);
    Set<String> strings = zSetOperations.reverseRange(key, 0, 100);
  }
}
