
// MVC������ ������ �����ִ� ���α׷�2
// 		- ��Ʈ�ѷ��� ������ Reflection API�� �̿��Ͽ� �˾Ƴ��� �۾��ϰ��� �ϴ� �޼��带 ȣ��
// 		- main�޼��� ȣ���ϴ� �κ��� �ϵ��ڵ�

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

// DispatcherServlet����
public class _12_MethodCall2 {

	public static void main(String[] args) throws Exception {
		
		// 1. YoilTellerMVC�� ��ü�� ����
		Class clazz = Class.forName("com.fastcampus.spring._10_YoilTeller_MVC");
		Object obj = clazz.newInstance();
		
		// 2. main�޼����� ������ �����´�.
		Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
		
		// 3. Model�� ����, BindingAwareModelMap�� ���� ����ü�̴�. Model�� �������̽�
		Model model = new BindingAwareModelMap();
		System.out.println("[before] model = " + model);
		
		// 4. ��Ʈ�ѷ��� �޼��� ȣ��
		// String viewName = obj.main(2022, 3, 12, model);
		String viewName = (String)main.invoke(obj, new Object[] {2022, 3, 12, model});	// Reflection API�� �̿��� obj�� main�޼��� ȣ��
		System.out.println("viewName = " + viewName);
		
		System.out.println("[after] model = " + model);
		
		// 5. ��Ʈ�ѷ��� �۾��� ����� ������
		render(model, viewName);
	}

	static void render(Model model, String viewName) throws IOException {

		String result = "";
		
		// 1. view�� ������ ���پ� �о �ϳ��� ���ڿ��� �����.
		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/"+viewName+".jsp"), "utf-8");
		
		while(sc.hasNextLine())
			result += sc.nextLine() + System.lineSeparator();
		
		// 2. model�� map���� ��ȯ
		Map map = model.asMap();
		
		// 3. key�� �ϳ��� �о template�� ${key}�� value�� �ٲ۴�.
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();

			result = result.replace("${"+key+"}", ""+map.get(key));
		}
		
		// 4. ������ ����� ����Ѵ�.
		System.out.println(result);
		
		
	}
}
