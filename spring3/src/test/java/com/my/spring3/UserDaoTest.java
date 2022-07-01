package com.my.spring3;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.spring3.dao.UserDao;
import com.my.spring3.domain.UserDto;

@RunWith(SpringJUnit4ClassRunner.class)		
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoTest {

	@Autowired
	UserDao userDao;
	
	@Test
	public void insertTest() {
		
		userDao.deleteAll();
		assertTrue(userDao.count() == 0);
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2021, 1, 1);
		
		UserDto dto = new UserDto("asdf", "1234", "È«±æµ¿", "sdfsr@naver.com", new Date(cal.getTimeInMillis()), "facebook#kakao");
		System.out.println("dto = " + dto);
		assertTrue(userDao.insert(dto) == 1);
	}
	
	@Test
	public void selectTest() {
		
		userDao.deleteAll();
		assertTrue(userDao.count() == 0);
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2021, 1, 1);
		
		UserDto dto = new UserDto("asdf", "1234", "È«±æµ¿", "sdfsr@naver.com", new Date(cal.getTimeInMillis()), "facebook#kakao");

		assertTrue(userDao.insert(dto) == 1);
		assertTrue(userDao.count() == 1);
		
		cal.set(2021, 3, 15);
		dto = new UserDto("qwer", "4567", "ÀÓ²©Á¤", "vcer75@naver.com", new Date(cal.getTimeInMillis()), "kakao#instagram");
		assertTrue(userDao.insert(dto) == 1);
		assertTrue(userDao.count() == 2);
		
		UserDto dto2 = userDao.select("qwer");
		
		System.out.println("dto = " + dto);
		System.out.println("dto2 = " + dto2);
		assertTrue(dto.getName().equals(dto2.getName()));
	}
	
	@Test
	public void selectAllTest() {
		
		userDao.deleteAll();
		assertTrue(userDao.count() == 0);
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2021, 1, 1);
		
		UserDto dto = new UserDto("asdf", "1234", "È«±æµ¿", "sdfsr@naver.com", new Date(cal.getTimeInMillis()), "facebook#kakao");

		assertTrue(userDao.insert(dto) == 1);
		assertTrue(userDao.count() == 1);
		
		cal.set(2021, 3, 15);
		dto = new UserDto("qwer", "4567", "ÀÓ²©Á¤", "vcer75@naver.com", new Date(cal.getTimeInMillis()), "kakao#instagram");
		assertTrue(userDao.insert(dto) == 1);
		assertTrue(userDao.count() == 2);
		
		assertTrue(userDao.selectAll().size() == 2);
	}
	@Test
	public void updateTest() {
		
		userDao.deleteAll();
		assertTrue(userDao.count() == 0);
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2021, 1, 1);
		
		UserDto dto = new UserDto("asdf", "1234", "È«±æµ¿", "sdfsr@naver.com", new Date(cal.getTimeInMillis()), "facebook#kakao");

		assertTrue(userDao.insert(dto) == 1);
		assertTrue(userDao.count() == 1);
		
		dto = new UserDto("asdf", "0000", "È«±æµ¿", "0000@naver.com", new Date(cal.getTimeInMillis()), "facebook#kakao");
		assertTrue(userDao.update(dto) == 1);
	}
	
	@Test
	public void delete() {
		
		userDao.deleteAll();
		assertTrue(userDao.count() == 0);
		
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.set(2021, 1, 1);
		
		UserDto dto = new UserDto("asdf", "1234", "È«±æµ¿", "sdfsr@naver.com", new Date(cal.getTimeInMillis()), "facebook#kakao");

		assertTrue(userDao.insert(dto) == 1);
		assertTrue(userDao.count() == 1);
		
		cal.set(2021, 3, 15);
		dto = new UserDto("qwer", "4567", "ÀÓ²©Á¤", "vcer75@naver.com", new Date(cal.getTimeInMillis()), "kakao#instagram");
		assertTrue(userDao.insert(dto) == 1);
		assertTrue(userDao.count() == 2);
		
		assertTrue(userDao.delete("asdf") == 1);
	}

}
