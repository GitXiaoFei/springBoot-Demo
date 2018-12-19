package com.springboot.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springboot.dao.UserMapper;
import com.springboot.model.User;
import com.springboot.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService   {
	@Resource
	private UserMapper userDao;
	
	public User getUserById(int userId){
		return this.userDao.selectByPrimaryKey(userId);
	}

}