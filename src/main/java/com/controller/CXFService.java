package com.controller;

import com.entity.User;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 滩涂上的芦苇 on 2017/5/19.
 */
@WebService(serviceName="CXFServices")
public class CXFService {
    public List<User> query(String name){
        System.out.println("已经进入服务器:"+name);
        List<User> temp = new ArrayList<User>();
        User a = new User();
        a.setUserName("aaa");
        temp.add(a);
        return temp;
    }
}
