package kr.or.ddit.blacklist.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.blacklist.model.BlackListVO;

//@Repository
public class BlackListDao implements IBlackListDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(BlackListVO blackListVo) {
		return sqlSession.insert("blackList.insert", blackListVo);
	}
	
	@Override
	public BlackListVO get(String id) {
		return sqlSession.selectOne("blackList.get", id);
	}
	
	@Override
	public List<BlackListVO> list() {
		return sqlSession.selectList("blackList.list");
	}

	@Override
	public List<BlackListVO> map(Map<String, Object> map) {
		return sqlSession.selectList("blackList.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("blackList.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("blackList.delete", id);
	}

	

}
