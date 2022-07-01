package com.my.spring3.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.spring3.domain.UserDto;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	SqlSession sqlSession;
	
	String namespace = "com.my.spring3.dao.userMapper.";
	
	@Override
	public int insert(UserDto dto) {
		
		return sqlSession.insert(namespace+"insert", dto);
	}
	
	@Override
	public UserDto select(String id) {
		
		return sqlSession.selectOne(namespace+"select", id);
	}
	@Override
	public List<UserDto> selectAll() {
		
		return sqlSession.selectList(namespace+"selectAll");
	}
	
	@Override
	public int update(UserDto dto) {
		
		return sqlSession.update(namespace+"update", dto);
	}
	
	@Override
	public int delete(String id) {
		
		return sqlSession.delete(namespace+"delete", id);
	}
	@Override
	public int deleteAll() {
		
		return sqlSession.delete(namespace+"deleteAll");
	}

	@Override
	public int count() {

		return sqlSession.selectOne(namespace+"count");
	}
	
}
