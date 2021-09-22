package project.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
        import org.springframework.web.servlet.HandlerInterceptor;
        import project.demo.beans.BoardInfoBean;
        import project.demo.beans.UserBean;
        import project.demo.service.TopMenuService;

        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.util.List;

@Slf4j
public class TopMenuInterceptor implements HandlerInterceptor {

    private TopMenuService topMenuService;
    private UserBean loginUserBean;

    public TopMenuInterceptor(TopMenuService topMenuService, UserBean loginUserBean) {
        this.topMenuService = topMenuService;
        this.loginUserBean = loginUserBean;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<BoardInfoBean>topMenuList = topMenuService.getTopMenuList();
        request.setAttribute("topMenuList", topMenuList);
        request.setAttribute("loginUserBean", loginUserBean);

        log.info("loginUserBean={}", loginUserBean);

        return true;
    }
}
