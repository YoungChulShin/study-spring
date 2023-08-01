package study.spring.configurationproperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackages = "study.spring.configurationproperties.config.model")
public class ConfigurationPropertiesApplication {

  public static void main(String[] args) {
    SpringApplication.run(ConfigurationPropertiesApplication.class, args);
  }

}
