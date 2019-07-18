package kr.or.ddit.template.common_template.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.template.common_template.model.CommonTemplateVO;

//@Repository
public class CommonTemplateDao implements ICommonTemplateDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(CommonTemplateVO commonTemplateVo) {
		return sqlSession.insert("commonTemplate.insert", commonTemplateVo);
	}
	
	@Override
	public CommonTemplateVO get(String id) {
		return sqlSession.selectOne("commonTemplate.get", id);
	}
	
	@Override
	public List<CommonTemplateVO> list() {
		return sqlSession.selectList("commonTemplate.list");
	}

	@Override
	public List<CommonTemplateVO> map(Map<String, Object> map) {
		return sqlSession.selectList("commonTemplate.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("commonTemplate.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("commonTemplate.delete", id);
	}

	

}
