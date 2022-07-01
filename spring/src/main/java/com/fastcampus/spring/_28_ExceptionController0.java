package com.fastcampus.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class _28_ExceptionController0 {
	
	@RequestMapping("/ex0")
	String main() throws Exception {
		try {
			throw new Exception("예외가 발생했습니다.");
		} catch (Exception e) {
			System.out.println("자체 메서드에서 처리");
			return "error";
		}
	}
	
	@RequestMapping("/ex00")
	String main2() throws Exception {
		try {
			throw new Exception("예외가 발생했습니다.");
		} catch (Exception e) {
			System.out.println("자체 메서드에서 처리");
			return "error";
		}
	}
}


