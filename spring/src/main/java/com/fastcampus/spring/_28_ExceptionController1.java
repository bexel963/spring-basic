package com.fastcampus.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class _28_ExceptionController1 {
	
	// _28_ExceptionController0 에서 각각의 메서드에서 try~catch 블럭으로 처리 하던것을
	// @ExceptionHandler를 이용하여 하나의 메서드에서 처리할 수 있다.
	// 이 메서드가 클래스 내부에서 try~catch 블럭 역할을 한다.
	@ExceptionHandler(Exception.class)
	public String catcher(Exception ex) {
		return "error";
	}
	
	@RequestMapping("/ex1")
	String main() throws Exception {
		
		throw new Exception("예외가 발생했습니다.");
	}
	
	@RequestMapping("/ex2")
	String main2() throws Exception {
	
		throw new Exception("예외가 발생했습니다.");

	}
}


