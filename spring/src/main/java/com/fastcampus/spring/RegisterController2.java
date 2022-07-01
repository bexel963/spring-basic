
// 맵핑될 URL의 공통 부분을 @RequestMapping으로 클래스에 적용
//		- 매핑을 모듈별로 나눠서 사용할 수 있다.
// 타입 변환을 위한 @InitBinder 사용

package com.fastcampus.spring;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register2")
public class RegisterController2 {

	// String을 날짜형식으로 바꾸는 메서드 등록 - 해당 컨트롤러 내에서만 사용 가능
	// DS가 타입을 변환할 때 @InitBinder가 붙은 메서드가 있는지 먼저 확인해서 있으면 여기에 맞게 변환하고, 없으면 스프링이 가지고있는 디폴트 변환기를 이용한다.
	// @InitBinder가 붙은 메서드에는 스프링이 제공하는 에디터를 이용해서 타입을 변환한다.
	// 메서드 이름은 마음대로 하고, 매개변수는 고정이다.
	// @InitBinder는 해당 컨트롤러 내에서만 사용 가능하다. (@ExceptionHandler 처럼)
	@InitBinder
	void toDate(WebDataBinder binder) {
		// User클래스의 Date birth를 @DateTimeFormat(pattern="yyyy-MM-dd")private Date birth; 로 만들면 아래 두 줄과 같은 기능을 한다.
		//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//		binder.registerCustomEditor(Date.class, new CustomDateEditor(df, false));	// 변환등록기 등록 - false는 빈 값을 허용할건지 결정하는거
		
		// registerForm의 hobby란에 롤#영화#음악 입력 후 서버에 보내고 서버가 이것을 String[]으로 받으면 hobby[0]에 롤#영화#음악 가 다 들어간다.
		// 이것을 구분자를 기준으로 배열의 인덱스에 각각 담으려면 아래 코드를 추가해준다.
		// "hobby" 적용할 필드를 지정할 수 있다. (없으면 모든필드에 적용)
		binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));	
		
		// 스프링에 등록된 컨버터 목록 보기
		// 스프링이 이 목록에 등록된 변환기들을 이용하여 타입을 자동 변환해 주는것이다.
		ConversionService conversionService = binder.getConversionService();
		System.out.println("conversionService = " + conversionService);
	}
	
	@GetMapping("/add1")
	public String register() {
		return "registerForm";
	}
	
	// BindingResult를 적어주면 타입 변환 실패했을 때 에러페이지로 넘어가는 것이 아닌 바인딩 결과를 컨트롤러에게 주고 컨트롤러가 그것을 어떻게할지 처리하게 하는 것임.
	// 에러 페이지로 넘어가지 않는것일 뿐, 에러는 발생한 것임. -> sysout 으로 찍어서 확인할 수 있다.
	@PostMapping("/save1")	
	public String save(User user, BindingResult result, Model m) throws Exception{		// BindingResult는 해당 객체 바로 옆에 써줘야 한다.
		System.out.println("result = " + result);
		System.out.println("user = " + user);
		
		// 1. 유효성 검사
		if(!isValid(user)) {

			String msg = URLEncoder.encode("정보를 잘못 입력하셨습니다.", "utf-8");
			
			 m.addAttribute("msg", msg);
			 return "redirect:/register/add";		
		}
		
		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		
		return true;
	}
}
