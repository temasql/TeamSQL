package kr.or.ddit.template.user_template.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.template.user_template.model.UserTemplateVO;

@Repository
public class UserTemplateDao implements IUserTemplateDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	* Method : insertCommonTemplate
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 : 사용자가 회원가입할때 공통 템플릿을  사용자 개인 템플릿에 생성하는 쿼리
	*/
	@Override
	public int insertCommonTemplate(String user_id) {
		return sqlSession.insert("userTemplate.insertCommonTemplate", user_id);
	}

	
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
