
// 데이터검증(자동으로 검증기사용) - setValidator()로 @InitBinder에 등록하고, @Valid을 사용한다.
// 글로벌 검증기 사용 - 글로벌검증기 클래스 생성 후 servlet-context.xml에 등록하여 사용.
// 에러메시지.properties를 servlet-context.xml에 등록하여 jsp파일에서 등록한 에러메시지.properties를 출력하기.


package com.fastcampus.spring;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController4 {

	@InitBinder
	void toDate(WebDataBinder binder) {	

		binder.registerCustomEditor(String[].class, "hobby", new StringArrayPropertyEditor("#"));	
		
//		binder.setValidator(new UserValidator());	// (1)UserValidator객체를 생성해서 WebDataBinder의 로컬 validator로 등록
													// 글로벌검증기 사용하려면 setValidator를 사용하면 안 된다.
//		binder.addValidators(new UserValidator());	// 글로벌검증기도 사용하면서 로컬 검증기도 사용 하려면 add로 검증기 추가해준다.
													// 글로벌검증기 등록은 servlet-context.xml에서 한다.
		List<Validator> validatorList = binder.getValidators();		// 등록된 검증기 목록 가져오기
		System.out.println("validatorList = " + validatorList);
	}
	
	@GetMapping("/save")
	public String register() {
		return "registerForm2";
	}
							// (2)검증할 객체 앞에 @Vaild를 붙여준다.(스프링엔 없는것임)
	@PostMapping("/save")	//    @Valid를 사용하려면 pom.xml파일에 validation-api를 추가해야한다. (구글-maven repository검색 -> validation검색 -> 복사)
	public String save(@Valid User user, BindingResult result, Model m) throws Exception{		
		System.out.println("result = " + result);
		System.out.println("user = " + user);
		

		if(result.hasErrors()) {
			return "registerForm2";
		}
//		if(!isValid(user)) {
//
//			String msg = URLEncoder.encode("정보를 잘못 입력하셨습니다.", "utf-8");
//			
//			 m.addAttribute("msg", msg);
//			 return "redirect:/register/add";		
//		}
		
		return "registerInfo";
	}

	private boolean isValid(User user) {
		
		return true;
	}
}
