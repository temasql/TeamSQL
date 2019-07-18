package kr.or.ddit.history.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.history.model.HistoryVO;

//@Repository
public class HistoryDao implements IHistoryDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(HistoryVO historyVo) {
		return sqlSession.insert("history.insert", historyVo);
	}
	
	@Override
	public HistoryVO get(String id) {
		return sqlSession.selectOne("history.get", id);
	}
	
	@Override
	public List<HistoryVO> list() {
		return sqlSession.selectList("history.list");
	}

	@Override
	public List<HistoryVO> map(Map<String, Object> map) {
		return sqlSession.selectList("history.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("history.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("history.delete", id);
	}

	

}
