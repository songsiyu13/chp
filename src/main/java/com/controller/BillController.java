package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.security.*;

/**
 * Created by Song on 2017/3/17.
 */
@Controller
public class BillController {
    private Key publicKey;
    private Key privateKey;

    BillController()
    {
        try{
            Cipher cipher =Cipher.getInstance("RSA");
            //实例化Key
            KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");
            //获取一对钥匙
            KeyPair keyPair=keyPairGenerator.generateKeyPair();
            //获得公钥
            publicKey=keyPair.getPublic();
            //获得私钥
            privateKey=keyPair.getPrivate();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    @RequestMapping("/bill")
    @Transactional
    public String bill(@RequestParam(value = "password")byte[] pay)
    {
        try
        {
            Cipher cipher=Cipher.getInstance("RSA");
            //得到Key
            //用私钥去解密
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            String way = new String(cipher.doFinal(pay));

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping("/pay")
    @Transactional
    public String pay(HttpServletRequest request)
    {
        request.setAttribute("pk",publicKey);
        return "bill";
    }

}
