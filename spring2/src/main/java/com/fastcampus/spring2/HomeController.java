package com.fastcampus.spring2;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.*;
import org.springframework.web.context.support.*;

import javax.servlet.*;
import javax.servlet.http.*;

@Controller
public class HomeController {
	
//	@Autowired
//	WebApplicationContext servletAC; // Servlet AC는 @Autowired로 주입 받을 수 있다.

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, HttpServletRequest request, Model model) {
		
		// servlet AC 가져오려면 request.getServletContext() 하면 되지만,
		// root AC 가져오려면, 컨트롤러는 HttpServlet을 상속받지 않아서 아래와 같이 해야함.
//		ServletContext sc = request.getSession().getServletContext(); // ApplicationContextFacade
//		WebApplicationContext rootAC = WebApplicationContextUtils.getWebApplicationContext(sc); // Root AC를 가져오는 방법
//
//		System.out.println("webApplicationContext = " + rootAC);
//		System.out.println("servletAC = " + servletAC);
//
//		System.out.println("rootAC.getBeanDefinitionNames() = " + Arrays.toString(rootAC.getBeanDefinitionNames()));
//		System.out.println("servletAC.getBeanDefinitionNames() = " + Arrays.toString(servletAC.getBeanDefinitionNames()));
//
//		System.out.println("rootAC.getBeanDefinitionCount() = " + rootAC.getBeanDefinitionCount());
//		System.out.println("servletAC.getBeanDefinitionCount() = " + servletAC.getBeanDefinitionCount());
//
//		System.out.println("servletAC.getParent()==rootAC = " + (servletAC.getParent() == rootAC)); // servletAC.getParent()==rootAC = true
		
		return "index";
	}
}
