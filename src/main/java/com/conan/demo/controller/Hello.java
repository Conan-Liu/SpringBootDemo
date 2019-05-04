package com.conan.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Conan on 2019/5/4.
 */
@RestController
public class Hello {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello1() {
        return "Hello World...";
    }

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public ModelAndView firstEChartsDemo(){
        return new ModelAndView("test");
    }

    @RequestMapping(value = "/course_clickcount", method = RequestMethod.GET)
    public ModelAndView courseClickCountStat(){
        return new ModelAndView("demo");
    }
}