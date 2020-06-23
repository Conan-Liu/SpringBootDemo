package com.conan.spring.aop;

import com.conan.spring.ioc.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "aop")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/print")
    @ResponseBody
    public User printUser(Long id, String userName, String note) {
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setNote(note);
        System.out.println("**********");
        userService.printUser(user);
        System.out.println("____________________");
        return user;
    }

    /**
     * 测试多个切面
     */
    @RequestMapping("/manyaspects")
    public String manyAspects(){
        userService.manyAspects();
        return "manyAspects";
    }
}
