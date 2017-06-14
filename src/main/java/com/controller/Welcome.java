package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by 滩涂上的芦苇 on 2017/3/15.
 */
@Controller
public class Welcome {
    @RequestMapping("/")
    public String hello()
    {
        return "welcome";
    }

    @RequestMapping("/search")
    public String lucene()
    {
        return "lucene";
    }
}
