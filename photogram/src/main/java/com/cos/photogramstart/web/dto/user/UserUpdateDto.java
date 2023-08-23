package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class UserUpdateDto {

	private String name;				//필수 
	private String password;		//필수 
	private String website;
	private String bio;
	private String phone;
	private String gender;
	
	
	// 조금 위험함. 코드 수정이 필요할 예정 (필수값이 아닌 데이터들도 포함되어 있어서) 
	public User toEntity() {		
		return User.builder()
				.name(name)
				.password(password)
				.website(website)
				.bio(bio)
				.phone(phone)
				.gender(gender)
				.build();
	}
	
}
