package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.photogramstart.config.oauth.OAuth2DetailesService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity	//해당 파일로 시큐리티를 활성화 
@Configuration		//IoC
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
		private final OAuth2DetailesService oAuth2DetailesService;
		
		@Bean
		public BCryptPasswordEncoder encode() {
			return new BCryptPasswordEncoder();
		}
		
	
		@Override
		protected void configure(HttpSecurity http) throws Exception {
				//super 삭제 - 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.
				http.csrf().disable();					//CSRF토큰을 비활성화
				
				System.out.println("11");
				
				http.authorizeRequests()
					.antMatchers("/", "/user/**", "/image/**", "/subscribe/**" , "/comment/**", "/api/**").authenticated() 	//  / , /user , /image, /subscribe, /comment로 시작하는 주소로 들어오면 인증필요 
					.anyRequest().permitAll()							// 그게 아닌 모든 요청은 허용 하겠다.
					.and()
					.formLogin()												//form태그 및 input태그 중에 form태그로 로그인할
					.loginPage("/auth/signin")						//접근 권한이 없는 페이지(위에 설정한 주소)들은 다 이 주소로 이동.	//GET
					.loginProcessingUrl("/auth/signin")		//POST -> 스프링 시큐리티가 로그인 프로세스 진행 
					.defaultSuccessUrl("/")								//로그인을 정상처리하면 /로 이동 
					.and()
					.oauth2Login()											// form 로그인도 하는데, oauth2 로그인도 할거야 
					.userInfoEndpoint()									// oauth2로그인을 하면 최종응답을 회원정보를 바로 받을 수 있다 (인증코드 받고 AccessToken을 받아서 진행안하게 설정)
					.userService(oAuth2DetailesService);
		}
}
