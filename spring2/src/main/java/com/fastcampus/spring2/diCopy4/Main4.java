
// - - ��ü�� �ڵ� �����ϱ� - @Autowired (Type���� ã�Ƽ� ����)

package com.fastcampus.spring2.diCopy4;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.reflect.ClassPath;


@Component 
class Car{
	
//	@Resource Engine engine;
//	@Resource Door door;
	@Autowired Engine engine;
	@Autowired Door door;
	
	@Override
	public String toString() {
		return "Car [engine=" + engine + ", door=" + door + "]";
	}
}
@Component 
class SportsCar extends Car {}
@Component 
class Truck extends Car {}
@Component 
class Engine {}
@Component 
class Door {}

class AppContext {
	
	Map map;
	
	AppContext(){
		map = new HashMap();
		doComponentScan();
		doAutowired();
		doResource();
	}
	
	private void doResource() {
		try {
			for(Object bean : map.values()) {
				for(Field fld : bean.getClass().getDeclaredFields()) {
					if(fld.getAnnotation(Resource.class) != null) {			// byName
						fld.set(bean, getBean(fld.getName()));				// car.engin = obj;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private void doAutowired() {
		// map�� ����� ��ü�� iv�߿� @Autowired�� �پ� ������
		// map���� iv�� Ÿ�Կ� �´� ��ü�� ã�Ƽ� ����(��ü�� �ּҸ� iv������)
		try {
			for(Object bean : map.values()) {
				for(Field fld : bean.getClass().getDeclaredFields()) {
					if(fld.getAnnotation(Autowired.class) != null) {		// byName
						fld.set(bean, getBean(fld.getType()));				// car.engin = obj;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	private void doComponentScan() {
		try {
			// 1. ��Ű�� ���� Ŭ���� ����� �����´�.
			// 2. �ݺ������� Ŭ������ �ϳ��� �о�ͼ� @Component�� �پ��ִ��� Ȯ���Ѵ�.
			// 3. @Component�� �پ������� ��ü�� �����ؼ� map�� �����Ѵ�.
			ClassLoader classLoader = AppContext.class.getClassLoader();		// Ŭ�����δ��� ���� ���� ������
			ClassPath classPath = ClassPath.from(classLoader);					// Ŭ����path ��������
			
			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.spring2.diCopy4");	// Ŭ����path���� Ŭ���� ��� ��������
			
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

public class Main4 {

	public static void main(String[] args) throws Exception {

		AppContext ac = new AppContext();						
		
		Car car = (Car)ac.getBean("car");
		Car car2 = (Car)ac.getBean(Car.class);			// Ÿ�� ã���ϸ� �� ���� �ȵǳ�
		Engine engine = (Engine)ac.getBean(Engine.class);
		Door door = (Door)ac.getBean(Door.class);
		
		// �������� ��ü�� ����
//		car.engine = engine;
//		car.door = door;


		System.out.println("car = " + car);
		System.out.println("car2 = " + car2);
		System.out.println("engine = " + engine);
		System.out.println("door = " + door);
	}

}