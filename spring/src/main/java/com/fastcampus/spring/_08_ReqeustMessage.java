
// - 요청 메세지 구현

package com.fastcampus.spring;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class _08_ReqeustMessage {

	@RequestMapping("/requestMessage")
	public void main(HttpServletRequest request) throws IOException {
		
		// 1. 요청라인 구현
		
		String requestLine = request.getMethod();	// GET 또는 POST
		requestLine += " " + request.getRequestURI();	// /spring/requestMessage
		
		String queryString = request.getQueryString();	// year=2021&month=10&day=1
		requestLine += queryString == null ? "" : "?"+queryString;
		requestLine += " " + request.getProtocol();		// HTTP/1.1
		
		System.out.println("requestLine : " + requestLine);
		
		// 2. 요청헤더 구현
		
		Enumeration<String> e = request.getHeaderNames();

		while (e.hasMoreElements()) {
			String name = e.nextElement();
			System.out.println(name + ":" + request.getHeader(name));
		}
		
		// 3. 요청바디 구현 - POST일 때만 해당, GET은 body가 없음(CONTENT_LENGTH=0)
		
		final int CONTENT_LENGTH = request.getContentLength();
		
		if(CONTENT_LENGTH > 0) {
			byte[] content = new byte[CONTENT_LENGTH];
			
			InputStream in = request.getInputStream();
			in.read(content, 0, CONTENT_LENGTH);
			
			System.out.println();								// 헤더와 바디의 구분줄
			System.out.println(new String(content, "utf-8"));	// year=2021&month=10&day=1
		}

	}
}
