package study.spring.multithread.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.setScheduler(taskScheduler());
  }

  // Spring application이 종료될 땨 task executor가 종료되는 것을 보장하기 위함
  @Bean(destroyMethod="shutdown")
  public Executor taskScheduler() {
    return Executors.newScheduledThreadPool(10);
  }
}
