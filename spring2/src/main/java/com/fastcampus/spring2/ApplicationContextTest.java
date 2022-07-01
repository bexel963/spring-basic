//
//// - 충돌되는거 다 주석 처리 후 실행.
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
//@Component("engine")						// 타입으로 빈을 검색해서 참조 변수에 자동 주입(DI) 하는데 검색된 빈이 1개가 아니면 예외가 발생한다.
//class Engine2 {}							// 이 때 @Component뒤에 아이디 적어주면 동일한 타입 다 찾은 후 아이디 일치하는거 하나 찾아서 1개를 주입 하게된다.
//
//@Component	// ("turboEngine") 생략되있음. 
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
////    @Autowired    		// DI 주입은 iv에 각각 해줘도 되지면 생성자를 이용해서 할 수도있다.
//    Engine2 engine;
////    @Autowired    
//    Door2[] doors;
//
//    public Car2() {}	// 다른 생성자가 있으면 어느 생성자를 이용해서 주입할 지를 알려줘야 하기 때문에 @Autowired를 생략 하면 안 된다.
//    
//    @Autowired 		// - 생성자의 @Autowired는 생략 가능하다.
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
////      Car car = ac.getBean("car", Car.class); // 타입을 지정하면 형변환 안해도됨. 아래의 문장과 동일
//        Car2 car  = (Car2) ac.getBean("car2"); // 이름으로 빈 검색
//        Car2 car2 = (Car2) ac.getBean(Car2.class);   // 타입으로 빈 검색
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
//        Map<String, String> map = System.getenv();			// 시스템 환경변수 목록 가져오기
//        System.out.println("map = " + map);
//        
//        System.out.println();
//        System.out.println("=====================================================================================================");
//        System.out.println();
//        
//        Properties properties = System.getProperties();		// 시스템 프로퍼티 목록 가져오기
//        System.out.println("properites = " + properties);
//        
//        System.out.println();
//        System.out.println("===========================================AC 메소드들==========================================================");
//        System.out.println();
//        
//        System.out.println("ac.getBeanDefinitionNames() = " + Arrays.toString(ac.getBeanDefinitionNames())); 	// 정의된 빈의 이름을 배열로 반환
//        System.out.println("ac.getBeanDefinitionCount() = " + ac.getBeanDefinitionCount()); 					// 정의된 빈의 개수를 반환
//
//        System.out.println("ac.containsBeanDefinition(\"car\") = " + ac.containsBeanDefinition("car2"));  		// true: 빈의 정의가 포함되어 있는지 확인
//        System.out.println("ac.containsBean(\"car\") = " + ac.containsBean("car2")); 							// true: 빈이 포함되어 있는지 확인
//
//        System.out.println("ac.getType(\"car\") = " + ac.getType("car2")); 										// 빈의 이름으로 타입을 알아낼 수 있음.
//        System.out.println("ac.isSingleton(\"car\") = " + ac.isSingleton("car2")); 								// true: 빈이 싱글톤인지 확인
//        System.out.println("ac.isSingleton(\"car\") = " + ac.isPrototype("car2")); 								// false: 빈이 프로토타입인지 확인
//        System.out.println("ac.isPrototype(\"door\") = " + ac.isPrototype("door2")); 							// true
//        // "car"라는 이름의 빈의 타입이 Car인지 확인
//        System.out.println("ac.isTypeMatch(\"car\", Car.class) = " + ac.isTypeMatch("car2", Car2.class));
//        // 빈 car에 @Component가 붙어있으면 반환
//        System.out.println("ac.findAnnotationOnBean(\"car\", Component.class) = " + ac.findAnnotationOnBean("car2", Component.class)); 
//        // @Component가 붙은 빈의 이름을 배열로 반환
//        System.out.println("ac.getBeanNamesForAnnotation(Component.class) = " + Arrays.toString(ac.getBeanNamesForAnnotation(Component.class))); 
//        // Engine 또는 그 자손 타입인 빈의 이름을 배열로 반환
//        System.out.println("ac.getBeanNamesForType(Car.class) = " + Arrays.toString(ac.getBeanNamesForType(Engine2.class))); 
//    }
//}