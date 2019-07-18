package kr.or.ddit.template.user_template.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.template.user_template.model.UserTemplateVO;

public interface IUserTemplateDao {

	int insert(UserTemplateVO userTemplateVo);
	
	UserTemplateVO get(String id);
	
	List<UserTemplateVO> list();
	
	List<UserTemplateVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
