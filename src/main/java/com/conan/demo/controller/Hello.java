package com.conan.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class Hello {

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView firstEChartsDemo() {
        return new ModelAndView("test");
    }

    @RequestMapping(value = "/course_clickcount", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView courseClickCountStat() {
        return new ModelAndView("demo");
    }

    /**
     * 测试全局异常捕获机制
     */
    @RequestMapping(value = "/testException", method = RequestMethod.GET)
    @ResponseBody
    public String testException(int i) {
        // 此处不要try - catch, 不然异常不能抛出， 不能被全局异常机制给捕获
        return "success + " + 1 / i;
    }

    @RequestMapping(value = "/testMap")
    @ResponseBody
    public Map<String, String> testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        return map;
    }

    @RequestMapping(value = "/index")
    public String indexController() {
        return "index";
    }

}