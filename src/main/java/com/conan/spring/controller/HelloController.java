package com.conan.spring.controller;

import com.alibaba.fastjson.JSONObject;
import com.conan.spring.pojo.ValidatePojo;
import com.conan.spring.pojo.mybatis.User;
import com.conan.spring.pojo.mybatis.UserTransaction;
import com.conan.spring.service.HelloService;
import com.conan.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @GetMapping(value = "/sayhello")
    @ResponseBody
    public String sayHello(String name, @RequestParam(value = "ageParam", required = true) int age) {
        return "Get Mapping HelloService... " + name + "\t" + age;
    }

    @RequestMapping(value = "/sayhello1", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello1() {
        return "HelloService World...";
    }

    // 传递数组参数，参数值逗号分割
    // 请求url: /hello/arr?intArr=10,20,1,32&strArr=aa,bb,cc,ad,xy
    @RequestMapping(value = "/arr")
    @ResponseBody
    public Map<String, Object> requestArray(int[] intArr, String[] strArr) {
        Map<String, Object> map = new HashMap<>();
        map.put("intArr", intArr);
        map.put("strArr", strArr);
        return map;
    }

    // rest风格的url传递参数，需要使用@PathVariable注解来获取url的参数值
    @RequestMapping(value = "/restparam/{id}")
    @ResponseBody
    public String restParam(@PathVariable("id") int id) {
        return "id = " + id;
    }

    /**
     * 数据验证
     * 请求参数是json格式，contentType:"application/json"
     *
     * @RequestBody 代表接收一个json参数，然后自动注入ValidatePojo对象中
     * Errors 错误信息，SpringMVC框架通过pojo验证后，自动填充，直接使用
     * Post请求
     * {
     * "date": "2019-08-09",
     * "doubleValue": 9999.9,
     * "range": 100,
     * "email": "email@qq",
     * "size": "ad345"
     * }
     */
    @RequestMapping(value = "/validatepojo")
    @ResponseBody
    public Map<String, Object> validatePojo(@Valid @RequestBody ValidatePojo vp, Errors errors) {
        Map<String, Object> errMap = new HashMap<>();
        // 获取错误列表
        List<ObjectError> errs = errors.getAllErrors();
        String key = null;
        String msg = null;
        for (ObjectError err : errs) {
            if (err instanceof FieldError) {
                key = ((FieldError) err).getField();
            } else {
                key = err.getObjectName();
            }
            msg = err.getDefaultMessage();
            errMap.put(key, msg);
        }
        return errMap;
    }

    @Autowired
    private UserService userService;

    // 测试Model接口
    @GetMapping("/model")
    public String userModel(int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        // 这里返回字符串，SpringMVC会自动穿件ModelAndView来绑定视图名称
        return "index";
    }

    // 测试ModelAndView
    @GetMapping("/modelandview")
    public ModelAndView userModelAndView(int id, ModelAndView mav) {
        User user = userService.getUser(id);
        mav.addObject("user", user);
        mav.setViewName("index");
        return mav;
    }

    // 测试拦截器
    @RequestMapping(value = "/interceptor/start")
    @ResponseBody
    public String interceptor() {
        System.out.println("执行处理器逻辑");
        return "interceptor ...";
    }

    @Autowired
    private HelloService helloService;

    /**
     * 全局异常处理
     */
    @GetMapping(value = "/globalexception1")
    @ResponseBody
    public String globalException1() {
        System.out.println(1 / 0);
        return "globalException1方法执行...";
    }

    @GetMapping(value = "/globalexception2")
    @ResponseBody
    public int globalException2() {
        System.out.println("globalException2方法执行...");
        return helloService.globalException();
    }

    /**
     * 请求参数是json或者对象
     */
    @RequestMapping(value = "/jsonobject", method = RequestMethod.POST)
    @ResponseBody
    public String jsonObject(@RequestBody JSONObject user) {
        Set<String> set = user.keySet();
        for (String s : set) {
            System.out.println(s + "\t" + user.getString(s));
        }
        System.out.println(" ========= " + user);
        return "success";
    }

    // 按照变量名称注入pojo对象，大小写没关系
    @RequestMapping(value = "/pojoobject", method = RequestMethod.POST)
    @ResponseBody
    public String pojoObject(@RequestBody UserTransaction user) {
        System.out.println(" ========= " + user);
        return "success";
    }

}
