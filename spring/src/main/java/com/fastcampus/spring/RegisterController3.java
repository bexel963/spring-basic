
// �����Ͱ���(�������� ������) - Validator�� ���� �����ϰ� ���� ȣ�� 


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
		
		UserValidator userValidator = new UserValidator();	// Validator�������̽��� ������ UserValidator Ŭ������ �ۼ��ؾ� �Ѵ�.
		userValidator.validate(user, result);				// �����Ϸ��� ��ü�� BindingResult�� ������ ����� �Ѱ��ش�. 
															// - BindingResult�� Errors �������̽��� �ڼ��̶� �ٷ� �ᵵ �ȴ�.
		// User ��ü�� ������ ��� ������ ������, registerForm�� �̿��ؼ� ������ ������� �Ѵ�.
		// ������ ��Ʈ�ѷ����� ��ȿ�� �˻� �ڵ带 �ۼ��� ��(�Ʒ� �ּ��� if��)�� ������ ������� ��ü�Ѵ�(���ɻ� �и�)
		if(result.hasErrors()) {
			return "redirect:/register/add";
		}
//		if(!isValid(user)) {
//
//			String msg = URLEncoder.encode("������ �߸� �Է��ϼ̽��ϴ�.", "utf-8");
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
