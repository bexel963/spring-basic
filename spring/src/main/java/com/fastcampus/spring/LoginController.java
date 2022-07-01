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
		System.out.println("rememberId = " + rememberId);	// �Ű����� Ÿ���� String���� �ϸ� on/off�� �޴´�.
		
		
		// 1. id�� pwd�� Ȯ��
		if(!loginCheck(id, pwd)) {
		// 2-1. ��ġ���� ������ loginForm���� �̵�
			String msg = URLEncoder.encode("id �Ǵ� pwd�� ��ġ���� �ʽ��ϴ�.", "utf-8");
			
			return "redirect:/login/login?msg="+msg;		// redirect�ϴ� ��쿣 GET������� �� - �����ִ� GetMapping�� ����
		}
		// 2-2. id�� pwd�� ��ġ�ϸ�
		// 	2-2-1. ���� ��ü���� �� ���ǿ� id�� ���� �� ����
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		
		System.out.println("sessionId = " + session.getAttribute("id"));
		//	2-2-2. ��Ű ����&����
		if(rememberId) {
			Cookie cookie = new Cookie("id", id);			// ��Ű ����
			response.addCookie(cookie);						// ���信 ����
		}else {
			Cookie cookie = new Cookie("id", id);			// Ȥ�� ������ ��,
			cookie.setMaxAge(0);							// ��Ű ����
			response.addCookie(cookie);						// ���信 ����
		}
		
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			String name = cookie.getName();
			String value = cookie.getValue();
			
			System.out.printf("[cookie] name=%s, value=%s\n", name, value);
		}
		
		//			3. Ȩ���� �̵�
//		return "redirect:/";
		
		toURL = toURL == null || toURL.equals("") ? "/" : toURL;
		
		return "redirect:"+toURL;
	}
	
	@GetMapping("/logout")
	String logOut(HttpSession session) {	// session�� request�κ��� �� ������ �Ű������� ���� session�� ���� �������� �ڵ����� session�� �־��ش�.
		// 1. ������ ����
		session.invalidate();
		// 2. Ȩ���� �̵�
		return "redirect:/";
	}

	private boolean loginCheck(String id, String pwd) {
		return "asdf".equals(id) && "1234".equals(pwd);
	}
}
