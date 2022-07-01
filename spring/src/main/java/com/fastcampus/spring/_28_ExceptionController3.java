
// - ���� ��Ʈ�ѷ����� �߻��ϴ� ���ܵ��� �������� ó���� �� �ְ� �����
// - ����ó���ϴ� �޼������ ������ Controller�� �־�ΰ� @ControllerAdvice�� ���̱⸸ �ϸ� �ȴ�.
// - ���ο� Ŭ���� ���� - GlobalCatcher �� @ControllerAdvice ���δ�.
// - ����� ���� ���� ����ϱ�

package com.fastcampus.spring;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

//����� ���� ����
@ResponseStatus(HttpStatus.BAD_REQUEST)			// �����ڵ� 500�� -> 400��
class MyException extends RuntimeException {
	MyException(String msg){
		super(msg);
	}
	MyException(){
		this("");
	}
}

// �� ��Ʈ�ѷ����� �߻��ϴ� ���ܴ� GlobalCatcher���� �޴´�. (���� ���ο� ����ó���� ���� ����..)
@Controller
public class _28_ExceptionController3 {
	
	@RequestMapping("/myException")
	String main4() throws Exception {
		
		throw new MyException("����� ���� ���ܰ� �߻��߽��ϴ�.");		
	}
	
	@RequestMapping("/ex6")
	String main() throws Exception {
		throw new Exception("���ܰ� �߻��߽��ϴ�.");					
	}
	
	@RequestMapping("/ex7")
	String main2() throws Exception {
		throw new NullPointerException("���ܰ� �߻��߽��ϴ�.");
	}
	
	@RequestMapping("/ex8")
	String main3() throws Exception {
		throw new FileNotFoundException("���ܰ� �߻��߽��ϴ�.");		// ��� ��ġ�ϴ°� ������ ���󿹿ܰ� �޴´�.
	}
	
}


