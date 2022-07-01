
// - 다른 컨트롤러들에서 발생한 예외를 여기서 공통으로 처리할 수 있다.
// - 컨트롤러 내부에 @ExceptionHandler를 자체적으로 가지고 있다면 가까운 데서 예외를 처리한다.
// 결론 : 공통으로 처리해야하는 예외를 처리하는 컨트롤러를 만들고, 개별적으로 처리해야 하는 예외는 해당 컨트롤러에 작성한다.

package com.fastcampus.spring;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

// 패키지를 지정할 수 있다. -> 지정한 패키지에만 적용한다.
// 지정 안 해주면 모든 패키지에 적용된다.
@ControllerAdvice("com.fastcampus.spring9999")	// 전역 예외 처리 클래스 만드는 어노테이션
public class _28_GlobalCatcher {
	
	@ExceptionHandler({Exception.class, FileNotFoundException.class})
	String catcher1(Exception ex, Model m) {
		System.out.println("공통 클래스에서 처리");

		m.addAttribute("ex", ex);
		return "error";
	}
	@ExceptionHandler(NullPointerException.class)
	String catcher2(Exception ex, Model m) {
		System.out.println("공통 클래스에서 처리");

		m.addAttribute("ex", ex);
		return "error";
	}
}
