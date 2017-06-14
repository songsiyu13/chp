package com.module;

import com.controller.UserController;
import com.dao.UserDao;
import com.entity.User;
import com.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Map;
/**
 * Created by Song on 2017/3/16.
 */
public class SimpleLoginModule implements LoginModule {
    public boolean logout() { return true;}
    public boolean abort() { return true;}
    public boolean commit() { return true;}
    private	Subject	subject;
    private	CallbackHandler callbackHandler;


    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?>options)
    {
        this.subject =	subject;
        this.callbackHandler =	callbackHandler;
    }

    public boolean login()	throws LoginException {
        if (callbackHandler == null)
            throw new LoginException("no handler");
        NameCallback nameCall = new NameCallback("username:	");
        PasswordCallback passCall = new PasswordCallback("password:	", false);
        try {
            callbackHandler.handle(new Callback[]{nameCall, passCall});
        }
        catch (UnsupportedCallbackException e)
        {
            LoginException e2 = new LoginException("Unsupported	callback");
            e2.initCause(e);
            throw e2;
        }
        catch (IOException e)
        {
            LoginException e2 = new LoginException("I/O exception in callback");
            e2.initCause(e);
            throw e2;
        }
        return checkLogin(nameCall.getName(), passCall.getPassword());
    }

    private boolean checkLogin(String userID, char[] passWord)
    {
        if(userID.equals("admin@admin.com") && Arrays.equals(passWord, "admin".toCharArray())) {
            subject.getPrincipals().add(new SimplePrincipal("role","admin"));
        }
        Connection conn = null;
        try {
            // 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 数据库连接url
            String url = "jdbc:mysql://localhost:3306/bookstore";
            // 获取数据库连接
            conn = DriverManager.getConnection(url, "root", "ssy226");

            String sql = "select * from user where userID = ? and passWord = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            // 对SQL语句的占位符参数进行动态赋值
            ps.setString(1, userID);
            ps.setString(2, String.valueOf(passWord));

            ResultSet rs = ps.executeQuery();
            // 判断结果集是否有效
            if(rs.next()){
                rs.close();
                ps.close();
                subject.getPrincipals().add(new SimplePrincipal("role","user"));
                return true;
            }
            else{
                rs.close();
                ps.close();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
