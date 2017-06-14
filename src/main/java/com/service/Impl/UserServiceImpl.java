package com.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entity.PageModel;
import com.entity.User;
import com.service.UserService;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired

	private UserDao userDao;
	
	public void add(User user) {
		userDao.add(user);
	}

	public void update(User user) {
		userDao.update(user);
	}


	public void delete(Integer userid) {
		userDao.delete(userid);
	}
	
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public User findByUserID(String userid) {
		return userDao.findByUserID(userid);
	}
}
