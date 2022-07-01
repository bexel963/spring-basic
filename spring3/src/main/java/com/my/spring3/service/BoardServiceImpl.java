package com.my.spring3.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.spring3.dao.BoardDao;
import com.my.spring3.domain.BoardDto;
import com.my.spring3.domain.SearchCondition;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	@Override
	public int write(BoardDto dto) {
		
		return boardDao.insert(dto);
	}
	
	@Override
	public int getCount() {
		
		return boardDao.count();
	}
	@Override
	public List<BoardDto> getList(){

		return boardDao.selectAll();
	}
	
	@Override
	public List<BoardDto> getPage(Map map){
		
		return boardDao.selectPage(map);
	}
	
	@Override
	public List<BoardDto> getSearchResultPage(SearchCondition sc){
		
		return boardDao.searchSelectPage(sc);
	}
	@Override
	public int getSearchResultCnt(SearchCondition sc) {
		
		return boardDao.searchResultCnt(sc);
	}
	
	@Override
	public int modify(BoardDto boardDto) {
		
		return boardDao.update(boardDto);
	}
	
	@Override
	public BoardDto read(int num) {
		
		return boardDao.select(num);
	}
	
	@Override
	public int remove(Map map) {
		
		return boardDao.delete(map);
	}
	
	@Override
	public int updateCommentCnt(Integer bno, Integer cnt) {
		
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("cnt", cnt);
		
		return boardDao.updateCommentCnt(map);
	}
}
