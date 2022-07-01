
// - 스프링이 개별 데이터로 요청받은 데이터들을 컨트롤러 main메서드의 객체 매개변수와 연결하는 과정.

package com.fastcampus.spring;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

public class _17_SetterCall {

	public static void main(String[] args) throws Exception{
		
		// URL요청으로 넘어온 값
		Map<String, String> map = new HashMap<>();
		map.put("year", "2022");
		map.put("month", "3");
		map.put("day", "13");
		
		// 컨트롤러의 main메서드의 매개변수는 객체로 선언되어있어서 스프링은 그 객체의 정보를 불러온다.
		Class<?> type = Class.forName("com.fastcampus.spring._17_MyDate");
		
		// MyDate인스턴스를 생성하고, map의 값으로 초기화한다.
		Object obj = dataBind(map, type);
		System.out.println("obj = " + obj);
	}

	private static Object dataBind(Map<String, String> map, Class<?> clazz) throws Exception{
		
		// 1. _17_MyDate인스턴스 생성
//		Object obj = clazz.newInstance();	// deprecated method
		Object obj = clazz.getDeclaredConstructor().newInstance(new Object[0]);
		
		// 2. _17_MyDate인스턴스의 setter를 호출해서, map의 값으로 _17_MyDate를 초기화
		//		2-1. _17_MyDate의 모든 iv를 돌면서 map에 있는지 찾는다.
		//		2-2. 찾으면, 찾은 값을 setter로 값을 객체에 저장한다.
		
		Field[] ivArr = clazz.getDeclaredFields();
		System.out.println(Arrays.toString(ivArr));

		for(int i=0 ; i<ivArr.length ; i++) {
			String name = ivArr[i].getName();
			Class<?> type = ivArr[i].getType();
			
			// map에 같은 이름의 key가 있으면 가져와서 setter호출
			Object value = map.get(name);	// 못찾으면 value의 값은 null
			Method method = null;
			
			try {
				if(value==null) 
					continue;
				method = clazz.getDeclaredMethod(getSetterName(name), type);	// setter의 정보 얻기
				System.out.println("method = " + method);
				method.invoke(obj, convertTo(value, type));	// obj의 setter를호출
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
		// value의 타입과 type의 타입이 같으면 그대로 반환
		if(value==null || type==null || type.isInstance(value))
			return value;
		
		// value의 타입과 type이 다르면, 변환해서 반환
		if(String.class.isInstance(value) && type==int.class) // String -> int
			return Integer.valueOf(""+value);

		return value;
	}
}
