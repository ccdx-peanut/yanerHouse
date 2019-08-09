package com.yaner.house.service.impl;


import com.yaner.house.bean.Admin;
import com.yaner.house.mapper.AdminMapper;
import com.yaner.house.mapper.UserMapper;
import com.yaner.house.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * @author:sfq
 * @create 2019-04-29  16:47
 **/
@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;
    @Value("${mail.fromMail.addr}")
    private String from;
    /**
     * 发送文本邮件
     * @param admin_name
     */
    @Override
    public String sendSimpleMail(String admin_name) {
        List<String> listNames = adminMapper.getAllAdminNames();
        if (listNames.contains(admin_name)){
            //说明传来的需要找回的用户名存在
            Admin admin = adminMapper.getAdminByAdminName(admin_name);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(admin.getAdmin_email());
            System.out.println("from:"+from+"---"+"接受的email:"+admin.getAdmin_email());
            message.setSubject("掩耳租房为您找回的密码");
            message.setText("您的员工密码为："+admin.getAdmin_password());
            try {
                mailSender.send(message);
                logger.info("简单邮件已经发送。");
                return "message001";//代表邮件已经发送
            } catch (Exception e) {
                logger.error("发送简单邮件时发生异常！", e);
                return "message002";//代表邮件未能成功发送
            }
        }else{
            return "message003";//代表找回的用户名不存在
        }

    }
    public String sendYzToUser(String user_name){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo("631708293@qq.com");
        String user_password = userMapper.getPasByName(user_name);
        System.out.println("user_password:"+user_password);
        message.setSubject("掩耳租房为您找回的密码:");
        message.setText("您的密码为："+user_password+"，感谢您使用掩耳租房。");
        try {
            mailSender.send(message);
            logger.info("邮件已经发送。");
            return "nextInfo002";//代表邮件已经发送
        } catch (Exception e) {
            logger.error("邮件发生异常！", e);
            return "nextInfo003";//代表邮件未能成功发送
        }
    }


}
