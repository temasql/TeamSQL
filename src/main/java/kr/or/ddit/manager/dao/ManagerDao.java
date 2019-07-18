package kr.or.ddit.manager.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.manager.model.ManagerVO;

//@Repository
public class ManagerDao implements IManagerDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(ManagerVO managerVo) {
		return sqlSession.insert("manager.insert", managerVo);
	}
	
	@Override
	public ManagerVO get(String id) {
		return sqlSession.selectOne("manager.get", id);
	}
	
	@Override
	public List<ManagerVO> list() {
		return sqlSession.selectList("manager.list");
	}

	@Override
	public List<ManagerVO> map(Map<String, Object> map) {
		return sqlSession.selectList("manager.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("manager.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("manager.delete", id);
	}

	

}
