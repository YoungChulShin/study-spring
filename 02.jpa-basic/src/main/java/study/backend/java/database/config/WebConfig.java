package study.backend.java.database.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import study.backend.java.database.config.log.CacheServletFilter;
import study.backend.java.database.config.log.RequestResponseLogInterceptor;
import study.backend.java.database.config.log.RequestResponseLogProperties;
import study.backend.java.database.config.log.RequestIdInterceptor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final RequestResponseLogProperties logProperties;
  private final RequestResponseLogInterceptor logInterceptor;
  private final RequestIdInterceptor requestIdInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(requestIdInterceptor);
    registry.addInterceptor(logInterceptor)
        .addPathPatterns("/**")
        .excludePathPatterns(logProperties.getExcludePatternArray());
  }

  @Bean
  public FilterRegistrationBean cacheServletFilter() {
    CacheServletFilter filter = new CacheServletFilter(
        this.logProperties.getExcludeMethodArray(),
        this.logProperties.getExcludePatternArray());

    FilterRegistrationBean registrationBean = new FilterRegistrationBean(filter);
    registrationBean.addUrlPatterns("/*");
    registrationBean.setOrder(Integer.MAX_VALUE);

    return registrationBean;
  }
}
