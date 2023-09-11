package com.cos.photogramstart.web.dto.subscribe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubscribeDto {
	private int userId;
	private String username;
	private String profileImageUrl;
	private Integer subscribeState;	//구독한 상태인지 
	private Integer equalUserState;	//구독한자가 로그인한자랑 동일인 인지 
	
}
