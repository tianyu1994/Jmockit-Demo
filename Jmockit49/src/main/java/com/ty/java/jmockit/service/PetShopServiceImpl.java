package com.ty.java.jmockit.service;

import com.ty.java.jmockit.entity.Animal;
import com.ty.java.jmockit.entity.PetShop;

import java.util.ArrayList;
import java.util.List;

public class PetShopServiceImpl {
    List<PetShop> petShopList;

    MailService mailService;
    UserService userService;

    public Boolean check() throws Exception {
        boolean checkFlag = true;
        if (!mailService.sendMail()) {
            checkFlag = false;
        }

        if (!userService.check()) {
            checkFlag = false;
        }

        System.out.println(petShopList);

        return checkFlag;
    }
}
