package com.conan.shop.service;

public interface PurchaseService {

    boolean purchase(Integer userId, Integer productId, Integer quantity);

    boolean purchaseReentrant(Integer userId, Integer productId, Integer quantity);
}
