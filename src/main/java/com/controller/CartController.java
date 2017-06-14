package com.controller;

import com.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Song on 2017/3/17.
 */

@Controller
@Scope("session")
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping("/addCart")
    public String addCart(@RequestParam(value = "bookID")int bookID,@RequestParam(value = "number")int number)
    {
        cartService.put(bookID,number);

        return "success";
    }

    @RequestMapping("/showCart")
    public String showCart(HttpServletRequest request)
    {
        request.setAttribute("cart",cartService.get());
        return "cart";
    }
}
