package com.my.spring3.service;

import java.util.List;

import com.my.spring3.domain.UserDto;

public interface UserService {

	int regisUser(UserDto dto);

	UserDto getUser(String id);

	List<UserDto> getUserAll();

	int amendUser(UserDto dto);

	int removeUser(String id);

}