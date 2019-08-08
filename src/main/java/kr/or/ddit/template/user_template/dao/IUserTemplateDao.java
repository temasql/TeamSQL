package kr.or.ddit.template.user_template.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.template.user_template.model.UserTemplateVO;

public interface IUserTemplateDao {

	int insertCommonTemplate(String user_id);
	
	int insertUserTemplate(UserTemplateVO userTemplateVo);
	
	String getAbb(Map<String, Object> map);
	
	List<UserTemplateVO> userTemplateList(UserTemplateVO userTemplateVO);
	
	List<UserTemplateVO> map(Map<String, Object> map);
	
	int updateUserTemplate(UserTemplateVO userTemplateVO);

	int deleteUserTemplate(UserTemplateVO userTemplateVO);
}
