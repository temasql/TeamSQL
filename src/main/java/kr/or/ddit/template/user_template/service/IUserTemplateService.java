package kr.or.ddit.template.user_template.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.template.user_template.model.UserTemplateVO;

public interface IUserTemplateService {
	
	int insert(UserTemplateVO userTemplateVo);
	
	UserTemplateVO get(String id);
	
	List<UserTemplateVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}
