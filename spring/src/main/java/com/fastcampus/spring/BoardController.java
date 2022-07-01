package com.fastcampus.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	@GetMapping("/list")
	String list(HttpServletRequest request) {
		
		if(!loginCheck(request)) {
//			return "redirect:/login/login";	// 로그인을 안 했으면 로그인 화면으로 이동.
			return "redirect:/login/login?toURL="+request.getRequestURL();
		}
		
		Cookie[] cookies = request.getCookies();
		
		for(Cookie cookie : cookies) {
			String name = cookie.getName();
			String value = cookie.getValue();
			
			System.out.printf("[cookie] name=%s, value=%s\n", name, value);
		}
		return "boardList";					// 로그인을 했으면 게시판 화면으로 이동.
	}

	private boolean loginCheck(HttpServletRequest request) {
		// 1. 세션을 얻는다.
		HttpSession session = request.getSession();
		// 2. 세션에 id가 있는지 확인
//		if(session.getAttribute("id") != null) {
//			return true;
//		}else {
//			return false;
//		}
		return session.getAttribute("id") != null;
	}
}
