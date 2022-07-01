
// - �ٸ� ��Ʈ�ѷ��鿡�� �߻��� ���ܸ� ���⼭ �������� ó���� �� �ִ�.
// - ��Ʈ�ѷ� ���ο� @ExceptionHandler�� ��ü������ ������ �ִٸ� ����� ���� ���ܸ� ó���Ѵ�.
// ��� : �������� ó���ؾ��ϴ� ���ܸ� ó���ϴ� ��Ʈ�ѷ��� �����, ���������� ó���ؾ� �ϴ� ���ܴ� �ش� ��Ʈ�ѷ��� �ۼ��Ѵ�.

package com.fastcampus.spring;

import java.io.FileNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

// ��Ű���� ������ �� �ִ�. -> ������ ��Ű������ �����Ѵ�.
// ���� �� ���ָ� ��� ��Ű���� ����ȴ�.
@ControllerAdvice("com.fastcampus.spring9999")	// ���� ���� ó�� Ŭ���� ����� ������̼�
public class _28_GlobalCatcher {
	
	@ExceptionHandler({Exception.class, FileNotFoundException.class})
	String catcher1(Exception ex, Model m) {
		System.out.println("���� Ŭ�������� ó��");

		m.addAttribute("ex", ex);
		return "error";
	}
	@ExceptionHandler(NullPointerException.class)
	String catcher2(Exception ex, Model m) {
		System.out.println("���� Ŭ�������� ó��");

		m.addAttribute("ex", ex);
		return "error";
	}
}
