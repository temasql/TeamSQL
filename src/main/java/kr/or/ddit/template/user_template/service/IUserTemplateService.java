package kr.or.ddit.template.user_template.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.template.user_template.model.UserTemplateVO;

public interface IUserTemplateService {
	
	int insertUserTemplate(UserTemplateVO userTemplateVo);
	
	String getAbb(Map<String, Object> map);
	
	String getOriginal(UserTemplateVO userTemplateVO);
	
	List<UserTemplateVO> userTemplateList(UserTemplateVO userTemplateVO);
	
	Map<String, Object> map(Map<String, Object> map);
	
	int updateUserTemplate(UserTemplateVO userTemplateVO);
	
	int deleteUserTemplate(UserTemplateVO userTemplateVO);
	
	/**
	* Method : sameAbb
	* 작성자 : 손주형
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 입력한 템플릿ID와 일치하는 약어 검색
	*/
	String sameAbb(Map<String, Object> map);
}
