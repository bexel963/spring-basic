
// - _10_YoilTeller_MVC 에서 year, month, day를 하나로 합치기

package com.fastcampus.spring;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class _17_YoilTeller_MVC3 {

	@ExceptionHandler(Exception.class)
	public String catcher(Exception e) {
		e.printStackTrace();
		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC3")							
	public String main(MyDate date, Model model) {
			
		System.out.println(date);
		
		// 0. 유효성 검사
		if(!(isValid(date)))
			return "yoilError";
		
		// 1. 작업
		char yoil = getYoil(date);		//  - @ModelAttribute는 반환타입에도 붙일 수 있다.
		
		// 2. 작업 결과를 model에 저장
//		model.addAttribute("myDate", date);	- 매개변수가 참조형 일때는 매개변수에 @ModelAttribute가 자동으로 붙는다.

		model.addAttribute("yoil", yoil);
		// 3. 결과를 출력할 view를 리턴
		return "yoil";						// /WEB-INF/views/yoil.jsp
		
	}
	
	private boolean isValid(MyDate date) {
		
		return true;
	}

	private char getYoil(MyDate date) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonth()-1, date.getDay());
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	
		return " 일월화수목금토".charAt(dayOfWeek);
	}
}
