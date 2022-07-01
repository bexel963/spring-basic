//
//// - �浹�Ǵ°� �� �ּ� ó�� �� ����.
//
//package com.fastcampus.spring2;
//
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.context.*;
//import org.springframework.context.annotation.*;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.support.*;
//import org.springframework.stereotype.*;
//
//import javax.inject.*;
//import java.util.*;
//
//@Component
//@Scope("prototype")
//class Door2 {}
//@Component("engine")						// Ÿ������ ���� �˻��ؼ� ���� ������ �ڵ� ����(DI) �ϴµ� �˻��� ���� 1���� �ƴϸ� ���ܰ� �߻��Ѵ�.
//class Engine2 {}							// �� �� @Component�ڿ� ���̵� �����ָ� ������ Ÿ�� �� ã�� �� ���̵� ��ġ�ϴ°� �ϳ� ã�Ƽ� 1���� ���� �ϰԵȴ�.
//
//@Component	// ("turboEngine") ����������. 
//class TurboEngine extends Engine2 {}
//@Component									
//class SuperEngine extends Engine2 {}		
//
//@Component
//class Car2 {
//    @Value("red") 
//    String color;
//    @Value("100") 
//    int oil;
////    @Autowired    		// DI ������ iv�� ���� ���൵ ������ �����ڸ� �̿��ؼ� �� �����ִ�.
//    Engine2 engine;
////    @Autowired    
//    Door2[] doors;
//
//    public Car2() {}	// �ٸ� �����ڰ� ������ ��� �����ڸ� �̿��ؼ� ������ ���� �˷���� �ϱ� ������ @Autowired�� ���� �ϸ� �� �ȴ�.
//    
//    @Autowired 		// - �������� @Autowired�� ���� �����ϴ�.
//    public Car2(@Value("red") String color, @Value("100") int oil, Engine2 engine, Door2[] doors) {
//		super();
//		this.color = color;
//		this.oil = oil;
//		this.engine = engine;
//		this.doors = doors;
//	}
//
//	@Override
//    public String toString() {
//        return "Car{" +
//                "color='" + color + '\'' +
//                ", oil=" + oil +
//                ", engine=" + engine +
//                ", doors=" + Arrays.toString(doors) +
//                '}';
//    }
//}
//
//@Component
//@PropertySource("setting.properties")
//class SysInfo{
//	
//	@Value("#{systemProperties['user.timezone']}")
//	String timeZone;
//	@Value("#{systemEnvironment['APPDATA']}")
//	String currDir;
//	@Value("${autosaveDir}")
//	String autosaveDir;
//	@Value("${autosaveInterval}")
//	int autosaveInterval;
//	@Value("${autosave}")
//	boolean autosave;
//	
//	@Override
//	public String toString() {
//		return "SysInfo [timeZone=" + timeZone + ", currDir=" + currDir + ", autosaveDir=" + autosaveDir
//				+ ", autosaveInterval=" + autosaveInterval + ", autosave=" + autosave + "]";
//	}
//	
//}
//public class ApplicationContextTest {
//    public static void main(String[] args) {
//        ApplicationContext ac = new GenericXmlApplicationContext("config2.xml");
////      Car car = ac.getBean("car", Car.class); // Ÿ���� �����ϸ� ����ȯ ���ص���. �Ʒ��� ����� ����
//        Car2 car  = (Car2) ac.getBean("car2"); // �̸����� �� �˻�
//        Car2 car2 = (Car2) ac.getBean(Car2.class);   // Ÿ������ �� �˻�
//        
//        System.out.println();
//        System.out.println("car = " + car);
//        System.out.println("car2 = " + car2);
//
//        System.out.println();
//        System.out.println("=====================================================================================================");
//        System.out.println();
//        
//        System.out.println("ac.getBean(SysInfo.class) = " + ac.getBean(SysInfo.class));
//        System.out.println();
//        
//        Map<String, String> map = System.getenv();			// �ý��� ȯ�溯�� ��� ��������
//        System.out.println("map = " + map);
//        
//        System.out.println();
//        System.out.println("=====================================================================================================");
//        System.out.println();
//        
//        Properties properties = System.getProperties();		// �ý��� ������Ƽ ��� ��������
//        System.out.println("properites = " + properties);
//        
//        System.out.println();
//        System.out.println("===========================================AC �޼ҵ��==========================================================");
//        System.out.println();
//        
//        System.out.println("ac.getBeanDefinitionNames() = " + Arrays.toString(ac.getBeanDefinitionNames())); 	// ���ǵ� ���� �̸��� �迭�� ��ȯ
//        System.out.println("ac.getBeanDefinitionCount() = " + ac.getBeanDefinitionCount()); 					// ���ǵ� ���� ������ ��ȯ
//
//        System.out.println("ac.containsBeanDefinition(\"car\") = " + ac.containsBeanDefinition("car2"));  		// true: ���� ���ǰ� ���ԵǾ� �ִ��� Ȯ��
//        System.out.println("ac.containsBean(\"car\") = " + ac.containsBean("car2")); 							// true: ���� ���ԵǾ� �ִ��� Ȯ��
//
//        System.out.println("ac.getType(\"car\") = " + ac.getType("car2")); 										// ���� �̸����� Ÿ���� �˾Ƴ� �� ����.
//        System.out.println("ac.isSingleton(\"car\") = " + ac.isSingleton("car2")); 								// true: ���� �̱������� Ȯ��
//        System.out.println("ac.isSingleton(\"car\") = " + ac.isPrototype("car2")); 								// false: ���� ������Ÿ������ Ȯ��
//        System.out.println("ac.isPrototype(\"door\") = " + ac.isPrototype("door2")); 							// true
//        // "car"��� �̸��� ���� Ÿ���� Car���� Ȯ��
//        System.out.println("ac.isTypeMatch(\"car\", Car.class) = " + ac.isTypeMatch("car2", Car2.class));
//        // �� car�� @Component�� �پ������� ��ȯ
//        System.out.println("ac.findAnnotationOnBean(\"car\", Component.class) = " + ac.findAnnotationOnBean("car2", Component.class)); 
//        // @Component�� ���� ���� �̸��� �迭�� ��ȯ
//        System.out.println("ac.getBeanNamesForAnnotation(Component.class) = " + Arrays.toString(ac.getBeanNamesForAnnotation(Component.class))); 
//        // Engine �Ǵ� �� �ڼ� Ÿ���� ���� �̸��� �迭�� ��ȯ
//        System.out.println("ac.getBeanNamesForType(Car.class) = " + Arrays.toString(ac.getBeanNamesForType(Engine2.class))); 
//    }
//}