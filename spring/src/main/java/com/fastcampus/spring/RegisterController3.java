
// 데이터검증(수동으로 검증기) - Validator를 직접 생성하고 직접 호출 


package com.fastcampus.spring;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController3 {

	@InitBinder
	void toDate(WebDataBinder binder) {	

		binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));	
		
	}
	
	@GetMapping("/add2")
	public String register() {
		return "registerForm";
	}
	
	@PostMapping("/save2")	
	public String save(User user, BindingResult result, Model m) throws Exception{		
		System.out.println("result = " + result);
		System.out.println("user = " + user);
		
		UserValidator userValidator = new UserValidator();	// Validator인터페이스를 구현한 UserValidator 클래스를 작성해야 한다.
		userValidator.validate(user, result);				// 검증하려는 객체와 BindingResult로 검증한 결과를 넘겨준다. 
															// - BindingResult는 Errors 인터페이스의 자손이라 바로 써도 된다.
		// User 객체를 검증한 결과 에러가 있으면, registerForm을 이용해서 에러를 보여줘야 한다.
		// 기존에 컨트롤러에서 유효성 검사 코드를 작성한 것(아래 주석된 if문)을 데이터 검증기로 대체한다(관심사 분리)
		if(result.hasErrors()) {
			return "redirect:/register/add";
		}
//		if(!isValid(user)) {
//
//			String msg = URLEncoder.encode("정보를 잘못 입력하셨습니다.", "utf-8");
//			
//			 m.addAttribute("msg", msg);
//			 return "redirect:/register/add";		
//		}
		
		return "registerInfo";
	}

	private boolean isValid(User user) {
		
		return true;
	}
}
