package com.conan.spring.component;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器拦截处理器，先执行preHandle -> 处理器（Controller方法）-> postHandle -> afterCompletion
 */
public class InterceptorExp implements HandlerInterceptor{

    // 处理器执行前方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("处理器前方法");
        // 返回true，不会拦截后续处理
        return true;
    }

    // 处理器执行后方法
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("处理器后方法");
    }

    // 处理器完成后方法
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("处理器完成方法");
    }
}
