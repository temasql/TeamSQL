package kr.or.ddit.domain.common_domain.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.domain.common_domain.model.CommonDomainVO;

public interface ICommonDomainDao {

	int insert(CommonDomainVO commonDomainVo);
	
	CommonDomainVO get(String id);
	
	List<CommonDomainVO> list();
	
	List<CommonDomainVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
