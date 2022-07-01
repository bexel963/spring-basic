package com.fastcampus.spring2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TxService {

	@Autowired 
	A1Dao a1Dao;
	
	@Autowired 
	B1Dao b1Dao;
	
	
	public void insertA1WithoutTx() throws Exception{
		
		a1Dao.insert(1, 100);
		a1Dao.insert(1, 200);
	}
	
					// ��ȣ ���� ���ָ� Exception�� �� �ڼ��� ó�� ����(rollback)
					// @Transaction�� RuntimeException, Error�� ó�� ����(rollback)
	@Transactional(rollbackFor = Exception.class)		// �̰� ���ָ� A1DaoTest���� ó�� Tx ���� �ϴ� �κа� rollback �ϴ� �κ��� ��ɹ�(insert��) �� �Ʒ��� �ڵ����� �־��ش�.
	public void insertA1WithTxFail() throws Exception{
		
		a1Dao.insert(1, 100);
		a1Dao.insert(1, 200);
	}
	
	@Transactional		
	public void insertA1WithTxSuccess() throws Exception{
		
		a1Dao.insert(1, 100);
		a1Dao.insert(2, 200);
	}
	
	
	
	// �̰� �׽�Ʈ�ϸ� �ƹ��͵� ���� �� �Ǿ� �Ѵ�.
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)		
	public void insertA1WithTx() throws Exception {
		
		a1Dao.insert(1, 100);	// ����
		insertB1WithTx();
		a1Dao.insert(1, 100);	// ����
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)		
	private void insertB1WithTx() throws Exception{
		b1Dao.insert(1, 100);	// ����
		b1Dao.insert(2, 100);	// ����	
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)		// ���� �ȵ�.
	public void insertA1WithTx_NEW() throws Exception {
		
		a1Dao.insert(1, 100);	// ����
		insertB1WithTx_NEW();
		a1Dao.insert(2, 100);	// ����
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)		// ���� �ȵ�.
	private void insertB1WithTx_NEW() throws Exception{
		b1Dao.insert(1, 100);	// ����
		b1Dao.insert(1, 100);	// ����	
	}
}
