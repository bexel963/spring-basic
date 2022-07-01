
// - �������� ���� �����ͷ� ��û���� �����͵��� ��Ʈ�ѷ� main�޼����� ��ü �Ű������� �����ϴ� ����.

package com.fastcampus.spring;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class _17_SetterCall {

	public static void main(String[] args) throws Exception{
		
		// URL��û���� �Ѿ�� ��
		Map<String, String> map = new HashMap<>();
		map.put("year", "2022");
		map.put("month", "3");
		map.put("day", "13");
		
		// ��Ʈ�ѷ��� main�޼����� �Ű������� ��ü�� ����Ǿ��־ �������� �� ��ü�� ������ �ҷ��´�.
		Class<?> type = Class.forName("com.fastcampus.spring._17_MyDate");
		
		// MyDate�ν��Ͻ��� �����ϰ�, map�� ������ �ʱ�ȭ�Ѵ�.
		Object obj = dataBind(map, type);
		System.out.println("obj = " + obj);
	}

	private static Object dataBind(Map<String, String> map, Class<?> clazz) throws Exception{
		
		// 1. _17_MyDate�ν��Ͻ� ����
//		Object obj = clazz.newInstance();	// deprecated method
		Object obj = clazz.getDeclaredConstructor().newInstance(new Object[0]);
		
		// 2. _17_MyDate�ν��Ͻ��� setter�� ȣ���ؼ�, map�� ������ _17_MyDate�� �ʱ�ȭ
		//		2-1. _17_MyDate�� ��� iv�� ���鼭 map�� �ִ��� ã�´�.
		//		2-2. ã����, ã�� ���� setter�� ���� ��ü�� �����Ѵ�.
		
		Field[] ivArr = clazz.getDeclaredFields();
		System.out.println(Arrays.toString(ivArr));

		for(int i=0 ; i<ivArr.length ; i++) {
			String name = ivArr[i].getName();
			Class<?> type = ivArr[i].getType();
			
			// map�� ���� �̸��� key�� ������ �����ͼ� setterȣ��
			Object value = map.get(name);	// ��ã���� value�� ���� null
			Method method = null;
			
			try {
				if(value==null) 
					continue;
				method = clazz.getDeclaredMethod(getSetterName(name), type);	// setter�� ���� ���
				System.out.println("method = " + method);
				method.invoke(obj, convertTo(value, type));	// obj�� setter��ȣ��
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return obj;
		
	}
	private static String getSetterName(String name) {
		return "set" + StringUtils.capitalize(name);
	}

	private static Object convertTo(Object value, Class<?> type) {
		// value�� Ÿ�԰� type�� Ÿ���� ������ �״�� ��ȯ
		if(value==null || type==null || type.isInstance(value))
			return value;
		
		// value�� Ÿ�԰� type�� �ٸ���, ��ȯ�ؼ� ��ȯ
		if(String.class.isInstance(value) && type==int.class) // String -> int
			return Integer.valueOf(""+value);

		return value;
	}
}
