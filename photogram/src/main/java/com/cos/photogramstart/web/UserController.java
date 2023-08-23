package com.cos.photogramstart.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.photogramstart.config.auth.PrincipalDetails;

@Controller
public class UserController {

	@GetMapping("/user/{id}")
	public String profile(@PathVariable String id) {
		//@PathVariable int id 가 맞는데 현재 툴 오류로 진행 불가 String으로 임시 진행 
		return "user/profile";
	}
	
	@GetMapping("/user/{id}/update")
	public String update(@PathVariable String id, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		//@PathVariable int id 가 맞는데 현재 툴 오류로 진행 불가 String으로 임시 진행 
		//1. 추천 
		System.out.println("세션 정보 :" +principalDetails.getUser());
		
		//2. 복잡(비추천)
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		PrincipalDetails mPrincipalDetails = (PrincipalDetails)auth.getPrincipal();
		System.out.println("직접 찾은 세션 정보 :" +mPrincipalDetails.getUser());
		
		return "user/update";
	}	
	
}
