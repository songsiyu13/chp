package com.service;

import com.entity.User;

public interface UserService {

	public void add(User user);
	
	public void update(User user);
	
	public void delete(Integer userid);
	
	public User findByUserID(String userid);
}
