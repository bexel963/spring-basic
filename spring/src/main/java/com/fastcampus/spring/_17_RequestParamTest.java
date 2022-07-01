package com.fastcampus.spring;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class _17_RequestParamTest {

	@ExceptionHandler(Exception.class)
	public String catcher(Exception e) {
		return "yoilError";
	}
	
	@RequestMapping("/requestParam1")
	public String main(HttpServletRequest request) {
		//	http://localhost/spring/requestParam1         ---->> year=null
		//	http://localhost/spring/requestParam1?year=   ---->> year=""
		//	http://localhost/spring/requestParam1?year    ---->> year=""
		String year = request.getParameter("year");
		System.out.printf("[%s]year = [%s]\n", new Date(), year);
		
		return "yoil";
	}
	
	@RequestMapping("/requestParam2") // "year"는 파라미터 이름
//  public String main2(@RequestParam(name="year", required=false) String year) - 아랫줄과 동일
	public String main2(String year) {
		//	http://localhost/spring/requestParam2         ---->> year=null
		//	http://localhost/spring/requestParam2?year=   ---->> year=""
		//	http://localhost/spring/requestParam2?year    ---->> year=""
		System.out.printf("[%s]year = [%s]\n", new Date(), year);
		return "yoil";
	}
	
	@RequestMapping("/requestParam3")
//  public String main3(@RequestParam(name="year", required=true) String year) - 아랫줄과 동일
	public String main3(@RequestParam String year) {
		//	http://localhost/spring/requestParam3         ---->> year=null		400 Bad Request
		//	http://localhost/spring/requestParam3?year=   ---->> year=""
		//	http://localhost/spring/requestParam3?year    ---->> year=""
		System.out.printf("year = [%s]\n", year);
		return "yoil";

	}
	
	@RequestMapping("/requestParam4")
//	public String main4(@RequestParam(name="year", required=false) String year) - 아랫줄과 동일
	public String main4(@RequestParam(required=false) String year) {
//  public String main4(String year)	- 윗줄과 동일
		//	http://localhost/spring/requestParam4         ---->> year=null
		//	http://localhost/spring/requestParam4?year=   ---->> year=""
		//	http://localhost/spring/requestParam4?year    ---->> year=""
		System.out.printf("[%s]year = [%s]\n", new Date(), year);
		return "yoil";
	}
	
	@RequestMapping("/requestParam5")
	public String main5(@RequestParam(required=false, defaultValue="1") String year) {
		//	http://localhost/spring/requestParam5         ---->> year=1
		//	http://localhost/spring/requestParam5?year=   ---->> year=""
		//	http://localhost/spring/requestParam5?year    ---->> year=""
		System.out.printf("[%s]year = [%s]\n", new Date(), year);
		return "yoil";
	}

// ==========================================================================================

// null과 " "은(둘다 String) int형으로 변환될 수 없다.
	
	@RequestMapping("/requestParam6")
//	public String main6(@RequestParam(name="year", required=false) int year) - 아랫줄과 동일
	public String main6(int year) {
		//	http://localhost/spring/requestParam6         ---->> year=null	 500 java.lang.IllegalStateException: Optional int parameter 'year' is present but cannot be translated into a null value due to being declared as a primitive type. Consider declaring it as object wrapper for the corresponding primitive type.
		//	http://localhost/spring/requestParam6?year=   ---->> year=""	 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
		//	http://localhost/spring/requestParam6?year    ---->> year=""	 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
		System.out.printf("[%s]year = [%s]\n", new Date(), year);
		return "yoil";
	}
	
	@RequestMapping("/requestParam7")
//	public String main7(@RequestParam(name="year", required=true) int year) - 아랫줄과 동일
	public String main7(@RequestParam int year) {
		//	http://localhost/spring/requestParam7         ---->> year=null	 400 Bad Request, Required int parameter 'year' is not present
		//	http://localhost/spring/requestParam7?year=   ---->> year=""	 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
		//	http://localhost/spring/requestParam7?year    ---->> year=""	 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
		System.out.printf("[%s]year = [%s]\n", new Date(), year);
		return "yoil";
	}
	
	// required=false로 설정하면 서버가 그 값에대한 대비를 해야한다. 안 해놓으면 500번대 에러 발생
	@RequestMapping("/requestParam8")
//	public String main8(int year) - 아랫줄과 동일
//  public String main8(@RequesParam(name="year", required=false) int year) - 아랫줄과 동일
	public String main8(@RequestParam(required=false) int year) {
		//	http://localhost/spring/requestParam8         ---->> year=null	 500 java.lang.IllegalStateException: Optional int parameter 'year' is present but cannot be translated into a null value due to being declared as a primitive type. Consider declaring it as object wrapper for the corresponding primitive type.
		//	http://localhost/spring/requestParam8?year=   ---->> year=""	 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
		//	http://localhost/spring/requestParam8?year    ---->> year=""	 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
		System.out.printf("[%s]year = [%s]\n", new Date(), year);
		return "yoil";
	}
	
	// 필수입력 일때는 예외처리를 해서 사용자에게 오류화면을 보여줌으로써 매끄럽게 진행시켜야한다.
	@RequestMapping("/requestParam9")
	public String main9(@RequestParam(required=true) int year) {
		//	http://localhost/spring/requestParam9         ---->> year=null	 400 Bad Request, Required int parameter 'year' is not present
		//	http://localhost/spring/requestParam9?year=   ---->> year=""	 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
		//	http://localhost/spring/requestParam9?year    ---->> year=""	 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
		System.out.printf("[%s]year = [%s]\n", new Date(), year);
		return "yoil";
	}
	
	@RequestMapping("/requestParam10")
	public String main10(@RequestParam(required=true, defaultValue="1") int year) {
		//	http://localhost/spring/requestParam10         ---->> year=1
		//	http://localhost/spring/requestParam10?year=   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
		//	http://localhost/spring/requestParam10?year    ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
		System.out.printf("[%s]year = [%s]\n", new Date(), year);
		return "yoil";
	}
	
	// requestParam8의 이유로 required가 false이고(필수입력이 아닐때) 파라미터가 int형 이면 defaultValue 값을 설정해 줘야한다.
	@RequestMapping("/requestParam11")
	public String main11(@RequestParam(required=false, defaultValue="1") int year) {
		//	http://localhost/spring/requestParam11         ---->> year=1
		//	http://localhost/spring/requestParam11?year=   ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
		//	http://localhost/spring/requestParam11?year    ---->> 400 Bad Request, nested exception is java.lang.NumberFormatException: For input string: ""
		System.out.printf("[%s]year = [%s]\n", new Date(), year);
		return "yoil";
	}
		
}
