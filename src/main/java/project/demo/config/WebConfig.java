package project.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.demo.beans.UserBean;
import project.demo.interceptor.CheckLoginInterceptor;
import project.demo.interceptor.CheckWriterInterceptor;
import project.demo.interceptor.TopMenuInterceptor;
import project.demo.service.BoardService;
import project.demo.service.TopMenuService;

import javax.annotation.Resource;

//프로젝트 작업시 사용할 bean을 정의하는 클래스
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TopMenuService topMenuService;

    @Resource(name="loginUserBean")
    private UserBean loginUserBean;

    @Autowired
    private BoardService boardService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);

        TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService, loginUserBean);
        InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
        reg1.addPathPatterns("/**");

        CheckLoginInterceptor checkLoginInterceptor = new CheckLoginInterceptor(loginUserBean);
        InterceptorRegistration reg2 =  registry.addInterceptor(checkLoginInterceptor);
        reg2.addPathPatterns("/user/modify", "/user/logout", "/board/*");
        reg2.excludePathPatterns("/board/main");

        //글 작성자만 게시글 수정, 삭제하게하는 인터셉터
        CheckWriterInterceptor checkWriterInterceptor = new CheckWriterInterceptor(loginUserBean, boardService  );
        InterceptorRegistration reg3 = registry.addInterceptor(checkWriterInterceptor);
        reg3.addPathPatterns("/board/modify", "/board/delete");
    }

    @Bean("loginUserBean")
    //세션스코프: 브라우저가 최초의 요청 시키는 시점, 최초 요청발생할때 주입
    @SessionScope
    public UserBean loginUserBean(){
        return new UserBean();
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver(){
        return new StandardServletMultipartResolver();
    }

//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//        commonsMultipartResolver.setDefaultEncoding("UTF-8");
//        commonsMultipartResolver.setMaxUploadSizePerFile(5*1024*1024);
//        return  commonsMultipartResolver;
//    }


}
