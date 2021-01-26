package com.ty.java.jmockit.service;

import com.ty.java.jmockit.entity.Animal;

public interface OnlineShop {
    Boolean isLogin = null;

    String getOnlineShopStatus();
    Boolean isLogin();
    Animal getAnimal(String id);
}
