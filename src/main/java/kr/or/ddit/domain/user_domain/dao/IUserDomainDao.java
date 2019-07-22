package kr.or.ddit.domain.user_domain.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.domain.user_domain.model.UserDomainVO;

public interface IUserDomainDao {

	/**
	* Method : insertCommonDomain
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 사용자가 회원가입할때 공통 도메인을  사용자 개인 도메인에 생성하는 쿼리
	*/
	int insertCommonDomain(String user_id);
	
	int insert(UserDomainVO userDomainVo);
	
	UserDomainVO get(String id);
	
	List<UserDomainVO> list();
	
	List<UserDomainVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
