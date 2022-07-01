
// - 여러 컨트롤러에서 발생하는 예외들을 공통으로 처리할 수 있게 만들기
// - 예외처리하는 메서드들을 별도의 Controller에 넣어두고 @ControllerAdvice를 붙이기만 하면 된다.
// - 새로운 클래스 생성 - GlobalCatcher 후 @ControllerAdvice 붙인다.
// - 사용자 정의 예외 사용하기

package com.fastcampus.spring;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

//사용자 정의 예외
@ResponseStatus(HttpStatus.BAD_REQUEST)			// 상태코드 500번 -> 400번
class MyException extends RuntimeException {
	MyException(String msg){
		super(msg);
	}
	MyException(){
		this("");
	}
}

// 이 컨트롤러에서 발생하는 예외는 GlobalCatcher에서 받는다. (따로 내부에 예외처리가 없기 때문..)
@Controller
public class _28_ExceptionController3 {
	
	@RequestMapping("/myException")
	String main4() throws Exception {
		
		throw new MyException("사용자 정의 예외가 발생했습니다.");		
	}
	
	@RequestMapping("/ex6")
	String main() throws Exception {
		throw new Exception("예외가 발생했습니다.");					
	}
	
	@RequestMapping("/ex7")
	String main2() throws Exception {
		throw new NullPointerException("예외가 발생했습니다.");
	}
	
	@RequestMapping("/ex8")
	String main3() throws Exception {
		throw new FileNotFoundException("예외가 발생했습니다.");		// 얘랑 일치하는게 없으면 조상예외가 받는다.
	}
	
}


