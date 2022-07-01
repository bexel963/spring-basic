package com.fastcampus.spring2.aop;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.transaction.annotation.Transactional;

public class AopMain {

	public static void main(String[] args) throws Exception {
		
		MyAdvice myAdvice = new MyAdvice();
		
		Class myClass = Class.forName("com.fastcampus.spring2.aop.MyClass");
		Object obj = myClass.newInstance();
		
		for(Method m : myClass.getDeclaredMethods()) {
			myAdvice.invoke(m, obj, null);
			System.out.println();
		}
	}
}

class MyAdvice{
	Pattern p = Pattern.compile("a.*");
	
	boolean matches(Method m) {
		Matcher matcher = p.matcher(m.getName());
		return matcher.matches();
	}
	void invoke(Method m, Object obj, Object... args) throws Exception {
		
//		if(matches(m))													// 정규식 패턴에 맞는 메서드만 출력
		if(m.getAnnotation(Transactional.class) != null)				// 해당 에너테이션이 붙은 메서드만 출력
			System.out.println("[befer]{");
		m.invoke(obj,  args);
//		if(matches(m))
		if(m.getAnnotation(Transactional.class) != null)
			System.out.println("}[after]");
	}
}

class MyClass {
	
	@Transactional
	void aaa() {
		System.out.println("aaa() is called.");
	}
	void aaa2() {
		System.out.println("aaa2() is called.");
	}
	void ccc() {
		System.out.println("ccc() is called.");
	}
}
