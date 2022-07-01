package com.fastcampus.spring;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping("/login")
	String loginForm() {
		
		return "loginForm";
	}
	
	@PostMapping("/login")
	String login(String id, String pwd, boolean rememberId, String toURL, HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("id = " + id);
		System.out.println("pwd = " + pwd);
		System.out.println("rememberId = " + rememberId);	// 매개변수 타입을 String으로 하면 on/off로 받는다.
		
		
		// 1. id와 pwd를 확인
		if(!loginCheck(id, pwd)) {
		// 2-1. 일치하지 않으면 loginForm으로 이동
			String msg = URLEncoder.encode("id 또는 pwd가 일치하지 않습니다.", "utf-8");
			
			return "redirect:/login/login?msg="+msg;		// redirect하는 경우엔 GET방식으로 감 - 위에있는 GetMapping이 받음
		}
		// 2-2. id와 pwd가 일치하면
		// 	2-2-1. 세션 객체생성 후 세션에 id를 설정 후 저장
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		
		System.out.println("sessionId = " + session.getAttribute("id"));
		//	2-2-2. 쿠키 생성&저장
		if(rememberId) {
			Cookie cookie = new Cookie("id", id);			// 쿠키 생성
			response.addCookie(cookie);						// 응답에 저장
		}else {
			Cookie cookie = new Cookie("id", id);			// 혹시 있을지 모를,
			cookie.setMaxAge(0);							// 쿠키 삭제
			response.addCookie(cookie);						// 응답에 저장
		}
		
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			String name = cookie.getName();
			String value = cookie.getValue();
			
			System.out.printf("[cookie] name=%s, value=%s\n", name, value);
		}
		
		//			3. 홈으로 이동
//		return "redirect:/";
		
		toURL = toURL == null || toURL.equals("") ? "/" : toURL;
		
		return "redirect:"+toURL;
	}
	
	@GetMapping("/logout")
	String logOut(HttpSession session) {	// session은 request로부터 얻어도 되지만 매개변수에 직접 session을 쓰면 스프링이 자동으로 session을 넣어준다.
		// 1. 세션을 종료
		session.invalidate();
		// 2. 홈으로 이동
		return "redirect:/";
	}

	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd);
	}
}
