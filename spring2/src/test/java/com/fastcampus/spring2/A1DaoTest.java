package com.fastcampus.spring2;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@RunWith(SpringJUnit4ClassRunner.class)		// 여기서 AC 생성
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) // AC설정, 테스트에 사용할 설정파일 지정.
public class A1DaoTest {

	@Autowired
	A1Dao a1Dao;
	
	@Autowired
	DataSource ds;
	
	@Autowired
	DataSourceTransactionManager tm;
	
	@Test
	public void InsertTest() {
		// 2개의 명령이 전부 성공할 때만 명령이 실행 되게 바꾸기. -> 위 아래로 Tx를 걸어주면 된다.
		// 1. TxManager를 생성
//		PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
		// 2. Tx 시작
		TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());	
		// 3. Dao에서 Connection 가져오는 부분을 수정해야한다.
		// 4. Dao에서 finally 부분을 수정해야한다. - 무조건 close 하는게 아니라 psmt만 닫고 Tx 매니저가 판단 후 ds를 닫는다.
		try {
			a1Dao.insert(1, 100); 	// - (1)성공
			a1Dao.insert(1, 200); 	// - (2)실패 : A1Dao의 cath에서 예외 던짐
			tm.commit(status);
		} catch (Exception e) {		// - (3)여기서 예외 받음
			e.printStackTrace();
			tm.rollback(status);	// - (4)롤백 : (1)명령 실행 취소    -> DB에 아무것도 안 들어감. Connection 주소 동일한거 확인
		}finally {
			
		}
	}

}
