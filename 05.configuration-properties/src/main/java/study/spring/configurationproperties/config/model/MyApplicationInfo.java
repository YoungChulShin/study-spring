package study.spring.configurationproperties.config.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "my-application")
public class MyApplicationInfo {

  private String name;
}
