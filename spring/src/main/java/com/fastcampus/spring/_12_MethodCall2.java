
// MVC패턴의 원리를 보여주는 프로그램2
// 		- 컨트롤러의 정보를 Reflection API를 이용하여 알아내고 작업하고자 하는 메서드를 호출
// 		- main메서드 호출하는 부분을 하드코딩

package com.fastcampus.spring;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

// DispatcherServlet역할
public class _12_MethodCall2 {

	public static void main(String[] args) throws Exception {
		
		// 1. YoilTellerMVC의 객체를 생성
		Class clazz = Class.forName("com.fastcampus.spring._10_YoilTeller_MVC");
		Object obj = clazz.newInstance();
		
		// 2. main메서드의 정보를 가져온다.
		Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		
		// 3. Model을 생성, BindingAwareModelMap은 모델의 구현체이다. Model은 인터페이스
		Model model = new BindingAwareModelMap();
		System.out.println("[before] model = " + model);
		
		// 4. 컨트롤러의 메서드 호출
		// String viewName = obj.main(2022, 3, 12, model);
		String viewName = (String)main.invoke(obj, new Object[] {2022, 3, 12, model});	// Reflection API를 이용한 obj의 main메서드 호출
		System.out.println("viewName = " + viewName);
		
		System.out.println("[after] model = " + model);
		
		// 5. 컨트롤러가 작업한 결과를 렌더링
		render(model, viewName);
	}

	static void render(Model model, String viewName) throws IOException {

		String result = "";
		
		// 1. view의 내용을 한줄씩 읽어서 하나의 문자열로 만든다.
		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/"+viewName+".jsp"), "utf-8");
		
		while(sc.hasNextLine())
			result += sc.nextLine() + System.lineSeparator();
		
		// 2. model을 map으로 변환
		Map map = model.asMap();
		
		// 3. key를 하나씩 읽어서 template의 ${key}를 value로 바꾼다.
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();

			result = result.replace("${"+key+"}", ""+map.get(key));
		}
		
		// 4. 렌더링 결과를 출력한다.
		System.out.println(result);
		
		
	}
}
