package project.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import project.demo.beans.BoardInfoBean;
import project.demo.service.TopMenuService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class TopMenuInterceptor implements HandlerInterceptor {

    @Autowired
    private TopMenuService topMenuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<BoardInfoBean>topMenuList = topMenuService.getTopMenuList();
        request.setAttribute("topMenuList", topMenuList);

        return true;
    }
}
