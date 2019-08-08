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
	
	/**
	* Method : getAbb
	* 작성자 : 손주형
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 유저가 템플릿을 등록할 때 
	* 			  약어가 존재하는지 검색하는 메서드
	*/
	@Override
	public String getAbb(Map<String, Object> map) {
		return userTemplateDao.getAbb(map);
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

	/**
	* Method : updateUserTemplate
	* 작성자 : 손주형
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 사용자가 사용자 템플릿을 수정하는 메서드
	*/
	@Override
	public int updateUserTemplate(UserTemplateVO userTemplateVO) {
		return userTemplateDao.updateUserTemplate(userTemplateVO);
	}
	
	/**
	* Method : deleteUserTemplate
	* 작성자 : 손주형
	* 변경이력 :
	* @param userTemplateVO
	* @return
	* Method 설명 : 사용자 템플릿을 삭제하는 메서드
	*/
	@Override
	public int deleteUserTemplate(UserTemplateVO userTemplateVO) {
		return userTemplateDao.deleteUserTemplate(userTemplateVO);
	}

	/**
	* Method : getOriginal
	* 작성자 : 손주형
	* 변경이력 :
	* @param utemplate_abb
	* @return
	* Method 설명 : 사용자가 약어가 DB에 있는 약어와 일치하는 원문을 리턴
	*/
	@Override
	public String getOriginal(UserTemplateVO userTemplateVO) {
		return userTemplateDao.getOriginal(userTemplateVO);
	}
}
