
// - config.txt���� �ߴ��Ÿ� config.xml���� ������
//   config.xml�� Ŭ���� ������ ������ �ְ� �� ������ �о ��ü ����ҿ� ��ü�� map���·� �����.

package com.fastcampus.spring2;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

class Engine{}
class Door{}

class Car{
	
	String color;
	int oil;
	Engine engine;
	Door[] door;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getOil() {
		return oil;
	}
	public void setOil(int oil) {
		this.oil = oil;
	}
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	public Door[] getDoor() {
		return door;
	}
	public void setDoor(Door[] door) {
		this.door = door;
	}
	@Override
	public String toString() {
		return "Car [color=" + color + ", oil=" + oil + ", engine=" + engine + ", door=" + Arrays.toString(door) + "]";
	}
		
}


public class SpringDiTest {
	public static void main(String[] args) {
		
		// config.xml�� Ŭ���� ������ ������ �ְ� �� ������ �о ��ü ����ҿ� ��ü�� map���·� ���������.
		ApplicationContext ac = new GenericXmlApplicationContext("config.xml");
		
		Car car2 = (Car)ac.getBean(Car.class);			// byType
		Car car = (Car)ac.getBean("car");				// byName
		Car car3 = ac.getBean("car", Car.class);	// �ڿ� Ÿ�������� �� �� ���� -> ����ȯ �� �ص� ��.(���� ���� ����)	
		Car car4 = (Car)ac.getBean("car");
		
		Engine engine = (Engine)ac.getBean("engine");
		Door door = (Door)ac.getBean("door");
		
		// car �ּ� Ȯ���غ��� - xml���� prototype, singleton �ٲ㺸�� �׽�Ʈ
		System.out.println("car = " + car);
		System.out.println("car2 = " + car2);
		System.out.println("car3 = " + car3);
		System.out.println("engine = " + engine);
		System.out.println("door = " + door);
		
		System.out.println("\n=============================car4 init======================================\n");
		
//		car4.setColor("red");
//		car4.setOil(100);
//		car4.setEngine(engine);
//		car4.setDoor(new Door[] { ac.getBean("door", Door.class), (Door)ac.getBean("door") });
		System.out.println("car4 = " + car4);
		

	}
}
