package wj.intercept;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import wj.entity.dataBaseMapping.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginIntercept implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        if (uri.indexOf("/login")>0){
            return true;
        }
        if (uri.indexOf("/register")>0){
            return true;
        }
        HttpSession session = httpServletRequest.getSession();
        User u = (User) session.getAttribute("User");
        if (u!=null){
            return true;
        }

        httpServletRequest.setAttribute("result","您还没有登录请先登录");
        httpServletRequest.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(httpServletRequest,httpServletResponse);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
