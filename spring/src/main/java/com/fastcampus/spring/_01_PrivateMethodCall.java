
// 외부에서 private메소드 호출하기 - reflection 이용.

package com.fastcampus.spring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class _01_PrivateMethodCall {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
//		_01_Hello hello = new _01_Hello();
//		hello.main();	// private라서 외부에서 호출 불가
		
																				// Hello클래스의 Class객체(클래스의 정보를 담고 있는 객체)를 얻어온다.
		Class helloClass = Class.forName("com.fastcampus.spring._01_Hello");	// forName은 클래스를 못 찾으면 에러나기 때문에 예외처리 해줘야함.
		
		_01_Hello hello = (_01_Hello)helloClass.newInstance();
		
		Method main = helloClass.getDeclaredMethod("main");					// Hello클래스의 main 메서드의 정보를 가져온다.				
																			// Method는 메소드를 참조할 때 사용한다.
		main.setAccessible(true);
		
		main.invoke(hello);	// = hello.main();          메소드명.invoke(객체명);
	}
}
