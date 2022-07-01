
// - try~catch ������ ����ó�� �ϴ°��� @ExceptionHandler�� ó���ϱ�
// - @ExceptionHandler�� @ExceptionHandler�� �ۼ��Ǿ��ִ� �ش� Ŭ���� ������ �۵��Ѵ�. 

package com.fastcampus.spring;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class _28_ExceptionController2 {
	
	
	// try~catch ��ó�� ��� �߰��� �� �ִ�.
	// �޼��� �ۼ��� @RequestMapping �޼��� �ۼ��ϵ��� �ϸ� �ȴ�.
	// �迭�� ������ ���� �����ϴ�.
	@ExceptionHandler({Exception.class, FileNotFoundException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)	// 200�� �����ڵ带 500������ �ٲٴ� ��.
	String catcher1(Exception ex, Model m) {		
		System.out.println("���������� ó��");
//		m.addAttribute("ex", ex);			// error�������� isErrorPage="true" ������ �ϸ� �� �ʿ����.
		return "error";						// ���ܰ� �߻��ϰ� ó��jsp�������� �� ó�� �ߴµ� ���� ���°� 200���밡 ���´�. (�����ϱ� 200���밡 ������ �ȵ�)
	}
	@ExceptionHandler(NullPointerException.class)
	String catcher2(Exception ex, Model m) {
		System.out.println("���������� ó��");
		System.out.println("m = " + m);		// ���⿡ �ִ� �𵨰� @RequestMapping �޼��忡 �ִ� ���� ���� �ٸ� ��ü�̴�.
		m.addAttribute("ex", ex);			// �𵨰�ü�� ����ϸ� �� �ȿ� �ִ� ������� ��µȴ�.
		return "error";
	}
//--------------------------------------------------------------------------------------
	// @RequestMapping���� ���ܰ� �߻��ϸ� @ExceptionHandler�� ó���� �Ѵ�.
	@RequestMapping("/ex3")
	String main() throws Exception {
		
		throw new Exception("���ܰ� �߻��߽��ϴ�.");					
	}
	
	@RequestMapping("/ex4")
	String main2(Model m) throws Exception {
		m.addAttribute("msg", "message from ExceptionController.main()");
		throw new NullPointerException("���ܰ� �߻��߽��ϴ�.");
	}
	
	@RequestMapping("/ex5")
	String main3() throws Exception {

		throw new FileNotFoundException("���ܰ� �߻��߽��ϴ�.");		// ��� ��ġ�ϴ°� ������ ���󿹿ܰ� �޴´�.
	}
	
}


