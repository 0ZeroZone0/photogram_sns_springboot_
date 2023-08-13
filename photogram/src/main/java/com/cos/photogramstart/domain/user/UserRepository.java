package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;


//어노테이션 없이도 JpaRepository를 상속하면 IoC가 자동으로 등록 
																								//오브젝트, 프라이머리키 타입
public interface UserRepository extends JpaRepository<User, Integer>{

}
