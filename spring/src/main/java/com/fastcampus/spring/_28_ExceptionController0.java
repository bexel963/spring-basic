package com.fastcampus.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class _28_ExceptionController0 {
	
	@RequestMapping("/ex0")
	String main() throws Exception {
		try {
			throw new Exception("���ܰ� �߻��߽��ϴ�.");
		} catch (Exception e) {
			System.out.println("��ü �޼��忡�� ó��");
			return "error";
		}
	}
	
	@RequestMapping("/ex00")
	String main2() throws Exception {
		try {
			throw new Exception("���ܰ� �߻��߽��ϴ�.");
		} catch (Exception e) {
			System.out.println("��ü �޼��忡�� ó��");
			return "error";
		}
	}
}


