package com.cos.photogramstart.web.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor	//모든 필드 값을 파라미터로 받는 생성자를 만들어줌 
@NoArgsConstructor	//파라미터가 없는 기본 생성자를 생성
@Data								//getter, setter 
public class CMRespDto<T> {
	private int code;				//1(성공), -1(실패) 
	private String message;
	private T data;
}
