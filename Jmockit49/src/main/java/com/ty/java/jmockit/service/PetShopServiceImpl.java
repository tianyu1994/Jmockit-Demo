package com.ty.java.jmockit.service;

public class PetShopServiceImpl {
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

        return checkFlag;
    }
}
