
// - �ڵ� ��ü ����ϱ� - guava ���̺귯�� pom.xml�� �߰� (������̼����� �ڵ����� ��ü ����ҿ� ��ü �߰�)

package com.fastcampus.spring2.diCopy3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;


@Component class Car{}
@Component class SportsCar extends Car {}
@Component class Truck extends Car {}
@Component class Engine {}

class AppContext {
	
	Map map;
	
	AppContext(){
		map = new HashMap();
		doComponentScan();
	}
	
	private void doComponentScan() {
		try {
			// 1. ��Ű�� ���� Ŭ���� ����� �����´�.
			// 2. �ݺ������� Ŭ������ �ϳ��� �о�ͼ� @Component�� �پ��ִ��� Ȯ���Ѵ�.
			// 3. @Component�� �پ������� ��ü�� �����ؼ� map�� �����Ѵ�.
			ClassLoader classLoader = AppContext.class.getClassLoader();		// Ŭ�����δ��� ���� ���� ������
			ClassPath classPath = ClassPath.from(classLoader);					// Ŭ����path ��������
			
			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.spring2.diCopy3");	// Ŭ����path���� Ŭ���� ��� ��������
			
			for(ClassPath.ClassInfo classInfo : set) {
				Class clazz = classInfo.load();
				Component component = (Component)clazz.getAnnotation(Component.class);
				if(component != null) {
					String id = StringUtils.uncapitalize(classInfo.getSimpleName());   
					map.put(id, clazz.newInstance());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// byName���� ��ü�� �˻�
	Object getBean(String key){
		
		return map.get(key);
	}
	
	// byType���� ��ü�� �˻�
	Object getBean(Class clazz) {
		
		for(Object obj : map.values()) {
			if(clazz.isInstance(obj)) {
				return obj;
			}
		}
		return null;
	}
}

public class Main3 {

	public static void main(String[] args) throws Exception {

		AppContext ac = new AppContext();						
		
		Car car = (Car)ac.getBean("car");				// byName���� ��ü�� �˻�
		Car car2 = (Car)ac.getBean(Car.class);			// byType���� ��ü�� �˻�
		Engine engine = (Engine)ac.getBean("engine");
		
		System.out.println("car = " + car);
		System.out.println("engine = " + engine);
	}

}