
// - jUnit ���� test : TDD
// JUnit(��������)�� �ڹ� ���α׷��� ���� ���� �׽�Ʈ �����ӿ�ũ�̴�.
// @test �޼���� public �̾����

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

// @RunWith ��� �Ϸ��� spring-test �� �����Ͽ� �߰�
@RunWith(SpringJUnit4ClassRunner.class)		// ���⼭ AC ����
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})	// AC����, �׽�Ʈ�� ����� �������� ����.
// �� �� �� ó�� �ϸ� �� Ŭ���� �ȿ��ִ� ��� �޼��忡 ���� AC�� �����ϴ� �ڵ带 ���� �ʾƵ� �ȴ�.
public class DBConnectionTest2Test {
	
	@Autowired
	DataSource ds;
	
	// Test �޼������ ���� ������ ��ü���� ������ ��Ų��.
	// �׷��� ds�� iv�̱� �ص�, ���� Ŭ������ �ִ� �� �׽�Ʈ �޼������ �� ds�� �������� �ʴ´�.
	
	// Test �޼���� public �̾����.
	@Test
    public void springjdbcConnectionTest() throws Exception {
      
//        ApplicationContext ac = new GenericXmlApplicationContext("file:src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); 

        System.out.println("conn = " + conn);
        
        assertTrue(conn!=null); 	// ��ȣ ���� ���ǽ��� true��, �׽�Ʈ ����.   
	}
	
	@Test
	public void insertUserTest() throws Exception{	
		User user = new User("�ڹ�", "1234", "������", "asdfsr@daum.net", new Date(), "facebook", new Date());
		
		deleteAll();
		
		int rowCnt = insertUser(user);
		
		assertTrue(rowCnt==1);
	}
	
	@Test
	public void updateUserTest() throws Exception{
		deleteAll();
		
		User user = new User("�ڹ�", "1234", "������", "asdfsr@daum.net", new Date(), "facebook", new Date());
		
		int rowCnt = insertUser(user);
		
		user.setPwd("0000");
		user.setEmail("aaaa");
		
		rowCnt = updateUser(user);
		
		assertTrue(rowCnt==1);
	}
	
	@Test
	public void selectUserTest() throws Exception {
		deleteAll();
		User user = new User("�ڹ�", "1234", "������", "asdfsr@daum.net", new Date(), "facebook", new Date());
		int rowCnt = insertUser(user);
		
		User user2 = selectUser("�ڹ�");
		
		assertTrue(user.getId().equals(user2.getId()));
	}
	
	@Test
	public void deleteUserTest() throws Exception {
		deleteAll();
		int rowCnt = deleteUser("asdf");
		
		assertTrue(rowCnt==0);
		
		User user = new User("�ڹ�", "1234", "������", "asdfsr@daum.net", new Date(), "facebook", new Date());
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
			conn.setAutoCommit(false);			// AutoComit�� true�� default ���̴�. - AutoComit�� ������ �� �������� ������ �ϳ��� Ʈ��������� ������ �� �ְ� ��.
			
			String sql = "insert into springbasic.user_info(`id`,`pwd`,`name`,`email`,`birth`,`sns`,`reg_date`) VALUES(?, ?, ?, ?, ?, ?, now())";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, "qwer");
			pstmt.setString(2, "1234");
			pstmt.setString(3, "abc");
			pstmt.setString(4, "abc@abc.com");
			pstmt.setDate(5, new java.sql.Date(new Date().getTime()));	
			pstmt.setString(6, "fb");
			
			// AutoCommit�̸� 1��, 2�� ����� �ϳ��� Ʈ��������� ���� �� ���� �ȴ�.
			// �׷��� 1�� ����� commit �ǰ� 2����ɸ� rollback�� �ȴ�. -> 1�� ����� DB�� �����ְԵ�.
			
			int rowCnt = pstmt.executeUpdate();		// 1�� ���
			
			pstmt.setString(1, "qwer");

			rowCnt = pstmt.executeUpdate();			// 2�� ���
			
			conn.commit();
			
		} catch (Exception e) {
			conn.rollback();			// 1, 2 �� �� �ϳ��� ������ �߻��ϸ� �ѹ�.
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
	    pstmt.setTimestamp(6, new java.sql.Timestamp(user.getReg_date().getTime()));	// Timestamp�� ��¥+�ð� �� �����°�.
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

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection����, �������
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

	// ����� ������ user_info���̺� �����ϴ� �޼���
	public int insertUser(User user) throws Exception{
		
		Connection conn = ds.getConnection();		// DB�� �����͸� �������� ���� DB�� ������ �ؾ���.
		
		// sql�� �ۼ�
		String sql = "insert into springbasic.user_info(`id`,`pwd`,`name`,`email`,`birth`,`sns`,`reg_date`) VALUES(?, ?, ?, ?, ?, ?, now())";
		
		// sql�� �Է� - Statement�� ���� SQL Injection���� ���, �������.
		PreparedStatement pstmt = conn.prepareStatement(sql);	
		pstmt.setString(1, user.getId());		// PreparedStatement�� setter�� �̿��Ͽ� ù��° ?�� user�� id�� �ִ´�.
		pstmt.setString(2, user.getPwd());
		pstmt.setString(3, user.getName());
		pstmt.setString(4, user.getEmail());
		pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));	// setDate�� sql Date�� �ʿ����
		pstmt.setString(6, user.getSns());
		
		// �Է��� sql�� ���� - ����� int�� (sql���� ������ ��� DB�� ��� ���� ������ �޾Ҵ°�. insert�� ���� ��� �����ϸ� 1 ��ȯ.)
		// executeUpdate()�� insert, update, delete���� ��� ����, select������ executeQuery()�� ���.
		int rowCnt = pstmt.executeUpdate();			
													
		return rowCnt;
	}
	
	
}
