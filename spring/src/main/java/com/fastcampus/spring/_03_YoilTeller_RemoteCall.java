
// - ��, ��, ���� �Է��ϸ� �ش� ��¥�� ���� �������� �˷��ִ� ���α׷�.
// �� �������� ��û�Ͽ� ��Ĺ�� �ִ� ���α׷��� �����Ų��.

package com.fastcampus.spring;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class _03_YoilTeller_RemoteCall {

	@RequestMapping("/getYoil")							// �������� ��� �Ϸ��� HttpServletResponse�� ��ü�� �ʿ��ϴ�.
	public static void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// < �Է� >
		
		String query = request.getQueryString();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		// < �۾� >
		
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm-1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	// 1:�Ͽ���, 2:������ ....
		char yoil = " �Ͽ�ȭ�������".charAt(dayOfWeek);
		
		// < ��� >
		
		response.setContentType("text/html");	// ���������� �ؽ�Ʈ ���� �˷���.
		response.setCharacterEncoding("utf-8");	// ���������� ���ڵ� ���� �˷���.
		PrintWriter out = response.getWriter();	// response��ü���� ���������� ��� ��Ʈ���� ��´�.
		
		out.println("<html>");
		out.println("<head>");
		out.println("<body>");
		out.println(query + "<br>");
		out.println(year + "�� " + month + "�� " + day + "���� ");
		out.println(yoil + "���� �Դϴ�.");
		out.println("</body>");
		out.println("</head>");
		out.println("</html>");
	}
}
