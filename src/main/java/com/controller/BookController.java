package com.controller;

import com.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 滩涂上的芦苇 on 2017/4/6.
 */

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/queryBooks")
    public String queryBooks(HttpServletRequest request)
    {
        request.setAttribute("books",bookService.queryBooks());
        return "books";
    }

}
