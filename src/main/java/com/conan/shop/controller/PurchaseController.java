package com.conan.shop.controller;

import com.conan.shop.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/shop")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    /**
     * 使用@RestController注解返回的字符串不能被视图解析，只能返回json
     * 所以使用ModelAndView来返回视图
     */
    @GetMapping("/shop")
    public ModelAndView testPage() {
        ModelAndView mav = new ModelAndView("shop");
        return mav;
    }

    @PostMapping("/purchase")
    public Map<String, Object> purchase(Integer userId, Integer productId, Integer quantity) {
        System.out.println("======================>"+userId+productId+quantity);
        boolean success = purchaseService.purchaseReentrant(userId, productId, quantity);
        String message = success ? "抢购成功" : "抢购失败";
        Map<String, Object> map = new HashMap<>(8);
        map.put("success", success);
        map.put("message", message);
        return map;
    }
}
