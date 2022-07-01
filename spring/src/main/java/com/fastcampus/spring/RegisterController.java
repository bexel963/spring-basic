package com.fastcampus.spring;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

//	@RequestMapping("/register/add")	// �Ʒ��ٰ� ����
	@RequestMapping(value="/register/add0", method= {RequestMethod.GET, RequestMethod.POST})	// �迭�� ��� ���� - GET, POST��� �Ѵ� ����Ѵ�.
//	@GetMapping("/register/add")
	public String register() {
		return "registerForm";
	}
	
//	@RequestMapping("/register/save")
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)	// method�� ������ ������θ� URL��û�� �� �ְ� �Ѵ�.
	@PostMapping("/register/save0")	// ���ٰ� ����, sts 4.3���� ���� - ������ ������ pom.xml���� ����
	public String save(User user, Model m) throws Exception{
		
		// 1. ��ȿ�� �˻�
		if(!isValid(user)) {
			// �������� ���� �ѱ��� �Է��ϸ� �������� URL�� �ڵ����� ���ڵ��� ���ش�. - �׷��� �Է��ѵ��� ������ ���δ�.
			// ��Ʈ�ѷ����� �ѱ��� �̿��� ���� URL�� ����� �̰Ŵ� �������� URL�� ���ڵ� �� ���ش�. - ���ڰ� ������.
			// �׷��� �̷� ��� ��Ʈ�ѷ����� URL���ڵ��� ���� ������Ѵ�.
			// �̶� ����ϴ� ���� URLEncoder.encode()�̴�. - ����ó�� �������.
			// �׸��� �̰��� �޴� jsp������ URLDecoder.decode()�� �������.
			String msg = URLEncoder.encode("������ �߸� �Է��ϼ̽��ϴ�.", "utf-8");
			
			 m.addAttribute("msg", msg);
			 return "redirect:/register/add0";		// redirect�� �ּ�â�� �ٲ��.(�������� �ڵ����� ���ο� ��û�� �ϱ� ������)
//			 return "forward:/register/add0";		// forward�� �ּ�â�� �� �ٲ��.
		
			// msg�� model�� �ִ� ��� ���� �Ʒ� ����� �ִ�. 
			// ���� model�� view�� �����ϱ� ���� ���ε� redirect(�ٽ� ��û)�� �Ἥ ������ ��쿡�� model�� �Ἥ ������ �� ��������.
			// �� model�� redirect:/register/add ��û�� ������ ����.
			// �� model�� /register/save ��û�� ���� ���̱� �����̴�.
			// �׷����� �������� ���� ������ �ڵ����� �Ʒ� ����ó�� �ٲ��ش�.
			//			- model�� ����� �����͸� URL�ڿ� �ٿ��ش�.
			// ����� �� ���ٰ� �Ʒ� ������ ���� �ڵ��̴�.
//			return "redirect:/register/add?msg=" + msg;	// URL ���ۼ�(reWriting)
		}
		
		// 2. DB�� �ű�ȸ�� ������ ����
		return "registerInfo";
	}

	private boolean isValid(User user) {
		
		return false;
	}
}
