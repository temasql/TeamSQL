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

	
	/**
	* Method : insertUserTemplate
	* 작성자 : 손주형
	* 변경이력 :
	* @param userTemplateVo
	* @return
	* Method 설명 : 사용자가 템플릿을 등록(추가)할때 사용하는 쿼리
	*/
	@Override
	public int insertUserTemplate(UserTemplateVO userTemplateVo) {
		return sqlSession.insert("userTemplate.insertUserTemplate", userTemplateVo);
	}
	
	/**
	* Method : getAbb
	* 작성자 : 손주형
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 유저가 템플릿을 등록할 때 
	* 			  약어가 존재하는지 검색하는 메서드
	*/
	@Override
	public String getAbb(Map<String, Object> map) {
		return sqlSession.selectOne("userTemplate.getAbb", map);
	}
	
	/**
	* Method : userTemplateList
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : 각 유저별 템플릿 리스트 조회
	*/
	@Override
	public List<UserTemplateVO> userTemplateList(UserTemplateVO userTemplateVO) {
		return sqlSession.selectList("userTemplate.userTemplateList", userTemplateVO);
	}

	@Override
	public List<UserTemplateVO> map(Map<String, Object> map) {
		return sqlSession.selectList("userTemplate.map", map);
	}
	
	/**
	* Method : updateUserTemplate
	* 작성자 : 손주형
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 사용자가 사용자 템플릿을 수정하는 메서드
	*/
	@Override
	public int updateUserTemplate(UserTemplateVO userTemplateVO) {
		return sqlSession.update("userTemplate.updateUserTemplate", userTemplateVO);
	}

	/**
	* Method : deleteUserTemplate
	* 작성자 : 손주형
	* 변경이력 :
	* @param userTemplateVO
	* @return
	* Method 설명 : 사용자 템플릿을 삭제하는 메서드
	*/
	@Override
	public int deleteUserTemplate(UserTemplateVO userTemplateVO) {
		return sqlSession.delete("userTemplate.deleteUserTemplate", userTemplateVO);
	}


	/**
	* Method : getOriginal
	* 작성자 : 손주형
	* 변경이력 :
	* @param utemplate_abb
	* @return
	* Method 설명 : 사용자가 약어가 DB에 있는 약어와 일치하는 원문을 리턴
	*/
	@Override
	public String getOriginal(UserTemplateVO userTemplateVO) {
		return sqlSession.selectOne("userTemplate.getOriginal", userTemplateVO);
	}

	/**
	* Method : sameAbb
	* 작성자 : 손주형
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 입력한 템플릿ID와 일치하는 약어 검색
	*/
	@Override
	public String sameAbb(Map<String, Object> map) {
		return sqlSession.selectOne("userTemplate.sameAbb", map);
	}
}
