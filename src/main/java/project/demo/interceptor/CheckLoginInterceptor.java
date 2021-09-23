package project.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import project.demo.beans.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckLoginInterceptor implements HandlerInterceptor {

    //인터셉터는 bean주입 못받아서 생성자로 받아야한다.

    private UserBean loginUserBean;

    public CheckLoginInterceptor(UserBean loginUserBean){
        this.loginUserBean = loginUserBean;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //로그인 안했다면
        if (loginUserBean.isUserLogin() == false) {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/user/not_login");
            return false;
        }
        //로그인했다면 다음단계(컨트롤러)로 진행.
        return true;
    }
}
