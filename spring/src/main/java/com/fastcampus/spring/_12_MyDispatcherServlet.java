
// _12_MethodCall3�� ������ �̿��� �������� Controller ȣ���ϴ� ���α׷�

// @WebServlet�� @Controller + @RequestMapping �ΰ� ��ģ�� - Servlet�� �޼��� ������ ������ �ȵǰ� Ŭ���� �����θ� ������ �� �ִ�.
// Servlet�� HttpServlet�� ����� �޾ƾ��Ѵ�.
// �޼���� �� service��� �̸����� �ؾ��ϰ� �Ű������� request, response �ΰ��� �� �־�����Ѵ�.

package com.fastcampus.spring;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;


@WebServlet("/myDispatcherServlet")  
public class _12_MyDispatcherServlet extends HttpServlet {	
	@Override	
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// URL��û - http://localhost:8080/spring/myDispatcherServlet?year=2022&month=3&day=13 
		Map    map = request.getParameterMap();	// year=2022&month=3&day=13 �� ������ ������ �����Ѵ�.
		Model  model = null;
		String viewName = "";
		
		try {
			Class clazz = Class.forName("com.fastcampus.spring._10_YoilTeller_MVC");
			Object obj = clazz.newInstance();
			
      			// 1. main�޼����� ������ ��´�.
			Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);
			
      			// 2. main�޼����� �Ű����� ���(paramArr)�� �о �޼��� ȣ�⿡ ����� ���� ���(argArr)�� �����.
			Parameter[] paramArr = main.getParameters();
			Object[] argArr = new Object[main.getParameterCount()];

			for(int i=0;i<paramArr.length;i++) {
				String paramName = paramArr[i].getName();
				Class  paramType = paramArr[i].getType();
				Object value = map.get(paramName);

				// paramType�߿� Model�� ������, ���� & ���� 
				if(paramType==Model.class) {
					argArr[i] = model = new BindingAwareModelMap();
				} else if(paramType==HttpServletRequest.class) {
					argArr[i] = request;
				} else if(paramType==HttpServletResponse.class) {
					argArr[i] = response;					
				} else if(value != null) {  // map�� paramName�� ������,
					// value�� parameter�� Ÿ���� ���ؼ�, �ٸ��� ��ȯ�ؼ� ���� 
					String strValue = ((String[])value)[0];	// getParameterMap()���� ���� value�� String�迭�̹Ƿ� ��ȯ �ʿ� (map���� key�� string�ε� value�� string[] ��)
					argArr[i] = convertTo(strValue, paramType);				
				} 
			}
			
			// 3. Controller�� main()�� ȣ�� - YoilTellerMVC.main(int year, int month, int day, Model model)
			viewName = (String)main.invoke(obj, argArr); 	
			
		} catch(Exception e) {
			e.printStackTrace();
		}
				
		// 4. �ؽ�Ʈ ������ �̿��� rendering
		render(model, viewName, response);			
	} // main
	
	private Object convertTo(Object value, Class type) {
		if(type==null || value==null || type.isInstance(value)) // Ÿ���� ������ �״�� ��ȯ 
			return value;
		
		// Ÿ���� �ٸ���, ��ȯ�ؼ� ��ȯ
		if(String.class.isInstance(value) && type==int.class) { // String -> int
			return Integer.valueOf((String)value);
		} else if(String.class.isInstance(value) && type==double.class) { // String -> double
			return Double.valueOf((String)value);
		}
			
		return value;
	}
		
	private String getResolvedViewName(String viewName) {
		return getServletContext().getRealPath("/WEB-INF/views") +"/"+viewName+".jsp";
	}
	
	private void render(Model model, String viewName, HttpServletResponse response) throws IOException {
		String result = "";
		
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		// 1. ���� ������ ���پ� �о �ϳ��� ���ڿ��� �����.
		Scanner sc = new Scanner(new File(getResolvedViewName(viewName)), "utf-8");
//		Scanner sc = new Scanner(new File("src/main/webapp/WEB-INF/views/"+viewName+".jsp"),"utf-8");

		while(sc.hasNextLine())
			result += sc.nextLine()+ System.lineSeparator();
		
		// 2. model�� map���� ��ȯ 
		Map map = model.asMap();
		
		// 3.key�� �ϳ��� �о template�� ${key}�� value�ٲ۴�.
		Iterator it = map.keySet().iterator();
		
		while(it.hasNext()) {
			String key = (String)it.next();

			// 4. replace()�� key�� value ġȯ�Ѵ�.
			result = result.replace("${"+key+"}", map.get(key)+"");
		}
		
		// 5.������ ����� ����Ѵ�.
		out.println(result);
	}
}