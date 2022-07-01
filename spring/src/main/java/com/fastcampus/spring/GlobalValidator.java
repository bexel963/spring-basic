package com.fastcampus.spring;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class GlobalValidator implements Validator {

	// �Ѿ���� Ŭ���� ���� ������ ������ Ȯ���ϴ� �޼���
	@Override
	public boolean supports(Class<?> clazz) {			
//		return User.class.equals(clazz);			// clazz ��ü�� User Ÿ������ Ȯ��
		return User.class.isAssignableFrom(clazz);	// clazz ��ü�� User �Ǵ� �� �ڼ�Ÿ������ Ȯ�� 
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("GlobalValidator.validate() is Called");
//		if(!(target instanceof User)) return;			- ���� �̰� �ؾߵǴµ� supports���� �ϱ� ������ �� �ص� �ȴ�.
		User user = (User)target;
		
		String id = user.getId();
		String pwd = user.getPwd();
		
//		if(id==null || "".equals(id.trim())) {			- �Ʒ� �ڵ�� ���� ��.
//			errors.rejectValue("id", "required");
//		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");		// �����ڵ带 required�� �����Ѵ�.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");		// �ۼ��� �����޽���.properties���� �����ڵ�� �´� �׸��� ã�Ƽ� �־��ش�.
		
		if(id==null || id.length()<5 || id.length()>12) {
//			errors.rejectValue("id", "invalidLength");
			errors.rejectValue("id", "invalidLength", new String[] {"", "5", "12"}, null);		
		}
	}

}
