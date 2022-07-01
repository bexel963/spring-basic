package com.my.spring3.service;

import java.util.List;
import java.util.Map;

import com.my.spring3.domain.BoardDto;
import com.my.spring3.domain.SearchCondition;

public interface BoardService {

	int write(BoardDto dto);

	List<BoardDto> getList();

	List<BoardDto> getSearchResultPage(SearchCondition sc);
	
	int getSearchResultCnt(SearchCondition sc);
	
	int modify(BoardDto boardDto);

	BoardDto read(int num);

	int remove(Map map);

	List<BoardDto> getPage(Map map);

	int getCount();

	int updateCommentCnt(Integer bno, Integer cnt);

}