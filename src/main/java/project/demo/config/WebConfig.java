package project.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import project.demo.beans.UserBean;

//프로젝트 작업시 사용할 bean을 정의하는 클래스
@Configuration
public class WebConfig {

    @Bean("loginUserBean")
    //세션스코프: 브라우저가 최초의 요청 시키는 시점, 최초 요청발생할때 주입
    @SessionScope
    public UserBean loginUserBean(){
        return new UserBean();
    }
}
