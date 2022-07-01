
// MVC패턴의 원리를 보여주는 프로그램3 - 컨트롤러의 정보를 Reflection API를 이용하여 알아내고 작업하고자 하는 메서드를 호출
//								  main메서드 호출하는 부분을 동적으로 구성 - 파라미터 정보를 알아내 거기에 맞게 데이터를 변환해서 넘겨준다.

package com.fastcampus.spring;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;



// DispatcherServlet 역할
public class _12_MethodCall3 {
	public static void main(String[] args) throws Exception {
		
		// 1. 브라우저에서 요청할 때 제공된 값 - request.getParameterMap()
		Map map = new HashMap();
		map.put("year", "2022");
		map.put("month", "3");
		map.put("day", "13");
		
		// 2. 클래스(컨트롤러) 정보를 가져온다.
		// 3. 가져온 클래스의 메소드 정보를 가져온 후 그 클래스의 객체(obj)를 생성한다.
		// 4. 가져온 메소드 정보에서 파라미터 정보를 파라미터(paramArr) 배열에 담는다.
		// 5. 파라미터 개수만큼의 크기의 Object배열(argArr)을 만든다.
		// 6. 파라미터 배열에서 파라미터 이름(key)을 추출하여 그 이름에 맞는 값(value)을 얻는다.
		// 7. 파라미터 배열에서 파라미터 타입을 추출하여 값(value)과 파라미터 타입을 비교해서 다르면 파라미터 타입에 맞게 변환해서 argArr에 저장한다.
		// 8. argArr을 인자로 넘기면서 컨트롤러의 main메소드를 호출한다.
		// 9. main메소드의 반환값(model, viewName)을 받아서 이 둘을 렌더링 후 브라우저에 보여준다.
		
		Model model = null;
		Class clazz = Class.forName("com.fastcampus.spring._10_YoilTeller_MVC");
		Object obj = clazz.newInstance();
		
		Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		
		Parameter[] paramArr = main.getParameters();
		Object[] argArr = new Object[main.getParameterCount()];		// 컨트롤러의 main메소드로 넘겨줄 인자들을 저장할 배열
		
		for(int i=0 ; i<paramArr.length ; i++) {
			String paramName = paramArr[i].getName();
			Class paramType = paramArr[i].getType();
			Object value = map.get(paramName);	// map에서 못 찾으면 value는 null
			
			if(paramType == Model.class) {
				argArr[i] = model = new BindingAwareModelMap();
				System.out.println("[before] model = " + model);
			}else if(value != null) {
				argArr[i] = convertTo(value, paramType);	// value와 parameter의 타입을 비교해서, 다르면 변환해서 저장
			}
		}
		System.out.println("paramArr = " + Arrays.toString(paramArr));
		System.out.println("argArr = " + Arrays.toString(argArr));
		
		String viewName = (String)main.invoke(obj, argArr);	// Controller의 main()을 호출
		System.out.println("viewName = " + viewName);
		
		System.out.println("[after] model = " + model);
		
		render(model, viewName);
			
	}

	private static Object convertTo(Object value, Class paramType) {
		if(paramType==null || value==null || paramType.isInstance(value))	// 타입이 같으면 그대로 반환
			return value;
		if(String.class.isInstance(value) && paramType==int.class) {
			return Integer.valueOf((String)value);	// String -> int
		}else if(String.class.isInstance(value) && paramType==double.class) {
			return Double.valueOf((String)value);
		}
		return value;
	}

	private static void render(Model model, String viewName) throws Exception{
		String result = "";
		
		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/"+viewName+".jsp"),"utf-8");
		
		while(sc.hasNextLine())
			result += sc.nextLine() + System.lineSeparator();
		
		Map map = model.asMap();
		
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();
			
			result = result.replace("${"+key+"}", ""+map.get(key));
		}
		
		System.out.println(result);
	}
	
	
	
	
	
	
	
	
	
}