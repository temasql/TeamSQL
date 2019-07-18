package kr.or.ddit.filtering.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.filtering.model.FilteringVO;

//@Repository
public class FilteringDao implements IFilteringDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(FilteringVO filteringVo) {
		return sqlSession.insert("filtering.insert", filteringVo);
	}
	
	@Override
	public FilteringVO get(String id) {
		return sqlSession.selectOne("filtering.get", id);
	}
	
	@Override
	public List<FilteringVO> list() {
		return sqlSession.selectList("filtering.list");
	}

	@Override
	public List<FilteringVO> map(Map<String, Object> map) {
		return sqlSession.selectList("filtering.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("filtering.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("filtering.delete", id);
	}

	

}
