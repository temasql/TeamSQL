package kr.or.ddit.domain.common_domain.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.domain.common_domain.model.CommonDomainVO;

public interface ICommonDomainService {
	
	int insert(CommonDomainVO commonDomainVo);
	
	CommonDomainVO get(String id);
	
	List<CommonDomainVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}
