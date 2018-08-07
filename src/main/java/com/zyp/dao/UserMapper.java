package com.zyp.dao;

import org.springframework.stereotype.Repository;

import com.zyp.moudle.User;

@Repository
public interface UserMapper {
	public User getUserByName(User user);
}
