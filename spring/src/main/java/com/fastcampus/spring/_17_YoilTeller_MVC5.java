
// - _17_YoilTeller_MVC4 에서 BindingResult 사용

package com.fastcampus.spring;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class _17_YoilTeller_MVC5 {

	@ExceptionHandler(Exception.class)
	public String catcher(Exception e, BindingResult result) {
		System.out.println("result = " + result);
		
		FieldError error = result.getFieldError();
		
		System.out.println("code = " + error.getCode());
		System.out.println("field = " + error.getField());
		System.out.println("msg = " + error.getDefaultMessage());
		e.printStackTrace();

		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC5")	
	public String main(MyDate date) {
					
		if(!(isValid(date)))
			return "yoilError";
		
		return "yoil";						
		
	}
	
	private boolean isValid(MyDate date) {
		
		return true;
	}
	
	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonth()-1, date.getDay());
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	
		return " 일월화수목금토".charAt(dayOfWeek);
	}
}
