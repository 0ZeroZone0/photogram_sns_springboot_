package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController //  응답리턴을 위해서 
@ControllerAdvice 	//모든 익셉션을 낚아채기 위해서 
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class)
	public CMRespDto <?> validationException(CustomValidationException e) {
		return new CMRespDto<Map<String,String>>(-1, e.getMessage(),  e.getErrorMap());
	}
	
}
