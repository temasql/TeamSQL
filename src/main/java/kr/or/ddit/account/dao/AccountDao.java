package kr.or.ddit.account.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.account.model.AccountVO;

//@Repository
public class AccountDao implements IAccountDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(AccountVO accountVO) {
		return sqlSession.insert("account.insert", accountVO);
	}
	
	@Override
	public AccountVO get(String id) {
		return sqlSession.selectOne("account.get", id);
	}
	
	@Override
	public List<AccountVO> list() {
		return sqlSession.selectList("account.list");
	}

	@Override
	public List<AccountVO> map(Map<String, Object> map) {
		return sqlSession.selectList("account.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("account.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("account.delete", id);
	}

	

}
