
// - _10_YoilTeller_MVC ���� year, month, day�� �ϳ��� ��ġ��

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
		
		// 0. ��ȿ�� �˻�
		if(!(isValid(date)))
			return "yoilError";
		
		// 1. �۾�
		char yoil = getYoil(date);		//  - @ModelAttribute�� ��ȯŸ�Կ��� ���� �� �ִ�.
		
		// 2. �۾� ����� model�� ����
//		model.addAttribute("myDate", date);	- �Ű������� ������ �϶��� �Ű������� @ModelAttribute�� �ڵ����� �ٴ´�.

		model.addAttribute("yoil", yoil);
		// 3. ����� ����� view�� ����
		return "yoil";						// /WEB-INF/views/yoil.jsp
		
	}
	
	private boolean isValid(MyDate date) {
		
		return true;
	}

	private char getYoil(MyDate date) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonth()-1, date.getDay());
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	
		return " �Ͽ�ȭ�������".charAt(dayOfWeek);
	}
}
