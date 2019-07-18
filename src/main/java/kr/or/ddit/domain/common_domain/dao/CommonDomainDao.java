package kr.or.ddit.domain.common_domain.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.domain.common_domain.model.CommonDomainVO;

//@Repository
public class CommonDomainDao implements ICommonDomainDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(CommonDomainVO commonDomainVo) {
		return sqlSession.insert("commonDomain.insert", commonDomainVo);
	}
	
	@Override
	public CommonDomainVO get(String id) {
		return sqlSession.selectOne("commonDomain.get", id);
	}
	
	@Override
	public List<CommonDomainVO> list() {
		return sqlSession.selectList("commonDomain.list");
	}

	@Override
	public List<CommonDomainVO> map(Map<String, Object> map) {
		return sqlSession.selectList("commonDomain.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("commonDomain.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("commonDomain.delete", id);
	}

	

}
