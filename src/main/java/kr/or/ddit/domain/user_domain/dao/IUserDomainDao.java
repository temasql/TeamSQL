package kr.or.ddit.domain.user_domain.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.domain.user_domain.model.UserDomainVO;

public interface IUserDomainDao {

	int insert(UserDomainVO userDomainVo);
	
	UserDomainVO get(String id);
	
	List<UserDomainVO> list();
	
	List<UserDomainVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
