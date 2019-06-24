package com.conan.demo.error;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Conan on 2019/6/23.
 */
@ControllerAdvice(basePackages = "com.conan.demo.controller")
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String, Object> errorResult() {
        Map<String, Object> errMap = new HashMap();
        errMap.put("errorCode", "500");
        errMap.put("errorMsg", "系统错误");
        return errMap;
    }
}