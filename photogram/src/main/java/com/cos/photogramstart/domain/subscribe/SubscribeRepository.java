package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository  extends JpaRepository<Subscribe, Integer>{

	@Modifying	//INSERT, DELETE, UPDATE를 네이티브 쿼리로 작성하려면 해당 어노테이션이 필요 !!!
	@Query(value = "INSERT INTO subscribe(fromUserId , toUserId, createDate) VALUES(:fromUserId, :toUserId, now())", nativeQuery = true)
	void mSubscribe(int fromUserId, int toUserId); 	//성공 1 실패 -1 로 리턴된다 (변경된 행의 갯수만큼 리턴된다 10개 변경 성공시 10으로 리턴) 실패는 그냥 -1 / 0으로 리턴시에는 해당 유저가 존재하지않거나 변경되지 않고 끝날때 

	@Modifying
	@Query(value = "DELETE FROM subscribe WHERE fromUserId = :fromUserId AND toUserId = :toUserId", nativeQuery = true)
	void mUnSubscribe(int fromUserId, int toUserId);  //성공 1 실패 -1 로 리턴된다 

	@Query(value = "SELECT COUNT(*)  FROM Subscribe WHERE fromUserId  = :principalId AND toUserId = :pageUserId", nativeQuery = true)
	int mSubscribeState(int principalId, int pageUserId);
	
	@Query(value = "SELECT COUNT(*) FROM Subscribe WHERE fromUserId = :pageUserId", nativeQuery = true)
	int mSubscribeCount(int pageUserId);
	
}
