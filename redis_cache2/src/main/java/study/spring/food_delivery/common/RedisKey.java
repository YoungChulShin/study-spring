package study.spring.food_delivery.common;

public class RedisKey {

  public static final int DEFAULT_TTL_SEC = 60;

  public static final String KEY_AGENT_LOCATION = "agent_location";
  public static final int KEY_AGENT_LOCATION_TTL_SEC = 20;


  private RedisKey() { }
}
