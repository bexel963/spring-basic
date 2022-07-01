
// - ��ü �����̳ʸ� �̿��� ��ü ����. (map���·� �Ǿ��ִ� ��ü ����Ҹ� ���� �� �ܺ����Ͽ��� �����͸� �о�� �� ��ü����ҿ� ��ü �߰�)

package com.fastcampus.spring2.diCopy2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

class Car{}
class SportsCar extends Car {}
class Truck extends Car {}
class Engine {}

class AppContext {
	Map map;			// ��ü �����
	
	AppContext() {
//		map = new HashMap();
		
//		map.put("car", new SportsCar());
//		map.put("engine", new Engine());
		
//		�� �ϵ� �ڵ��� �������� �ٲ۴�.
		
		try {
			// ������Ƽ�� �ִ� ������ map�� ����.
			Properties p = new Properties();
			
			p.load(new FileReader("config.txt"));			
			
			map = new HashMap(p);
			
			// �ݺ������� Ŭ���� �̸��� �� ��ü�� �����ؼ� �ٽ� map�� ����.
			for(Object key : map.keySet()) {				
				Class clazz = Class.forName((String)map.get(key));
				map.put(key, clazz.newInstance());
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	Object getBean(String key){
		
		return map.get(key);
	}
}

public class Main2 {

	public static void main(String[] args) throws Exception {

		AppContext ac = new AppContext();						
		
		Car car = (Car)ac.getBean("car");
		Engine engine = (Engine)ac.getBean("engine");
		
		System.out.println("car = " + car);
		System.out.println("engine = " + engine);
	}

}