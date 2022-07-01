
// �ܺο��� private�޼ҵ� ȣ���ϱ� - reflection �̿�.

package com.fastcampus.spring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class _01_PrivateMethodCall {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
//		_01_Hello hello = new _01_Hello();
//		hello.main();	// private�� �ܺο��� ȣ�� �Ұ�
		
																				// HelloŬ������ Class��ü(Ŭ������ ������ ��� �ִ� ��ü)�� ���´�.
		Class helloClass = Class.forName("com.fastcampus.spring._01_Hello");	// forName�� Ŭ������ �� ã���� �������� ������ ����ó�� �������.
		
		_01_Hello hello = (_01_Hello)helloClass.newInstance();
		
		Method main = helloClass.getDeclaredMethod("main");					// HelloŬ������ main �޼����� ������ �����´�.				
																			// Method�� �޼ҵ带 ������ �� ����Ѵ�.
		main.setAccessible(true);
		
		main.invoke(hello);	// = hello.main();          �޼ҵ��.invoke(��ü��);
	}
}
