
// - 년, 월, 일을 입력하면 해당 날짜가 무슨 요일인지 알려주는 프로그램.
// 웹 브라우저로 요청하여 톰캣에 있는 프로그램을 실행시킨다.

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

	@RequestMapping("/getYoil")							// 브라우저에 출력 하려면 HttpServletResponse의 객체가 필요하다.
	public static void main(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// < 입력 >
		
		String query = request.getQueryString();
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		
		int yyyy = Integer.parseInt(year);
		int mm = Integer.parseInt(month);
		int dd = Integer.parseInt(day);
		
		// < 작업 >
		
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm-1, dd);
		
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);	// 1:일요일, 2:월요일 ....
		char yoil = " 일월화수목금토".charAt(dayOfWeek);
		
		// < 출력 >
		
		response.setContentType("text/html");	// 브라우저에게 텍스트 형식 알려줌.
		response.setCharacterEncoding("utf-8");	// 브라우저에게 인코딩 형식 알려줌.
		PrintWriter out = response.getWriter();	// response객체에서 브라우저로의 출력 스트림을 얻는다.
		
		out.println("<html>");
		out.println("<head>");
		out.println("<body>");
		out.println(query + "<br>");
		out.println(year + "년 " + month + "월 " + day + "일은 ");
		out.println(yoil + "요일 입니다.");
		out.println("</body>");
		out.println("</head>");
		out.println("</html>");
	}
}
