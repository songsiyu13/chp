package com.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Created by 滩涂上的芦苇 on 2017/3/29.
 */
public class BookstoreUserDetails implements UserDetails{
    private String userID;
    private String password;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    public  BookstoreUserDetails(String userID, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities)
    {
        this.userID = userID;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return userID;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
