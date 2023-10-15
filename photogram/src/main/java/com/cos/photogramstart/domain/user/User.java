package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@Column(length=100, unique = true)					//OAuth2 로그인을 위해 컬럼 늘리기 
	private String username;					//유저아이디 
	@Column(nullable = false)
	private String password;					//패스워드 

	@Column(nullable = false)
	private String name;							//이름 
	private String website;						//웹사이트
	private String bio;								//자기소개
	@Column(nullable = false)
	private String email;							//이메일
	private String phone;						//전화번호
	private String gender;						//성별
	
	private String profileImageUrl;		//사진
	private String role; 							//권한
	
	//나는 연관관계의 주인이 아니다.  그러므로 테이블에 컬럼을 만들지마라~ 
	//User를 SELECT 할때 해당 User id로 등록된 image를 다 갖고와 
	//LAZY 일때는 User를 SELECT 할때 해당 User id로 등록된 image들을 가져오지마 - 대신 getImages() 함수의 image들이  호츌될때 가져와 
	//Eager 일때는 User를 SELECT 할때 해당 User id로 등록된 image들을 Join해서 가져와 ! 
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY) 
	@JsonIgnoreProperties({"user"})
	private List<Image> images; 		//양방향 맵핍 

	private LocalDateTime createDate;
	
	@PrePersist		//DB INSERT 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", website="
				+ website + ", bio=" + bio + ", email=" + email + ", phone=" + phone + ", gender=" + gender
				+ ", profileImageUrl=" + profileImageUrl + ", role=" + role + ", createDate="
				+ createDate + "]";
	}
	
	
	
	
}