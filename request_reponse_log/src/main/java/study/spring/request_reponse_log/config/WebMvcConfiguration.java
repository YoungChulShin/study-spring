package study.spring.request_reponse_log.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import study.spring.request_reponse_log.config.interceptor.LogInterceptor;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfiguration implements WebMvcConfigurer {

    private final LogInterceptor auditLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(auditLogInterceptor)
                .addPathPatterns("/**");
    }

    @Bean
    public FilterRegistrationBean cacheServletRequestFilter() {
        CacheServletFilter cacheServletFilter = new CacheServletFilter();
    }
}
