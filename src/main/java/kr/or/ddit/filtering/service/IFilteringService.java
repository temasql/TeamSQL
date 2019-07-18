package kr.or.ddit.filtering.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.filtering.model.FilteringVO;

public interface IFilteringService {
	
	int insert(FilteringVO filteringVo);
	
	FilteringVO get(String id);
	
	List<FilteringVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}
