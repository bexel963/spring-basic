
// - 객체를 생성하는 메서드를 만들어서 그것을 이용해 객체를 생성한다.
//	 객체를 생성하는 메서드는 외부파일의 키 값을 읽어와 그 키에 해당하는 객체를 만들어서 반환한다.
//   이렇게 하면 생성하고자하는 객체를 변경하고 싶을 때 프로그램을 변경하지 않고 외부파일 config.txt 파일만 변경해 주면 된다.

package com.fastcampus.spring2.diCopy1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

class Car{}
class SportsCar extends Car {}
class Truck extends Car {}
class Engine {}

public class Main1 {

	public static void main(String[] args) throws Exception {
		
		Car car = getCar();										
		Car car2 = (Car)getObject("car");
		Engine engine = (Engine)getObject("engine");
		
		System.out.println("car = " + car);
		System.out.println("car2 = " + car2);
		System.out.println("engine = " + engine);
	}
	
	static Car getCar() throws Exception {
		
		Properties p = new Properties();
		p.load(new FileReader("config.txt"));
		
		Class clazz = Class.forName(p.getProperty("car"));
		
		return (Car)clazz.newInstance();
	}
	
	static Object getObject(String key) throws Exception {
		
		Properties p = new Properties();
		p.load(new FileReader("config.txt"));				// 1. config.txt 파일을 읽어와 key-value를 프로퍼티에 저장한다.
		
		Class clazz = Class.forName(p.getProperty(key));	// 2. 프로퍼티의 key값에 해당하는 value의 클래스 정보를 얻어온다. 
		
		return clazz.newInstance();							// 3. 클래스의 객체를 생성해서 반환한다.
	}
}
