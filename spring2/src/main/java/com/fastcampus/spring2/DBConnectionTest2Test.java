
// - jUnit 으로 test : TDD
// JUnit(제이유닛)은 자바 프로그래밍 언어용 유닛 테스트 프레임워크이다.
// @test 메서드는 public 이어야함

package com.fastcampus.spring2;


import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// @RunWith 사용 하려면 spring-test 을 폼파일에 추가
@RunWith(SpringJUnit4ClassRunner.class)		// 여기서 AC 생성
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})	// AC설정, 테스트에 사용할 설정파일 지정.
// 위 두 줄 처럼 하면 한 클래스 안에있는 모든 메서드에 각각 AC를 생성하는 코드를 넣지 않아도 된다.
public class DBConnectionTest2Test {
	
	@Autowired
	DataSource ds;
	
	// Test 메서드들은 각각 별도의 객체에서 실행을 시킨다.
	// 그래서 ds가 iv이긴 해도, 같은 클래스에 있는 각 테스트 메서드들이 이 ds를 공유하지 않는다.
	
	// Test 메서든는 public 이어야함.
	@Test
    public void springjdbcConnectionTest() throws Exception {
      
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); 

        System.out.println("conn = " + conn);
        
        assertTrue(conn!=null); 	// 괄호 안의 조건식이 true면, 테스트 성공.   
	}
	
	@Test
	public void insertUserTest() throws Exception{	
		User user = new User("자바", "1234", "유관순", "asdfsr@daum.net", new Date(), "facebook", new Date());
		
		deleteAll();
		
		int rowCnt = insertUser(user);
		
		assertTrue(rowCnt==1);
	}
	
	@Test
	public void updateUserTest() throws Exception{
		deleteAll();
		
		User user = new User("자바", "1234", "유관순", "asdfsr@daum.net", new Date(), "facebook", new Date());
		
		int rowCnt = insertUser(user);
		
		user.setPwd("0000");
		user.setEmail("aaaa");
		
		rowCnt = updateUser(user);
		
		assertTrue(rowCnt==1);
	}
	
	@Test
	public void selectUserTest() throws Exception {
		deleteAll();
		User user = new User("자바", "1234", "유관순", "asdfsr@daum.net", new Date(), "facebook", new Date());
		int rowCnt = insertUser(user);
		
		User user2 = selectUser("자바");
		
		assertTrue(user.getId().equals(user2.getId()));
	}
	
	@Test
	public void deleteUserTest() throws Exception {
		deleteAll();
		int rowCnt = deleteUser("asdf");
		
		assertTrue(rowCnt==0);
		
		User user = new User("자바", "1234", "유관순", "asdfsr@daum.net", new Date(), "facebook", new Date());
		rowCnt = insertUser(user);
		assertTrue(rowCnt==1);
		
		rowCnt = deleteUser(user.getId());
		assertTrue(rowCnt==1);
		
		assertTrue(selectUser(user.getId())==null);
	}
	
	@Test
	public void transactionTest() throws Exception{
		
		Connection conn = null;
		
		try {
			deleteAll();
			conn = ds.getConnection();		
			conn.setAutoCommit(false);			// AutoComit은 true가 default 값이다. - AutoComit을 끔으로 써 여러개의 문장을 하나의 트랜잭션으로 구성할 수 있게 함.
			
			String sql = "insert into springbasic.user_info(`id`,`pwd`,`name`,`email`,`birth`,`sns`,`reg_date`) VALUES(?, ?, ?, ?, ?, ?, now())";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, "qwer");
			pstmt.setString(2, "1234");
			pstmt.setString(3, "abc");
			pstmt.setString(4, "abc@abc.com");
			pstmt.setDate(5, new java.sql.Date(new Date().getTime()));	
			pstmt.setString(6, "fb");
			
			// AutoCommit이면 1번, 2번 명령을 하나의 트랜잭션으로 묶을 수 없게 된다.
			// 그러면 1번 명령은 commit 되고 2번명령만 rollback이 된다. -> 1번 명령은 DB에 남아있게됨.
			
			int rowCnt = pstmt.executeUpdate();		// 1번 명령
			
			pstmt.setString(1, "qwer");

			rowCnt = pstmt.executeUpdate();			// 2번 명령
			
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();			// 1, 2 번 중 하나라도 문제가 발생하면 롤백.
			e.printStackTrace();
		} finally {
			
		}
													
	}
	
	public int updateUser(User user) throws Exception {
		
		Connection conn = ds.getConnection();
		
		String sql = "update springbasic.user_info " + "set pwd = ?, name=?, email=?, birth =?, sns=?, reg_date = ? " + "where id = ? ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, user.getPwd());
	    pstmt.setString(2, user.getName());
	    pstmt.setString(3, user.getEmail());
	    pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
	    pstmt.setString(5, user.getSns());
	    pstmt.setTimestamp(6, new java.sql.Timestamp(user.getReg_date().getTime()));	// Timestamp는 날짜+시간 다 나오는거.
	    pstmt.setString(7, user.getId());
	    
	    int rowCnt = pstmt.executeUpdate();
	    
		return rowCnt;
	}
	
	public int deleteUser(String id) throws Exception {
		
		Connection conn = ds.getConnection();		
		
		String sql = "delete from springbasic.user_info where id= ? ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);	
		pstmt.setString(1, id);
		int rowCnt = pstmt.executeUpdate();	
		
		return rowCnt;
	}
	public User selectUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "select * from user_info where id= ? ";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection공격, 성능향상
        pstmt.setString(1,id);
        ResultSet rs = pstmt.executeQuery(); //  select

        if(rs.next()) {
            User user = new User();
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(new Date(rs.getDate(5).getTime()));
            user.setSns(rs.getString(6));
            user.setReg_date(new Date(rs.getTimestamp(7).getTime()));
            return user;
        }
        return null;
    }
	
	private void deleteAll() throws Exception{

		Connection conn = ds.getConnection();		
		
		String sql = "delete from springbasic.user_info";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);	
		pstmt.executeUpdate();	
	}

	// 사용자 정보를 user_info테이블에 저장하는 메서드
	public int insertUser(User user) throws Exception{
		
		Connection conn = ds.getConnection();		// DB에 데이터를 넣으려면 먼저 DB와 연결을 해야함.
		
		// sql문 작성
		String sql = "insert into springbasic.user_info(`id`,`pwd`,`name`,`email`,`birth`,`sns`,`reg_date`) VALUES(?, ?, ?, ?, ?, ?, now())";
		
		// sql문 입력 - Statement에 비해 SQL Injection공격 방어, 성능향상.
		PreparedStatement pstmt = conn.prepareStatement(sql);	
		pstmt.setString(1, user.getId());		// PreparedStatement의 setter를 이용하여 첫번째 ?에 user의 id를 넣는다.
		pstmt.setString(2, user.getPwd());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));	// setDate는 sql Date를 필요로함
		pstmt.setString(6, user.getSns());
		
		// 입력한 sql문 실행 - 결과는 int임 (sql문을 실행한 결과 DB에 몇개의 행이 영향을 받았는가. insert문 같은 경우 성공하면 1 반환.)
		// executeUpdate()는 insert, update, delete에만 사용 가능, select문에는 executeQuery()를 사용.
		int rowCnt = pstmt.executeUpdate();			
													
		return rowCnt;
	}
	
	
}
