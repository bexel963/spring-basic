package com.fastcampus.spring2;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)		// 여기서 AC 생성
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"}) // AC설정, 테스트에 사용할 설정파일 지정.
public class B1DaoTest {

	@Autowired
	TxService txService;
	
	@Test
	public void InsertA1WithoutTxTest() throws Exception{
		
		txService.insertA1WithTx_NEW();
	}

}
