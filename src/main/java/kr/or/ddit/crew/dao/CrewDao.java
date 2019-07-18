package kr.or.ddit.crew.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.crew.model.CrewVO;

//@Repository
public class CrewDao implements ICrewDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(CrewVO crewVo) {
		return sqlSession.insert("crew.insert", crewVo);
	}
	
	@Override
	public CrewVO get(String id) {
		return sqlSession.selectOne("crew.get", id);
	}
	
	@Override
	public List<CrewVO> list() {
		return sqlSession.selectList("crew.list");
	}

	@Override
	public List<CrewVO> map(Map<String, Object> map) {
		return sqlSession.selectList("crew.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("crew.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("crew.delete", id);
	}

	

}
