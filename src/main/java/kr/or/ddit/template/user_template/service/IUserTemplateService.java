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
}
