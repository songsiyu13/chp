package com.dao;

import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository(value="userDao")
public interface UserDao {
	void add(User user);
	void update(User user);
	void delete(Integer userid);
	User findByUserID(String userid);
}
