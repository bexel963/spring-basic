package com.my.spring3.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.spring3.domain.CommentDto;

@Repository
public class CommentDaoImpl implements CommentDao {
	
	 	@Autowired
	    private SqlSession session;
	    private static String namespace = "com.my.spring3.dao.commentMapper.";

	    @Override
		public int count(Integer bno)  {
	        return session.selectOne(namespace+"count", bno);
	    } 

	    @Override
		public int deleteAll(Integer bno) {
	        return session.delete(namespace+"deleteAll", bno);
	    } 

	    @Override
		public int delete(Integer cno, String commenter)  {
	        Map map = new HashMap();
	        map.put("cno", cno);
	        map.put("commenter", commenter);
	        return session.delete(namespace+"delete", map);
	    } 

	    @Override
		public int insert(CommentDto dto)  {
	        return session.insert(namespace+"insert", dto);
	    } 

	    @Override
		public List<CommentDto> selectAll(Integer bno)  {
	        return session.selectList(namespace+"selectAll", bno);
	    } 

	    @Override
		public CommentDto select(Integer cno) {
	        return session.selectOne(namespace + "select", cno);
	    } 

	    @Override
		public int update(CommentDto dto)  {
	        return session.update(namespace+"update", dto);
	    } 
}
