
// �����Ͱ���(�ڵ����� ��������) - setValidator()�� @InitBinder�� ����ϰ�, @Valid�� ����Ѵ�.
// �۷ι� ������ ��� - �۷ι������� Ŭ���� ���� �� servlet-context.xml�� ����Ͽ� ���.
// �����޽���.properties�� servlet-context.xml�� ����Ͽ� jsp���Ͽ��� ����� �����޽���.properties�� ����ϱ�.


package com.fastcampus.spring;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController4 {

	@InitBinder
	void toDate(WebDataBinder binder) {	

		binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));	
		
//		binder.setValidator(new UserValidator());	// (1)UserValidator��ü�� �����ؼ� WebDataBinder�� ���� validator�� ���
													// �۷ι������� ����Ϸ��� setValidator�� ����ϸ� �� �ȴ�.
//		binder.addValidators(new UserValidator());	// �۷ι������⵵ ����ϸ鼭 ���� �����⵵ ��� �Ϸ��� add�� ������ �߰����ش�.
													// �۷ι������� ����� servlet-context.xml���� �Ѵ�.
		List<Validator> validatorList = binder.getValidators();		// ��ϵ� ������ ��� ��������
		System.out.println("validatorList = " + validatorList);
	}
	
	@GetMapping("/save")
	public String register() {
		return "registerForm2";
	}
							// (2)������ ��ü �տ� @Vaild�� �ٿ��ش�.(�������� ���°���)
	@PostMapping("/save")	//    @Valid�� ����Ϸ��� pom.xml���Ͽ� validation-api�� �߰��ؾ��Ѵ�. (����-maven repository�˻� -> validation�˻� -> ����)
	public String save(@Valid User user, BindingResult result, Model m) throws Exception{		
		System.out.println("result = " + result);
		System.out.println("user = " + user);
		

		if(result.hasErrors()) {
			return "registerForm2";
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
