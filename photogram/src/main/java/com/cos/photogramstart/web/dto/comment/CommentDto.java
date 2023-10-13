package com.cos.photogramstart.web.dto.comment;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

// @NotNull 		null값 체크 
// @NotEmpty	빈값이거나 null 체크 
// @NotBlank		빈값이거나 null 체크 그리고 빈 공백(스페이스)까지 

@Data
public class CommentDto {
	@NotBlank
	private String content;
	@NotNull
	private int imageId;
	
	//toEntity가 필요 없다. 
	
}
