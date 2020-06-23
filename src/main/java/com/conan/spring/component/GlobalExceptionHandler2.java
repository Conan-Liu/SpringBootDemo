package com.conan.spring.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * 使用{@link HandlerExceptionResolver}接口来全局处理异常
 */
public class GlobalExceptionHandler2 implements HandlerExceptionResolver {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler2.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, @Nullable Object handler, Exception ex) {
        // 先处理UndeclaredThrowableException
        Exception exception=new Exception();
        if(ex instanceof UndeclaredThrowableException){
            exception=(Exception) ((UndeclaredThrowableException) ex).getUndeclaredThrowable();
        }else if(ex instanceof ArithmeticException){
            // code
        }else if(ex instanceof NullPointerException){

        }

        // 根据不同的异常，不同的处理方法
        String simpleName = ex.getClass().getSimpleName();
        return null;
    }
}
