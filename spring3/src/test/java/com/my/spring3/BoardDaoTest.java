package com.my.spring3;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.spring3.dao.BoardDao;
import com.my.spring3.domain.BoardDto;
import com.my.spring3.domain.SearchCondition;

@RunWith(SpringJUnit4ClassRunner.class)		
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDaoTest {

	@Autowired
	private BoardDao boardDao;
	
	@Test
	public void sampleInsert() {
		
		boardDao.deleteAll();
		BoardDto dto = null;
		
		for(int i=0 ; i<220 ; i++) {
			
			dto = new BoardDto("����"+i, "����"+i, "qwer");
			boardDao.insert(dto);
		}
	}
	@Test
	public void connect() {
		
		assertTrue(boardDao != null);
		System.out.println("boardDao = " + boardDao);
	}
	@Test
	public void insertTest() {
		
		boardDao.deleteAll();
		BoardDto boardDto = new BoardDto("������", "������", "�ۼ���");
		assertTrue(boardDao.insert(boardDto)==1);
		assertTrue(boardDao.count()==1);
		
		boardDto = new BoardDto("������", "������", "�ۼ���");
		assertTrue(boardDao.insert(boardDto)==1);
		assertTrue(boardDao.count()==2);
		
		boardDto = new BoardDto("������", "������", "�ۼ���");
		assertTrue(boardDao.insert(boardDto)==1);
		assertTrue(boardDao.count()==3);
		
		System.out.println("time = " + boardDto.getReg_date());
	}
	
	@Test
	public void selectTest() {
		
		boardDao.deleteAll();
		assertTrue(boardDao.count()==0);
		
		BoardDto boardDto = new BoardDto("����(selectTest)", "����(selectTest)", "�ۼ���(selectTest)");
		boardDao.insert(boardDto);
		assertTrue(boardDao.count()==1);
		
		int bno = boardDao.selectAll().get(0).getBno();
		boardDto.setBno(bno);
		
		BoardDto boardDto2 = boardDao.select(bno);
		
		assertTrue(boardDto.equals(boardDto2));
	}
	
	@Test
	public void selectAllTest() {
		
		boardDao.deleteAll();
		assertTrue(boardDao.count()==0);
		
		List<BoardDto> list = boardDao.selectAll();
		assertTrue(list.size() == 0);
		
		BoardDto boardDto = new BoardDto("����(selectAllTest)", "����(selectAllTest)", "�ۼ���(selectAllTest)");
		assertTrue(boardDao.insert(boardDto) == 1);
		
		list = boardDao.selectAll();
		assertTrue(list.size() == 1);
		
		assertTrue(boardDao.insert(boardDto) == 1);
		
		list = boardDao.selectAll();
		assertTrue(list.size() == 2);
	}
	@Test
	public void searchSelectPage() {
		
		SearchCondition sc = new SearchCondition(1, 10, "����1", "T");
		List list = boardDao.searchSelectPage(sc);
		System.out.println("list = " + list);
	}
	@Test
	public void searchResultCnt() {
		
		SearchCondition sc = new SearchCondition(1, 10, "����1", "T");
		int count = boardDao.searchResultCnt(sc);
		System.out.println("count = " + count);
	}
	
	@Test
	public void updateTest() {
		
		boardDao.deleteAll();
		BoardDto boardDto = new BoardDto("����(updateTest)", "����(updateTest)", "�ۼ���(updateTest)");
		assertTrue(boardDao.insert(boardDto)==1);
		
		int bno = boardDao.selectAll().get(0).getBno();
		System.out.println("bno = " + bno);
		boardDto.setBno(bno);
		boardDto.setTitle("���� ����");
		assertTrue(boardDao.update(boardDto)==1);
		
		BoardDto boardDto2 = boardDao.select(bno);
		
		assertTrue(boardDto.equals(boardDto2));
	}

	@Test
	public void deleteTest() {
		
		boardDao.deleteAll();
		assertTrue(boardDao.count() == 0);
		
		BoardDto boardDto = new BoardDto("����(deleteTest)", "����(deleteTest)", "�ۼ���(deleteTest)");
		assertTrue(boardDao.insert(boardDto) == 1);
		
		int bno = boardDao.selectAll().get(0).getBno();
		String writer = boardDao.select(bno).getWriter();
		
		Map map = new HashMap();
		map.put("bno", bno);
		map.put("writer", writer);
		
		System.out.println("map = " + map);
		
		assertTrue(boardDao.delete(map) == 1);
		assertTrue(boardDao.count()==0);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
