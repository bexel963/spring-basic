
// - _10_YoilTeller_MVC ���� @RequestParam�� ExceptionHandler �߰�

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
public class _17_YoilTeller_MVC2 {

	@ExceptionHandler(Exception.class)
	public String catcher(Exception e) {
		e.printStackTrace();
		return "yoilError";
	}
	
	@RequestMapping("/getYoilMVC2")							
	public String main(@RequestParam(required=true) int year, @RequestParam(required=true) int month, @RequestParam(required=true) int day, Model model) {
			
		// 0. ��ȿ�� �˻�
		if(!(isValid(year, month, day)))
			return "yoilError";
		
		// 1. �۾�
		char yoil = getYoil(year, month, day);
		
		// 2. �۾� ����� model�� ����
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("day", day);
		model.addAttribute("yoil", yoil);
		
		// 3. ����� ����� view�� ����
		return "yoil";						// /WEB-INF/views/yoil.jsp
		
	}
	
	private boolean isValid(int year, int month, int day) {
		
		return true;
	}

	private char getYoil(int year, int month, int day) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	
		return " �Ͽ�ȭ�������".charAt(dayOfWeek);
	}
}
