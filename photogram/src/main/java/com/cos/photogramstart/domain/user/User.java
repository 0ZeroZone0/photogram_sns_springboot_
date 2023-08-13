package com.cos.photogramstart.domain.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)

@Builder
@AllArgsConstructor //전 생성자 
@NoArgsConstructor //빈 생성자 
@Data		//Getter, Setter 
@Entity //	디비에 테이블을 생성
public class User {
	@Id		//Primart Key(기본키)로 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	//번호 증가 전략이 데이터베이스를 따라간다. 
	private int id;
	
	private String username;					//유저아이디 
	private String password;					//패스워드 

	private String name;							//이름 
	private String website;						//웹사이트
	private String bio;								//자기소개
	private String email;							//이메일
	private String phone;						//전화번호
	private String gender;						//성별
	
	private String profileImageUrl;		//사진
	private String role; 							//권한

	private LocalDateTime createDate;
	
	@PrePersist		//DB INSERT 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}