package com.cos.photogramstart.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.cos.photogramstart.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

	private static final long serialVersionUID = 1L;
	
	private User user;
	private Map<String, Object> attributes;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}

	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user = user;
	}
	
	//권한: 한개가 아닐 수 있음. (3개 이상의 권한) 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collector = new ArrayList<>();
		collector.add(() -> {
				return user.getRole();
		});
		
		return collector;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {	//계정이 만료가 되었는 
		return true; 		//false가 맞는거다(로그인을 위해서 true로 변경)
	}

	@Override
	public boolean isAccountNonLocked() {//계정이 잠겼는가 
		return true;		//false가 맞는거다(로그인을 위해서 true로 변경)
	}

	@Override
	public boolean isCredentialsNonExpired() { //비밀번호가 오랫동안 안바뀐거 아닌가 
		return true;		//false가 맞는거다(로그인을 위해서 true로 변경)
	}

	@Override
	public boolean isEnabled() {	//계정이 활성화 되어있는가 
		return true;		//false가 맞는거다(로그인을 위해서 true로 변경)
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;		//{iid : 912312391293 , name : blarblar , email : test@gmail.com }
	}

	@Override
	public String getName() {
		return (String)attributes.get("name");
	}

}
