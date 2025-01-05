package todoapp.todoapp_develop.global.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import todoapp.todoapp_develop.auth.filter.AuthFilter;
import todoapp.todoapp_develop.user.repository.UserRepository;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public AuthFilter authFilter(UserRepository userRepository) {
        return new AuthFilter(userRepository);
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistration(AuthFilter authFilter) {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(authFilter);
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.setOrder(1);    // 필터 체인에서의 순서 지정
        return registrationBean;
    }
}