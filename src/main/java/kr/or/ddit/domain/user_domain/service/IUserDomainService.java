package kr.or.ddit.domain.user_domain.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.domain.user_domain.model.UserDomainVO;

public interface IUserDomainService {
	
	int insert(UserDomainVO userDomainVo);
	
	UserDomainVO get(String id);
	
	List<UserDomainVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}
