
// - _17_YoilTeller_MVC3 ���� @ModelAttribute ����

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
	// ��ȣ �ȿ��� Ű�� �������ִµ� ���� ���� - �����ϸ� Ű���� �Ű����� �̸����� �ձ��ڸ� �ҹ��ڷ� �� ������ ��������.
	// �Ű������� �������϶��� @ModelAttribute���� �����ϴ� - ������ �Ű������� �ƹ� �۾��� �� ���൵ �ڵ����� Model��ü�� �����Ѵ�. �Ʒ��� ó��
	// public String main(@ModelAttribute MyDate date) {
	public String main(MyDate date) {
			
		// 0. ��ȿ�� �˻�
		if(!(isValid(date)))
			return "yoilError";

//		< @ModelAttribute�� �̿��ϸ� �Ʒ� �۾��� ���� �ʿ���Եȴ�. >
		
//		// 1. �۾�
//		char yoil = getYoil(date);
//		
//		// 2. �۾� ����� model�� ����
//		model.addAttribute("myDate", date);
//		model.addAttribute("yoil", yoil);
		
		// 3. ����� ����� view�� ����
		return "yoil";						// /WEB-INF/views/yoil.jsp
		
	}
	
	private boolean isValid(MyDate date) {
		
		return true;
	}
				// ���� ��ȣ�� ���� ���Ѵ�. - ���������� @ModelAttribute�� ���� �޼ҵ带 ã�Ƽ� �߰��ϸ� ȣ���ϰ� �𵨿� ��ȯ���� ��´�. -> ���� �޼��带 ȣ���� �ʿ� ���Ե�.
	private @ModelAttribute("yoil") char getYoil(MyDate date) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonth()-1, date.getDay());
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	
		return " �Ͽ�ȭ�������".charAt(dayOfWeek);
	}
}
