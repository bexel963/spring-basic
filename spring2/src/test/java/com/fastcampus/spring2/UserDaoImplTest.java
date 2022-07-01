package com.fastcampus.spring2;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)		// ���⼭ AC ����
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {

	@Autowired
	UserDao userDao;		// root-context.xml�� component-scan ��� �Ǿ��־����.
	
	@Test
	public void deleteUser() {
		
		
	}

	@Test
	public void selectUser() {
		
		
	}

	@Test
	public void insertUser() {
		
		
	}
	
	@Test
	public void updateUser() throws Exception{

		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2021, 1, 1);
		
		userDao.deleteAll();												// �ڿ� �ð� 0���� ����
		User user = new User("������", "2461", "������", "bexel963@gmail.com", new Date(cal.getTimeInMillis()), "facebook", new Date(cal.getTimeInMillis()));
		int rowCnt = userDao.insertUser(user);
		assertTrue(rowCnt==1);
		
		user.setPwd("0000");
		user.setEmail("aaaa");
		
		rowCnt = userDao.updateUser(user);
		assertTrue(rowCnt==1);
		
		User user2 = userDao.selectUser(user.getId());
		
		System.out.println("user = " + user);
		System.out.println("user2 = " + user2);
		
		assertTrue(user.equals(user2));	
	}

}
