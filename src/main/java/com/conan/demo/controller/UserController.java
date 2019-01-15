package com.conan.demo.controller;

import com.conan.demo.model.User;
import com.conan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Administrator on 2019/1/2.
 */
// @RestController注解，相当于@Controller+@ResponseBody两个注解的结合, 就不能返回jsp,html页面，视图解析器无法解析jsp,html页面
@RestController
@RequestMapping("/user")
public class UserController {

//    @PathVaribale 获取url中的数据   /getUserInfo2/{userId}
//    @RequestParam 获取请求参数的值  url 中 ？ 后面的参数
//    @GetMapping 组合注解，是 @RequestMapping(method = RequestMethod.GET) 的缩写
//    @RequestBody 利用一个对象去获取前端传过来的数据

    @Autowired
    private UserService userService;

    @RequestMapping("/userInfo")
    public String userInfo() {
        return "UserInfo";
    }

    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(User user) {
        return userService.addUser(user);
    }

    // Get 请求
    @GetMapping("/getUserInfo1")
    public User getUser1(@RequestParam(name = "userId") Integer userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/getUserInfo2/{userId}")
    public User getUser2(@PathVariable(name = "userId") Integer userId) {
        return userService.getUser(userId);
    }

    @GetMapping("/getUserInfo3")
    public User getUser3(HttpServletRequest request) {
        return userService.getUser(Integer.valueOf(request.getParameter("userId")));
    }

    @GetMapping(value = "/myheader")
    public void getHeader(@RequestHeader(name = "myheader") String myheader) {
        System.out.println("myheader = " + myheader);
    }

    @PostMapping("/showInfo")
    public void showInfo(@RequestBody User user) {
        System.out.println(user);
    }

    @RequestMapping("/userId")
    public Object login(@PathVariable("id") Integer id) {
        return userService.getUser(id);
    }

    @RequestMapping("/userAll")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }
}