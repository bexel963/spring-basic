
// - - 객체를 자동 연결하기 - @Autowired (Type으로 찾아서 연결)

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
		// map에 저장된 객체의 iv중에 @Autowired가 붙어 있으면
		// map에서 iv의 타입에 맞는 객체를 찾아서 연결(객체의 주소를 iv에저장)
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
			// 1. 패키지 내의 클래스 목록을 가져온다.
			// 2. 반복문으로 클래스를 하나씩 읽어와서 @Component가 붙어있는지 확인한다.
			// 3. @Component이 붙어있으면 객체를 생성해서 map에 저장한다.
			ClassLoader classLoader = AppContext.class.getClassLoader();		// 클래스로더에 대한 정보 얻어오기
			ClassPath classPath = ClassPath.from(classLoader);					// 클래스path 가져오기
			
			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.spring2.diCopy4");	// 클래스path에서 클래스 목록 가져오기
			
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
	
	// byName으로 객체를 검색
	Object getBean(String key){
		
		return map.get(key);
	}
	
	// byType으로 객체를 검색
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
		Car car2 = (Car)ac.getBean(Car.class);			// 타입 찾기하면 왜 연결 안되낭
		Engine engine = (Engine)ac.getBean(Engine.class);
		Door door = (Door)ac.getBean(Door.class);
		
		// 수동으로 객체를 연결
//		car.engine = engine;
//		car.door = door;


		System.out.println("car = " + car);
		System.out.println("car2 = " + car2);
		System.out.println("engine = " + engine);
		System.out.println("door = " + door);
	}

}