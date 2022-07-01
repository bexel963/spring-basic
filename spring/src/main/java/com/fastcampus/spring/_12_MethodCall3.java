
// MVC������ ������ �����ִ� ���α׷�3 - ��Ʈ�ѷ��� ������ Reflection API�� �̿��Ͽ� �˾Ƴ��� �۾��ϰ��� �ϴ� �޼��带 ȣ��
//								  main�޼��� ȣ���ϴ� �κ��� �������� ���� - �Ķ���� ������ �˾Ƴ� �ű⿡ �°� �����͸� ��ȯ�ؼ� �Ѱ��ش�.

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



// DispatcherServlet ����
public class _12_MethodCall3 {
	public static void main(String[] args) throws Exception {
		
		// 1. ���������� ��û�� �� ������ �� - request.getParameterMap()
		Map map = new HashMap();
		map.put("year", "2022");
		map.put("month", "3");
		map.put("day", "13");
		
		// 2. Ŭ����(��Ʈ�ѷ�) ������ �����´�.
		// 3. ������ Ŭ������ �޼ҵ� ������ ������ �� �� Ŭ������ ��ü(obj)�� �����Ѵ�.
		// 4. ������ �޼ҵ� �������� �Ķ���� ������ �Ķ����(paramArr) �迭�� ��´�.
		// 5. �Ķ���� ������ŭ�� ũ���� Object�迭(argArr)�� �����.
		// 6. �Ķ���� �迭���� �Ķ���� �̸�(key)�� �����Ͽ� �� �̸��� �´� ��(value)�� ��´�.
		// 7. �Ķ���� �迭���� �Ķ���� Ÿ���� �����Ͽ� ��(value)�� �Ķ���� Ÿ���� ���ؼ� �ٸ��� �Ķ���� Ÿ�Կ� �°� ��ȯ�ؼ� argArr�� �����Ѵ�.
		// 8. argArr�� ���ڷ� �ѱ�鼭 ��Ʈ�ѷ��� main�޼ҵ带 ȣ���Ѵ�.
		// 9. main�޼ҵ��� ��ȯ��(model, viewName)�� �޾Ƽ� �� ���� ������ �� �������� �����ش�.
		
		Model model = null;
		Class clazz = Class.forName("com.fastcampus.spring._10_YoilTeller_MVC");
		Object obj = clazz.newInstance();
		
		Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		
		Parameter[] paramArr = main.getParameters();
		Object[] argArr = new Object[main.getParameterCount()];		// ��Ʈ�ѷ��� main�޼ҵ�� �Ѱ��� ���ڵ��� ������ �迭
		
		for(int i=0 ; i<paramArr.length ; i++) {
			String paramName = paramArr[i].getName();
			Class paramType = paramArr[i].getType();
			Object value = map.get(paramName);	// map���� �� ã���� value�� null
			
			if(paramType == Model.class) {
				argArr[i] = model = new BindingAwareModelMap();
				System.out.println("[before] model = " + model);
			}else if(value != null) {
				argArr[i] = convertTo(value, paramType);	// value�� parameter�� Ÿ���� ���ؼ�, �ٸ��� ��ȯ�ؼ� ����
			}
		}
		System.out.println("paramArr = " + Arrays.toString(paramArr));
		System.out.println("argArr = " + Arrays.toString(argArr));
		
		String viewName = (String)main.invoke(obj, argArr);	// Controller�� main()�� ȣ��
		System.out.println("viewName = " + viewName);
		
		System.out.println("[after] model = " + model);
		
		render(model, viewName);
			
	}

	private static Object convertTo(Object value, Class paramType) {
		if(paramType==null || value==null || paramType.isInstance(value))	// Ÿ���� ������ �״�� ��ȯ
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