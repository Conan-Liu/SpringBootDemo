package com.conan.spring.controller;

import com.conan.spring.pojo.mybatis.UserTransaction;
import com.conan.spring.service.UserBatchService;
import com.conan.spring.service.UserTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "trans")
public class TransactionController {

    @Autowired
    private UserTransactionService userTransactionService;

    @Autowired
    private UserBatchService userBatchService;

    @RequestMapping(value = "/getuser")
    @ResponseBody
    public UserTransaction getUser(int id) {
        return userTransactionService.getUser(id);
    }

    @RequestMapping(value = "/insertuser")
    @ResponseBody
    public Map<String, Object> insertUser(String userName, String note) {
        UserTransaction user = new UserTransaction();
        user.setUserName(userName);
        user.setNote(note);
        // 返回插入条数，注意这条代码会插入mysql并返回数据库表中自增长id的值，然后回填user对象
        int update = userTransactionService.insertUser(user);
        Map<String, Object> result = new HashMap<>();
        result.put("success", update == 1);
        result.put("user", user);
        return result;
    }

    /**
     * 测试传播行为
     * url: http://localhost:7777/springboot/trans_insertusers?userName1=user_name_1&note1=note_1&userName2=user_name_2&note2=note_2
     */
    @RequestMapping(value = "/insertusers")
    @ResponseBody
    public Map<String, Object> insertUsers(String userName1, String note1, String userName2, String note2) {
        UserTransaction user1 = new UserTransaction();
        user1.setUserName(userName1);
        user1.setNote(note1);
        UserTransaction user2 = new UserTransaction();
        user2.setUserName(userName2);
        user2.setNote(note2);

        List<UserTransaction> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);

        int inserts = userBatchService.insertUsers(list);
        Map<String, Object> result = new HashMap<>();
        result.put("success", inserts > 0);
        result.put("user", list);
        return result;
    }
}
