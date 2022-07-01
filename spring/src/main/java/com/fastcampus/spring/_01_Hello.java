
// 프로그램 등록, URL과 메소드 연결 - static - x , private - x

package com.fastcampus.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller	
public class _01_Hello {
	int iv = 10;
	static int cv = 20;
	
	@RequestMapping("/hello")
	private void main() {
		System.out.println("접근제어자를 private로 해도 외부(브라우저)에서 호출 가능. (내부에서는 private기능을 똑같이 한다.");
		System.out.println("Hello - main메서드를 static으로 지정하지 않아도 호출됨.");
		System.out.println("스태틱 멤버변수 사용 : " + cv);
		System.out.println("인스턴스 멤버변수 사용 : " + iv);
		System.out.println("메서드를 인스턴스 멤버로 지정하면 iv, cv 둘 다 사용 할 수있기 때문에 메서드에 static 붙이지 않는다.");
	}
}
