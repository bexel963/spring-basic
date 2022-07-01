package com.my.spring3.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring3.dao.UserDao;
import com.my.spring3.domain.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public int regisUser(UserDto dto) {
		
		return userDao.insert(dto);
	}
	
	@Override
	public UserDto getUser(String id) {
		
		return userDao.select(id);
	}
	
	@Override
	public List<UserDto> getUserAll(){
		
		return userDao.selectAll();
	}
	
	@Override
	public int amendUser(UserDto dto) {
		
		return userDao.update(dto);
	}
	
	@Override
	public int removeUser(String id) {
		
		return userDao.delete(id);
	}
}
