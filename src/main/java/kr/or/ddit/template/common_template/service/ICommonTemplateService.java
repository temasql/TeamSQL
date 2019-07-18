package kr.or.ddit.template.common_template.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.template.common_template.model.CommonTemplateVO;

public interface ICommonTemplateService {
	
	int insert(CommonTemplateVO commontemplateVo);
	
	CommonTemplateVO get(String id);
	
	List<CommonTemplateVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}
