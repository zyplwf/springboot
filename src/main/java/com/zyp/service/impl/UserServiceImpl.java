package com.zyp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zyp.dao.UserMapper;
import com.zyp.exception.UserException;
import com.zyp.moudle.User;
import com.zyp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	@Transactional
	public User getUser(String name) {
		throw new UserException(name);
	}

}
