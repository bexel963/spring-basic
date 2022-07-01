
// MVC패턴의 원리를 보여주는 프로그램1
//		- 컨트롤러 클래스 만들고 이와 map으로 표현
// 		- 스프링이 모델을 생성하고, 컨트롤러를 호출하면서 생성한 모델을 넘겨주고, 컨트롤러는 작업결과를 모델에 저장하고 view이름을 반환한다.
//		- 스프링은 반환받은 model과 view 이름을 렌더링하여 화면에 출력한다.

package com.fastcampus.spring;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

// 컨트롤러 역할
class ModelController{
	
	public String main(HashMap map) {
		map.put("id", "asdf");
		map.put("pwd", "1234");
		
		return "txtView2";
	}
}
// DispatcherServlet 역할
public class _12_MethodCall1 {

	public static void main(String[] args) throws IOException {
		
		HashMap map = new HashMap();
		System.out.println("before : " + map);
		
		ModelController mc = new ModelController();		// 브라우저가 요청을 하면 DispatcherServlet은 해당 컨트롤러의 객체를 생성한 후
		String viewName = mc.main(map);					// 컨트롤러의 매핑된 메소드를 호출한다.
		
		System.out.println("after : " + map);
		
		render(map, viewName);	// 작업 결과가 담겨있는 model과 컨트롤러가 넘겨준 view를 렌더링 한다.
	}

	static void render(HashMap map, String viewName) throws IOException {

		String result = "";
		
		// 1. view의 내용을 한줄씩 읽어서 하나의 문자열로 만든다.
		Scanner sc = new Scanner(new File(viewName + ".txt"));
		
		while(sc.hasNextLine()) {
			result += sc.nextLine() + System.lineSeparator();
		}
		
		// 2. map에 담긴 key를 하나씩 읽어서 template의 ${key}를 value로 바꾼다.
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();

			// 3. replace()로 key를 value 치환한다.
			result = result.replace("${"+key+"}", (String)map.get(key));
		}
		
		// 4.렌더링 결과를 출력한다.
		System.out.println(result);
	}
}
