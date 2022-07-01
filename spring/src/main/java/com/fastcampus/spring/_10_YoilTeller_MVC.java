
// - YoilTeller_RemoteCall ��Ʈ�ѷ��� MVC�������� �ٲٱ� - Model�� ModelAndView 2������ �ִ�.
// - MCV ���� : _12_MyDispatcherServlet
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
public class _10_YoilTeller_MVC {

	@RequestMapping("/getYoilMVC0")							
	public String main(int year, int month, int day, Model model) {
			
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
	
	@RequestMapping("/getYoilMVC1")							
	public ModelAndView main2(int year, int month, int day) {
			
		ModelAndView mv = new ModelAndView();
		
		// 0. ��ȿ�� �˻�
		if(!(isValid(year, month, day))) {
			mv.setViewName("yoil");
			return mv;
		}
		
		// 1. �۾�
		char yoil = getYoil(year, month, day);
		
		// 2. �۾� ����� ModelAndView�� ����
		mv.addObject("year", year);
		mv.addObject("month", month);
		mv.addObject("day", day);
		mv.addObject("yoil", yoil);
		
		// 3. ����� ������ view�� ModelAndView�� ����
		mv.setViewName("yoil");
		
		return mv;	// /WEB-INF/views/yoil.jsp
		
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
