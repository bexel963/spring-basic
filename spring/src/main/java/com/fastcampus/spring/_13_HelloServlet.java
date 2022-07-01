package com.fastcampus.spring;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// init()은 처음에 한번만 호출되고 그 후 요청이 올때마다 service()가 계속 반복적으로 호출되며 처리된다.
// destroy()은 왭 애플리케이션이 종료될때 호출된다.

@WebServlet("/helloServlet")
public class _13_HelloServlet extends HttpServlet{

	@Override
	public void init() throws ServletException {
		// 서블릿이 초기화될때 자동 호출되는 메서드
		// 1. 서블릿의 초기화 작업 담당
		System.out.println("[HelloServlet] init() is called");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 입력
		// 2. 처리
		// 3. 출력
		System.out.println("[HelloServlet] service() is called");
	}
  
	@Override
	public void destroy() {
		// 1. 뒷정리 - 서블릿이 메모리에서 제거될때 서블릿 컨텍스트 의해서 자동 호출
		System.out.println("[HelloServlet] destroy() is called");   

	}


}
