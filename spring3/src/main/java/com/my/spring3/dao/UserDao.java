package com.my.spring3.dao;

import java.util.List;

import com.my.spring3.domain.UserDto;

public interface UserDao {

	int insert(UserDto dto);

	int count();
	
	UserDto select(String id);

	List<UserDto> selectAll();

	int update(UserDto dto);

	int delete(String id);

	int deleteAll();

}