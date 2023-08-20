package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;

@RestController //  응답리턴을 위해서 
@ControllerAdvice 	//모든 익셉션을 낚아채기 위해서 
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class)
	public Map<String, String> validationException(CustomValidationException e) {
		return e.getErrorMap();
	}
	
}
