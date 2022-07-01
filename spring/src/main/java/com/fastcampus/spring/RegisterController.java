package com.fastcampus.spring;

import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

//	@RequestMapping("/register/add")	// 아랫줄과 동일
	@RequestMapping(value="/register/add0", method= {RequestMethod.GET, RequestMethod.POST})	// 배열로 사용 가능 - GET, POST방식 둘다 허용한다.
//	@GetMapping("/register/add")
	public String register() {
		return "registerForm";
	}
	
//	@RequestMapping("/register/save")
//	@RequestMapping(value="/register/save", method=RequestMethod.POST)	// method로 지정한 방식으로만 URL요청할 수 있게 한다.
	@PostMapping("/register/save0")	// 윗줄과 동일, sts 4.3부터 지원 - 스프링 버전은 pom.xml에서 변경
	public String save(User user, Model m) throws Exception{
		
		// 1. 유효성 검사
		if(!isValid(user)) {
			// 브라우저에 직접 한글을 입력하면 브라우저가 URL을 자동으로 인코딩을 해준다. - 그래서 입력한데로 내용이 보인다.
			// 컨트롤러에서 한글을 이용해 직접 URL을 만들면 이거는 브라우저가 URL을 인코딩 안 해준다. - 문자가 깨진다.
			// 그래서 이런 경우 컨트롤러에서 URL인코딩을 직접 해줘야한다.
			// 이때 사용하는 것이 URLEncoder.encode()이다. - 예외처리 해줘야함.
			// 그리고 이것을 받는 jsp에서는 URLDecoder.decode()를 해줘야함.
			String msg = URLEncoder.encode("정보를 잘못 입력하셨습니다.", "utf-8");
			
			 m.addAttribute("msg", msg);
			 return "redirect:/register/add0";		// redirect는 주소창이 바뀐다.(브라우저가 자동으로 새로운 요청을 하기 때문에)
//			 return "forward:/register/add0";		// forward는 주소창이 안 바뀐다.
		
			// msg를 model에 넣는 방법 말고 아래 방법도 있다. 
			// 원래 model은 view에 전달하기 위한 것인데 redirect(다시 요청)를 써서 전달할 경우에는 model을 써서 전달을 할 수가없다.
			// 위 model은 redirect:/register/add 요청에 쓸수가 없다.
			// 위 model은 /register/save 요청에 쓰는 모델이기 때문이다.
			// 그런데도 스프링이 위의 두줄을 자동으로 아래 한줄처럼 바꿔준다.
			//			- model에 저장된 데이터를 URL뒤에 붙여준다.
			// 결론은 위 두줄과 아래 한줄은 같은 코드이다.
//			return "redirect:/register/add?msg=" + msg;	// URL 재작성(reWriting)
		}
		
		// 2. DB에 신규회원 정보를 저장
		return "registerInfo";
	}

	private boolean isValid(User user) {
		
		return false;
	}
}
