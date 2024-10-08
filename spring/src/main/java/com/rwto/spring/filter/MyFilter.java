package com.rwto.spring.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author renmw
 * @create 2024/9/6 12:28
 * 可以拦截静态资源
 * 属于servlet容器
 * 基于责任链模式，通过函数回调的方式，实现的函数增强
 * 通过文件名的顺序进行控制执行顺序
 **/
@WebFilter(urlPatterns = "/hello/filter")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String ip = request.getRemoteHost();
        HttpSession session = request.getSession();
        Integer count = (Integer) session.getAttribute("count");
        count = null == count ? 1 : count+1;
        System.out.println("filter ip:" + ip + " count:"+count);
        session.setAttribute("count",count);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
