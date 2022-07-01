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

@RunWith(SpringJUnit4ClassRunner.class)		// ���⼭ AC ����
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) // AC����, �׽�Ʈ�� ����� �������� ����.
public class A1DaoTest {

	@Autowired
	A1Dao a1Dao;
	
	@Autowired
	DataSource ds;
	
	@Autowired
	DataSourceTransactionManager tm;
	
	@Test
	public void InsertTest() {
		// 2���� ����� ���� ������ ���� ����� ���� �ǰ� �ٲٱ�. -> �� �Ʒ��� Tx�� �ɾ��ָ� �ȴ�.
		// 1. TxManager�� ����
//		PlatformTransactionManager tm = new DataSourceTransactionManager(ds);
		// 2. Tx ����
		TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());	
		// 3. Dao���� Connection �������� �κ��� �����ؾ��Ѵ�.
		// 4. Dao���� finally �κ��� �����ؾ��Ѵ�. - ������ close �ϴ°� �ƴ϶� psmt�� �ݰ� Tx �Ŵ����� �Ǵ� �� ds�� �ݴ´�.
		try {
			a1Dao.insert(1, 100); 	// - (1)����
			a1Dao.insert(1, 200); 	// - (2)���� : A1Dao�� cath���� ���� ����
			tm.commit(status);
		} catch (Exception e) {		// - (3)���⼭ ���� ����
			e.printStackTrace();
			tm.rollback(status);	// - (4)�ѹ� : (1)��� ���� ���    -> DB�� �ƹ��͵� �� ��. Connection �ּ� �����Ѱ� Ȯ��
		}finally {
			
		}
	}

}
