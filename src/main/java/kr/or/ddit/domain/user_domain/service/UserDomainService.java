package kr.or.ddit.domain.user_domain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.domain.user_domain.dao.IUserDomainDao;
import kr.or.ddit.domain.user_domain.model.UserDomainVO;

//@Service
public class UserDomainService implements IUserDomainService{

	@Resource(name = "userDomainDao")
	private IUserDomainDao userDomainDao;

	@Override
	public int insert(UserDomainVO userDomainVo) {
		return userDomainDao.insert(userDomainVo);
	}
	
	@Override
	public UserDomainVO get(String id) {
		return userDomainDao.get(id);
	}
	
	@Override
	public List<UserDomainVO> list() {
		return userDomainDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<UserDomainVO> mapList =  userDomainDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return userDomainDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return userDomainDao.delete(id);
	}

}
