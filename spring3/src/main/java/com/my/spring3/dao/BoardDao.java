package com.my.spring3.dao;

import java.util.List;
import java.util.Map;

import com.my.spring3.domain.BoardDto;
import com.my.spring3.domain.SearchCondition;

public interface BoardDao {

	int insert(BoardDto boardDto);

	BoardDto select(int num);

	List<BoardDto> selectAll();

	int count();

	int update(BoardDto boardDto);

	int updateCommentCnt(Map map);

	int increaseViewCnt(int bno);
	
	int delete(Map map);
	
	int deleteAll();

	List<BoardDto> selectPage(Map map);
	
	List<BoardDto> searchSelectPage(SearchCondition sc);
	
	int searchResultCnt(SearchCondition sc);

}