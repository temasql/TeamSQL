package kr.or.ddit.domain.user_domain.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.domain.user_domain.model.UserDomainVO;

//@Repository
public class UserDomainDao implements IUserDomainDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(UserDomainVO userDomainVo) {
		return sqlSession.insert("userDomain.insert", userDomainVo);
	}
	
	@Override
	public UserDomainVO get(String id) {
		return sqlSession.selectOne("userDomain.get", id);
	}
	
	@Override
	public List<UserDomainVO> list() {
		return sqlSession.selectList("userDomain.list");
	}

	@Override
	public List<UserDomainVO> map(Map<String, Object> map) {
		return sqlSession.selectList("userDomain.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("userDomain.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("userDomain.delete", id);
	}

	

}
