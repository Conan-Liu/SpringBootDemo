package com.conan.spring.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用@ControllerAdvice + @ExceptionHandler注解实现全局异常处理
 * 捕捉Sevice，Dao，Controller异常
 */
// 可以通过参数basePackages指定需要捕捉异常的包
@ControllerAdvice(basePackages = "com.conan.spring")
public class GlobalExceptionHandler1 {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler1.class);

    // 这里只捕捉ArithmeticException，只有捕捉到这个异常，才会进入该方法，执行逻辑
    // 不同的异常可以精确一点，对应不同的全局处理
    @ExceptionHandler(value = ArithmeticException.class)
    public Map<String, Object> arithmeticExceptionHandler(HttpServletRequest req, Exception e) {
        LOG.error("GlobalExceptionHandler ===> {}", e.getMessage());
        e.printStackTrace();

        String exceptionClassName = e.getClass().getSimpleName();
        LOG.info("GlobalExceptionHandler ===> {}", exceptionClassName);

        Map<String, Object> errorMap = new HashMap<>((int) (4 / 0.75));
        errorMap.put("stackTrace", e.getStackTrace());
        errorMap.put("errorMessage", e.getMessage());
        errorMap.put("url", req.getRequestURL());
        return errorMap;
    }

    // 捕获全局的NullPointException，统一处理
    @ExceptionHandler(value = NullPointerException.class)
    public Map<String, Object> nullPointerExceptionHandler(HttpServletRequest req, Exception e) {
        LOG.error("GlobalExceptionHandler ===> {}", e.getMessage());
        e.printStackTrace();

        String exceptionClassName = e.getClass().getSimpleName();
        LOG.info("GlobalExceptionHandler ===> {}", exceptionClassName);

        Map<String, Object> errorMap = new HashMap<>((int) (4 / 0.75));
        errorMap.put("stackTrace", e.getStackTrace());
        errorMap.put("errorMessage", e.getMessage());
        errorMap.put("url", req.getRequestURL());
        return errorMap;
    }
}
