package org.samsung.util.validator;

import org.samsung.model.UserCreds;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserCredsValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {		
		return UserCreds.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors e) {		
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "UserName", "e.Username", "Username is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "Password", "e.Password", "Password is required");
	
	}

}
