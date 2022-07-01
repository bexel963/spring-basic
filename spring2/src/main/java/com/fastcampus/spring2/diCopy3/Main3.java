
// - 자동 객체 등록하기 - guava 라이브러리 pom.xml에 추가 (어노테이션으로 자동으로 객체 저장소에 객체 추가)

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
			// 1. 패키지 내의 클래스 목록을 가져온다.
			// 2. 반복문으로 클래스를 하나씩 읽어와서 @Component가 붙어있는지 확인한다.
			// 3. @Component이 붙어있으면 객체를 생성해서 map에 저장한다.
			ClassLoader classLoader = AppContext.class.getClassLoader();		// 클래스로더에 대한 정보 얻어오기
			ClassPath classPath = ClassPath.from(classLoader);					// 클래스path 가져오기
			
			Set<ClassPath.ClassInfo> set = classPath.getTopLevelClasses("com.fastcampus.spring2.diCopy3");	// 클래스path에서 클래스 목록 가져오기
			
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

public class Main3 {

	public static void main(String[] args) throws Exception {

		AppContext ac = new AppContext();						
		
		Car car = (Car)ac.getBean("car");				// byName으로 객체를 검색
		Car car2 = (Car)ac.getBean(Car.class);			// byType으로 객체를 검색
		Engine engine = (Engine)ac.getBean("engine");
		
		System.out.println("car = " + car);
		System.out.println("engine = " + engine);
	}

}