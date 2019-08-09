package com.yaner.house.service;

import org.springframework.stereotype.Service;

/**
 * @author:sfq
 * @create 2019-04-29  16:44
 **/
@Service
public interface MailService {
    public String sendSimpleMail(String admin_name);
    String sendYzToUser(String user_name);
}
