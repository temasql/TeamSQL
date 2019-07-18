package kr.or.ddit.filtering.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.filtering.model.FilteringVO;

public interface IFilteringDao {

	int insert(FilteringVO filteringVo);
	
	FilteringVO get(String id);
	
	List<FilteringVO> list();
	
	List<FilteringVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
