package com.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 滩涂上的芦苇 on 2017/5/22.
 */
public class MyAdvice {
    // before通知方法
    public void beforeShow() {
        try
        {
            File file = new File("log.txt");
            if(!file.exists())
            {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getName(),true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            StringBuffer data = new StringBuffer("Login welcome page :");
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String now = dateFormat.format(date);
            data.append(now);
            data.append("\n");
            bufferedWriter.write(data.toString());
            bufferedWriter.close();
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    // after通知方法
    public void afterShow() {
        System.out.println(getClass().toString() + " after show");
    }

    // afterReturn通知方法
    public void afterReturnShow() {
        System.out.println(getClass().toString() + " afterReturn show");
    }

    // afterThrowing通知方法
    public void afterThrowingShow() {
        System.out.println(getClass().toString() + " afterThrowing show");
    }

    // around通知方法
    public void aroundShow(ProceedingJoinPoint jpoint) {

        try {
            System.out.println(getClass().toString() + " around before show");
            // 执行目标对象的连接点处的方法
            jpoint.proceed();
            System.out.println(getClass().toString() + " around after show");
        } catch (Throwable e) {
            System.out.println(getClass().toString() + " around afterThrowing show");
        }
    }
}
