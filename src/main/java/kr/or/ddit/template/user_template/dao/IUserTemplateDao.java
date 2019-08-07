package kr.or.ddit.template.user_template.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.template.user_template.model.UserTemplateVO;

public interface IUserTemplateDao {

	int insertCommonTemplate(String user_id);
	
	int insertUserTemplate(UserTemplateVO userTemplateVo);
	
	UserTemplateVO get(String id);
	
	List<UserTemplateVO> userTemplateList(UserTemplateVO userTemplateVO);
	
	List<UserTemplateVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
