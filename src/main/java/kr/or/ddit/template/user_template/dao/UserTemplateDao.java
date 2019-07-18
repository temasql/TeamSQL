package kr.or.ddit.template.user_template.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.template.user_template.model.UserTemplateVO;

//@Repository
public class UserTemplateDao implements IUserTemplateDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(UserTemplateVO userTemplateVo) {
		return sqlSession.insert("userTemplate.insert", userTemplateVo);
	}
	
	@Override
	public UserTemplateVO get(String id) {
		return sqlSession.selectOne("userTemplate.get", id);
	}
	
	@Override
	public List<UserTemplateVO> list() {
		return sqlSession.selectList("userTemplate.list");
	}

	@Override
	public List<UserTemplateVO> map(Map<String, Object> map) {
		return sqlSession.selectList("userTemplate.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("userTemplate.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("userTemplate.delete", id);
	}

	

}
