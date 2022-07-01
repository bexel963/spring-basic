package com.my.spring3.dao;

import java.util.List;

import com.my.spring3.domain.CommentDto;

public interface CommentDao {

	int count(Integer bno);

	int deleteAll(Integer bno);

	int delete(Integer cno, String commenter);

	int insert(CommentDto dto);

	List<CommentDto> selectAll(Integer bno);

	CommentDto select(Integer cno);

	int update(CommentDto dto);

}