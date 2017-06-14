package com.controller;

import com.entity.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 滩涂上的芦苇 on 2017/3/15.
 */

@Controller
public class UserController{
    @Autowired
    private UserService userService;

    @RequestMapping("/signup")
    public String signup(@RequestParam(value = "userID") String userID, @RequestParam(value = "userName") String userName, @RequestParam(value = "passWord") String passWord) {
        User user = new User();
        user.setPassWord(passWord);
        user.setUserName(userName);
        user.setUserID(userID);

        userService.add(user);
        return "success";
    }

    @RequestMapping("/signin")
    public String signin() {

        return "signin";
    }

    @RequestMapping("/message")
    public String message() {

        return "success";
    }

    @RequestMapping("/chat")
    public String chatRoom() {

        return "chatRoom";
    }
}
