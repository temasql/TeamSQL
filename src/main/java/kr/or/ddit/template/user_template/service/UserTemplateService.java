package kr.or.ddit.template.user_template.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.template.user_template.dao.IUserTemplateDao;
import kr.or.ddit.template.user_template.model.UserTemplateVO;

@Service
public class UserTemplateService implements IUserTemplateService{

	@Resource(name = "userTemplateDao")
	private IUserTemplateDao userTemplateDao;

	@Override
	public int insertUserTemplate(UserTemplateVO userTemplateVo) {
		return userTemplateDao.insertUserTemplate(userTemplateVo);
	}
	
	@Override
	public UserTemplateVO get(String id) {
		return userTemplateDao.get(id);
	}
	
	/**
	* Method : userTemplateList
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : 각 유저별 템플릿 리스트 조회
	*/
	@Override
	public List<UserTemplateVO> userTemplateList(UserTemplateVO userTemplateVO) {
		return userTemplateDao.userTemplateList(userTemplateVO);
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
