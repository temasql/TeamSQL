package kr.or.ddit.filtering.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.filtering.dao.IFilteringDao;
import kr.or.ddit.filtering.model.FilteringVO;

//@Service
public class FilteringService implements IFilteringService{

	@Resource(name = "filteringDao")
	private IFilteringDao filteringDao;

	@Override
	public int insert(FilteringVO filteringVo) {
		return filteringDao.insert(filteringVo);
	}
	
	@Override
	public FilteringVO get(String id) {
		return filteringDao.get(id);
	}
	
	@Override
	public List<FilteringVO> list() {
		return filteringDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<FilteringVO> mapList =  filteringDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return filteringDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return filteringDao.delete(id);
	}

}
