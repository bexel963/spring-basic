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
	
					// 괄호 문장 써주면 Exception과 그 자손을 처리 가능(rollback)
					// @Transaction은 RuntimeException, Error만 처리 가능(rollback)
	@Transactional(rollbackFor = Exception.class)		// 이걸 써주면 A1DaoTest에서 처럼 Tx 시작 하는 부분과 rollback 하는 부분을 명령문(insert문) 위 아래로 자동으로 넣어준다.
	public void insertA1WithTxFail() throws Exception{
		
		a1Dao.insert(1, 100);
		a1Dao.insert(1, 200);
	}
	
	@Transactional		
	public void insertA1WithTxSuccess() throws Exception{
		
		a1Dao.insert(1, 100);
		a1Dao.insert(2, 200);
	}
	
	
	
	// 이거 테스트하면 아무것도 저장 안 되야 한다.
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)		
	public void insertA1WithTx() throws Exception {
		
		a1Dao.insert(1, 100);	// 성공
		insertB1WithTx();
		a1Dao.insert(1, 100);	// 실패
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)		
	private void insertB1WithTx() throws Exception{
		b1Dao.insert(1, 100);	// 성공
		b1Dao.insert(2, 100);	// 성공	
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)		// 실행 안됨.
	public void insertA1WithTx_NEW() throws Exception {
		
		a1Dao.insert(1, 100);	// 성공
		insertB1WithTx_NEW();
		a1Dao.insert(2, 100);	// 성공
	}
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)		// 실행 안됨.
	private void insertB1WithTx_NEW() throws Exception{
		b1Dao.insert(1, 100);	// 성공
		b1Dao.insert(1, 100);	// 실패	
	}
}
