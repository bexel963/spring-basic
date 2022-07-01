package com.my.spring3.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.spring3.domain.BoardDto;
import com.my.spring3.domain.SearchCondition;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	SqlSession session;
	
	String namespace = "com.my.spring3.dao.boardMapper.";
	
	@Override
	public int insert(BoardDto boardDto) {
			
		return session.insert(namespace+"insert", boardDto);
	}
	
	@Override
	public BoardDto select(int num) {
		
		return session.selectOne(namespace+"select", num); 
	}
	
	@Override
	public List<BoardDto> selectAll(){
		
		return session.selectList(namespace+"selectAll");
	}
	
	@Override
	public int count() {
		
		return session.selectOne(namespace+"count");
	}
	
	@Override
    public List<BoardDto> selectPage(Map map) {
		
        return session.selectList(namespace+"selectPage", map);
    } 
	
	@Override
	public List<BoardDto> searchSelectPage(SearchCondition sc){
		
		return session.selectList(namespace+"searchSelectPage", sc);
	}
	@Override
	public int searchResultCnt(SearchCondition sc) {
		
		return session.selectOne(namespace+"searchResultCnt", sc);
	}
	
	
	@Override
	public int update(BoardDto boardDto) {
		
		return session.update(namespace+"update", boardDto);
	}
	
	@Override
	public int increaseViewCnt(int bno) {
		
		return session.update(namespace+"increaseViewCnt", bno);
	}

	@Override
	public int delete(Map map) {
		
		return session.delete(namespace+"delete", map);
	}

	@Override
	public int deleteAll() {
		
		return session.delete(namespace+"deleteAll");
	}
	
	@Override
	public int updateCommentCnt(Map map) {
		
		return session.update(namespace+"updateCommentCnt", map);
	}
}
