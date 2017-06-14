package com.controller;

import com.entity.User;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 滩涂上的芦苇 on 2017/5/22.
 */

@Component("restSample")
public class RestTest1 {
    @GET
    @Path("/order")
    @Produces({ MediaType.APPLICATION_JSON })

    public List<User> query(String name){
        //System.out.println("已经进入服务器:"+name);
        List<User> temp = new ArrayList<User>();
        User a = new User();
        a.setUserName("aaa");
        temp.add(a);
        return temp;
    }
}
