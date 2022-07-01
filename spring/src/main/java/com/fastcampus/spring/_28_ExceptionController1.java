package com.fastcampus.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class _28_ExceptionController1 {
	
	// _28_ExceptionController0 ���� ������ �޼��忡�� try~catch ������ ó�� �ϴ�����
	// @ExceptionHandler�� �̿��Ͽ� �ϳ��� �޼��忡�� ó���� �� �ִ�.
	// �� �޼��尡 Ŭ���� ���ο��� try~catch �� ������ �Ѵ�.
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		return "error";
	}
	
	@RequestMapping("/ex1")
	String main() throws Exception {
		
		throw new Exception("���ܰ� �߻��߽��ϴ�.");
	}
	
	@RequestMapping("/ex2")
	String main2() throws Exception {
	
		throw new Exception("���ܰ� �߻��߽��ϴ�.");

	}
}


