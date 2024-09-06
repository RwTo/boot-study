package com.rwto.spring.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author renmw
 * @create 2024/9/6 12:24
 * 只能拦截controller
 * 属于springmvc
 **/
public class MyInterceptor implements HandlerInterceptor {
    /**
     * 请求执行前
     * @param request
     * @param response
     * @param handler
     * @return true 放行，false 拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ip = request.getRemoteHost();
        HttpSession session = request.getSession();
        Integer count = (Integer) session.getAttribute("count");
        count = null == count ? 1 : count+1;
        System.out.println("interceptor ip:" + ip + " count:"+count);
        session.setAttribute("count",count);
        return true;
    }

    /**
     * 请求执行后
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 视图渲染后
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
