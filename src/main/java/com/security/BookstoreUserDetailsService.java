package com.security;

import com.dao.UserDao;
import com.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by 滩涂上的芦苇 on 2017/3/26.
 */
@Service
public class BookstoreUserDetailsService implements UserDetailsService {

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    @Autowired
    UserDao userDao;

    @Autowired
    JedisPool jedisPool;

    public Jedis getResource() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jedis;
    }

    public String setObject(String key,Object value,int cacheSeconds){
        String result = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            result = jedis.set(toBytes(key),toBytes(value));
            if (cacheSeconds!=0){
                jedis.expire(key,cacheSeconds);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private Object getObject(String key){
        Object value = null;
        Jedis jedis = null;
        try {
            jedis = getResource();
            byte[] bytes = jedis.get(toBytes(key));
            if (bytes == null) return null;
            value =  toObject(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    private byte[] toBytes(Object value) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(value);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    /**
     * byte[]转换为object
     * @param bytes
     * @return
     */
    private Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        Jedis jedis = null;
        User user = (User)getObject(username);
        if(user == null)
        {
            user = userDao.findByUserID(username);
            setObject(username,user,10);
        }
        if (user == null) throw new UsernameNotFoundException(messages.getMessage("User notFound",new Object[]{username},"userName{0} not found"));

        String userId = user.getUserID();

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_USER");
        authorities.add(grantedAuthority);
        if (user.getUserID().equals("admin@admin.com"))
        {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("READ_SECRET"));
        }

        UserDetails userDetails = new BookstoreUserDetails(userId, user.getPassWord(), true ,authorities);

        return  userDetails;
    }
}
