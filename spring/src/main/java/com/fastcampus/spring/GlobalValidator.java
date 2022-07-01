package com.fastcampus.spring;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GlobalValidator implements Validator {

	// 넘어오는 클래스 검증 가능한 것인지 확인하는 메서드
	@Override
	public boolean supports(Class<?> clazz) {			
//		return User.class.equals(clazz);			// clazz 객체가 User 타입인지 확인
		return User.class.isAssignableFrom(clazz);	// clazz 객체가 User 또는 그 자손타입인지 확인 
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("GlobalValidator.validate() is Called");
//		if(!(target instanceof User)) return;			- 원래 이거 해야되는데 supports에서 하기 때문에 안 해도 된다.
		User user = (User)target;
		
		String id = user.getId();
		String pwd = user.getPwd();
		
//		if(id==null || "".equals(id.trim())) {			- 아래 코드와 같은 뜻.
//			errors.rejectValue("id", "required");
//		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");		// 에러코드를 required로 저장한다.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");		// 작성한 에러메시지.properties에서 에러코드와 맞는 항목을 찾아서 넣어준다.
		
		if(id==null || id.length()<5 || id.length()>12) {
//			errors.rejectValue("id", "invalidLength");
			errors.rejectValue("id", "invalidLength", new String[] {"", "5", "12"}, null);		
		}
	}

}
