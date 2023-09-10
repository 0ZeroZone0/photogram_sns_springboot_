package com.cos.photogramstart.web.dto.user;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserProfileDto {
	private boolean pageOwnerState;
	private int imageCount;
	private boolean subscribeState;	//구독한 상태 
	private int subscribeCount;			//구독자 수 
	private User user;
}
