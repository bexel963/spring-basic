
// - ��ü�� �����ϴ� �޼��带 ���� �װ��� �̿��� ��ü�� �����Ѵ�.
//	 ��ü�� �����ϴ� �޼���� �ܺ������� Ű ���� �о�� �� Ű�� �ش��ϴ� ��ü�� ���� ��ȯ�Ѵ�.
//   �̷��� �ϸ� �����ϰ����ϴ� ��ü�� �����ϰ� ���� �� ���α׷��� �������� �ʰ� �ܺ����� config.txt ���ϸ� ������ �ָ� �ȴ�.

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
		p.load(new FileReader("config.txt"));				// 1. config.txt ������ �о�� key-value�� ������Ƽ�� �����Ѵ�.
		
		Class clazz = Class.forName(p.getProperty(key));	// 2. ������Ƽ�� key���� �ش��ϴ� value�� Ŭ���� ������ ���´�. 
		
		return clazz.newInstance();							// 3. Ŭ������ ��ü�� �����ؼ� ��ȯ�Ѵ�.
	}
}
