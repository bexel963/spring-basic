
// - try~catch 문으로 예외처리 하는것을 @ExceptionHandler로 처리하기
// - @ExceptionHandler는 @ExceptionHandler가 작성되어있는 해당 클래스 에서만 작동한다. 

package com.fastcampus.spring;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class _28_ExceptionController2 {
	
	
	// try~catch 블럭처럼 계속 추가할 수 있다.
	// 메서드 작성은 @RequestMapping 메서드 작성하듯이 하면 된다.
	// 배열로 여러개 지정 가능하다.
	@ExceptionHandler({Exception.class, FileNotFoundException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)	// 200번 상태코드를 500번으로 바꾸는 것.
	String catcher1(Exception ex, Model m) {		
		System.out.println("개별적으로 처리");
//		m.addAttribute("ex", ex);			// error페이지에 isErrorPage="true" 설정을 하면 모델 필요없다.
		return "error";						// 예외가 발생하고 처리jsp페이지로 잘 처리 했는데 응답 상태가 200번대가 나온다. (에러니까 200번대가 나오면 안됨)
	}
	@ExceptionHandler(NullPointerException.class)
	String catcher2(Exception ex, Model m) {
		System.out.println("개별적으로 처리");
		System.out.println("m = " + m);		// 여기에 있는 모델과 @RequestMapping 메서드에 있는 모델은 서로 다른 객체이다.
		m.addAttribute("ex", ex);			// 모델객체를 출력하면 모델 안에 있는 내용들이 출력된다.
		return "error";
	}
//--------------------------------------------------------------------------------------
	// @RequestMapping에서 예외가 발생하면 @ExceptionHandler가 처리를 한다.
	@RequestMapping("/ex3")
	String main() throws Exception {
		
		throw new Exception("예외가 발생했습니다.");					
	}
	
	@RequestMapping("/ex4")
	String main2(Model m) throws Exception {
		m.addAttribute("msg", "message from ExceptionController.main()");
		throw new NullPointerException("예외가 발생했습니다.");
	}
	
	@RequestMapping("/ex5")
	String main3() throws Exception {

		throw new FileNotFoundException("예외가 발생했습니다.");		// 얘랑 일치하는게 없으면 조상예외가 받는다.
	}
	
}


