package kr.or.ddit.template.common_template.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.template.common_template.model.CommonTemplateVO;

public interface ICommonTemplateDao {

	int insert(CommonTemplateVO commonTemplateVo);
	
	CommonTemplateVO get(String id);
	
	List<CommonTemplateVO> list();
	
	List<CommonTemplateVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
