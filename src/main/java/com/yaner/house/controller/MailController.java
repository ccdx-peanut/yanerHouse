package com.yaner.house.controller;

import com.yaner.house.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:sfq
 * @create 2019-04-29  16:53
 **/
@RestController
public class MailController {
    //自动注入mailService
    @Autowired
    private MailService mailService;

    //处理找回的用户名对应的密码
    @RequestMapping("/returnPasswoed")
    public String returnPasswoed(String admin_name){
        return mailService.sendSimpleMail(admin_name);
    }
}
