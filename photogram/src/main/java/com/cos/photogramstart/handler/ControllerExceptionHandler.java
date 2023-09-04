package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomApiException;
import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController //  응답리턴을 위해서 
@ControllerAdvice 	//모든 익셉션을 낚아채기 위해서 
public class ControllerExceptionHandler {

	@ExceptionHandler(CustomValidationException.class)
	public String  validationException(CustomValidationException e) {
		//CMRespDto, Scripte 비교
		// 1. 클라이언트에게 응답할 때는 Script가 좋음 
		// 2. Ajax 통신 - CMRespDto가 좋음
		// 3. Android 통신 - CMRespDto가 좋음 
		if(e.getErrorMap() == null) {
			return Script.back(e.getMessage());
		}else {
			return Script.back(e.getErrorMap().toString());
		}
	}
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<CMRespDto<?>>  validationApiException(CustomValidationApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),e.getErrorMap()), HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(CustomApiException.class)
	public ResponseEntity<CMRespDto<?>> apiException(CustomApiException e) {
		return new ResponseEntity<>(new CMRespDto<>(-1,e.getMessage(),null), HttpStatus.BAD_REQUEST);
	}		
	
}
