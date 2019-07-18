package kr.or.ddit.template.user_template.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.template.user_template.dao.IUserTemplateDao;
import kr.or.ddit.template.user_template.model.UserTemplateVO;

//@Service
public class UserTemplateService implements IUserTemplateService{

	@Resource(name = "userTemplateDao")
	private IUserTemplateDao userTemplateDao;

	@Override
	public int insert(UserTemplateVO userTemplateVo) {
		return userTemplateDao.insert(userTemplateVo);
	}
	
	@Override
	public UserTemplateVO get(String id) {
		return userTemplateDao.get(id);
	}
	
	@Override
	public List<UserTemplateVO> list() {
		return userTemplateDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<UserTemplateVO> mapList =  userTemplateDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return userTemplateDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return userTemplateDao.delete(id);
	}

}
