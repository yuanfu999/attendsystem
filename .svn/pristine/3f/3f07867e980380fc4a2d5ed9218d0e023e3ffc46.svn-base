package com.aboluo.attend.interceptor;
import com.aboluo.attend.pojo.Emp;
import com.aboluo.attend.service.EmpService;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class Interceptor implements HandlerInterceptor {
    private EmpService empService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle){
        HttpSession session = request.getSession();
        String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() +request.getContextPath()+ "/";
        Emp session_emp =(Emp) session.getAttribute("session_emp");
        //int level = (Integer) session.getAttribute("level");
        if(session_emp!=null){
            return true;
        }else {
            //没有登录转向登录页面
            try {
                // request.getRequestDispatcher("../index.jsp").forward(request,response);
                response.sendRedirect("../index.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        //HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object handler, Exception e) throws Exception {

    }
}
