package kr.or.ddit.ts_file.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.ts_file.model.TSFileVO;

//@Repository
public class TSFileDao implements ITSFileDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(TSFileVO tsFileVo) {
		return sqlSession.insert("tsFile.insert", tsFileVo);
	}
	
	@Override
	public TSFileVO get(String id) {
		return sqlSession.selectOne("tsFile.get", id);
	}
	
	@Override
	public List<TSFileVO> list() {
		return sqlSession.selectList("tsFile.list");
	}

	@Override
	public List<TSFileVO> map(Map<String, Object> map) {
		return sqlSession.selectList("tsFile.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("tsFile.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("tsFile.delete", id);
	}

	

}
