package project.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.demo.beans.UserBean;
import project.demo.interceptor.TopMenuInterceptor;
import project.demo.service.TopMenuService;

import javax.annotation.Resource;

//프로젝트 작업시 사용할 bean을 정의하는 클래스
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TopMenuService topMenuService;

    @Resource(name="loginUserBean")
    private UserBean loginUserBean;

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new TopMenuInterceptor())
//                .order(1)
//                .addPathPatterns("/**");
//    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);

        TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService, loginUserBean);

        InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
        reg1.addPathPatterns("/**");
    }

    @Bean("loginUserBean")
    //세션스코프: 브라우저가 최초의 요청 시키는 시점, 최초 요청발생할때 주입
    @SessionScope
    public UserBean loginUserBean(){
        return new UserBean();
    }
}
