
// - _17_YoilTeller_MVC3 에서 @ModelAttribute 적용

package com.fastcampus.spring;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class _17_YoilTeller_MVC4 {

	@ExceptionHandler(Exception.class)
	public String catcher(Exception e) {
		e.printStackTrace();
		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC4")	
	// 괄호 안에는 키를 지정해주는데 생략 가능 - 생략하면 키값이 매개변수 이름에서 앞글자를 소문자로 한 것으로 정해진다.
	// 매개변수가 참조형일때는 @ModelAttribute생략 가능하다 - 참조형 매개변수는 아무 작업도 안 해줘도 자동으로 Model객체에 저장한다. 아래줄 처럼
	// public String main(@ModelAttribute MyDate date) {
	public String main(MyDate date) {
			
		// 0. 유효성 검사
		if(!(isValid(date)))
			return "yoilError";

//		< @ModelAttribute를 이용하면 아래 작업은 전부 필요없게된다. >
		
//		// 1. 작업
//		char yoil = getYoil(date);
//		
//		// 2. 작업 결과를 model에 저장
//		model.addAttribute("myDate", date);
//		model.addAttribute("yoil", yoil);
		
		// 3. 결과를 출력할 view를 리턴
		return "yoil";						// /WEB-INF/views/yoil.jsp
		
	}
	
	private boolean isValid(MyDate date) {
		
		return true;
	}
				// 여기 괄호는 생략 못한다. - 내부적으로 @ModelAttribute가 붙은 메소드를 찾아서 발견하면 호출하고 모델에 반환값을 담는다. -> 따로 메서드를 호출할 필요 없게됨.
	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonth()-1, date.getDay());
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	
		return " 일월화수목금토".charAt(dayOfWeek);
	}
}
