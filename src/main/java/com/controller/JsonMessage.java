package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by 滩涂上的芦苇 on 2017/4/27.
 */
@Controller
public class JsonMessage {
    @ResponseBody
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public String getMessage(@RequestParam(value = "bookID") String bookID)
    {
        if(bookID.equals("111"))return "111 message";
        return "222 message";
    }
}
