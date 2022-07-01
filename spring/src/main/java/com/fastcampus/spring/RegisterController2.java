
// ���ε� URL�� ���� �κ��� @RequestMapping���� Ŭ������ ����
//		- ������ ��⺰�� ������ ����� �� �ִ�.
// Ÿ�� ��ȯ�� ���� @InitBinder ���

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
@RequestMapping("/register2")
public class RegisterController2 {

	// String�� ��¥�������� �ٲٴ� �޼��� ��� - �ش� ��Ʈ�ѷ� �������� ��� ����
	// DS�� Ÿ���� ��ȯ�� �� @InitBinder�� ���� �޼��尡 �ִ��� ���� Ȯ���ؼ� ������ ���⿡ �°� ��ȯ�ϰ�, ������ �������� �������ִ� ����Ʈ ��ȯ�⸦ �̿��Ѵ�.
	// @InitBinder�� ���� �޼��忡�� �������� �����ϴ� �����͸� �̿��ؼ� Ÿ���� ��ȯ�Ѵ�.
	// �޼��� �̸��� ������� �ϰ�, �Ű������� �����̴�.
	// @InitBinder�� �ش� ��Ʈ�ѷ� �������� ��� �����ϴ�. (@ExceptionHandler ó��)
	@InitBinder
	void toDate(WebDataBinder binder) {
		// UserŬ������ Date birth�� @DateTimeFormat(pattern="yyyy-MM-dd")private Date birth; �� ����� �Ʒ� �� �ٰ� ���� ����� �Ѵ�.
		//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));	// ��ȯ��ϱ� ��� - false�� �� ���� ����Ұ��� �����ϴ°�
		
		// registerForm�� hobby���� ��#��ȭ#���� �Է� �� ������ ������ ������ �̰��� String[]���� ������ hobby[0]�� ��#��ȭ#���� �� �� ����.
		// �̰��� �����ڸ� �������� �迭�� �ε����� ���� �������� �Ʒ� �ڵ带 �߰����ش�.
		// "hobby" ������ �ʵ带 ������ �� �ִ�. (������ ����ʵ忡 ����)
		binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));	
		
		// �������� ��ϵ� ������ ��� ����
		// �������� �� ��Ͽ� ��ϵ� ��ȯ����� �̿��Ͽ� Ÿ���� �ڵ� ��ȯ�� �ִ°��̴�.
		ConversionService conversionService = binder.getConversionService();
		System.out.println("conversionService = " + conversionService);
	}
	
	@GetMapping("/add1")
	public String register() {
		return "registerForm";
	}
	
	// BindingResult�� �����ָ� Ÿ�� ��ȯ �������� �� ������������ �Ѿ�� ���� �ƴ� ���ε� ����� ��Ʈ�ѷ����� �ְ� ��Ʈ�ѷ��� �װ��� ������� ó���ϰ� �ϴ� ����.
	// ���� �������� �Ѿ�� �ʴ°��� ��, ������ �߻��� ����. -> sysout ���� �� Ȯ���� �� �ִ�.
	@PostMapping("/save1")	
	public String save(User user, BindingResult result, Model m) throws Exception{		// BindingResult�� �ش� ��ü �ٷ� ���� ����� �Ѵ�.
		System.out.println("result = " + result);
		System.out.println("user = " + user);
		
		// 1. ��ȿ�� �˻�
		if(!isValid(user)) {

			String msg = URLEncoder.encode("������ �߸� �Է��ϼ̽��ϴ�.", "utf-8");
			
			 m.addAttribute("msg", msg);
			 return "redirect:/register/add";		
		}
		
		// 2. DB�� �ű�ȸ�� ������ ����
		return "registerInfo";
	}

	private boolean isValid(User user) {
		
		return true;
	}
}
