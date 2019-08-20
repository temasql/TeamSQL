package kr.or.ddit.template.common_template.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.template.common_template.model.CommonTemplateVO;

@Repository
public class CommonTemplateDao implements ICommonTemplateDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	
	/**
	* Method : allTemplateList
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 전체 템플릿 리스트 조회
	*/
	@Override
	public List<CommonTemplateVO> allTemplateList() {
		return sqlSession.selectList("commonTemplate.allTemplateList");
	}

	
	/**
	* Method : templateCnt
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 템플릿 갯수
	*/
	@Override
	public int templateCnt() {
		return sqlSession.selectOne("commonTemplate.templateCnt");
	}

	
	/**
	* Method : templatePagingList
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 템플릿 페이징 리스트
	*/
	@Override
	public List<CommonTemplateVO> templatePagingList(Map<String, Object> map) {
		return sqlSession.selectList("commonTemplate.templatePagingList", map);
	}

	
	/**
	* Method : addTemplate
	* 작성자 : 이영은
	* 변경이력 :
	* @param templateVo
	* @return
	* Method 설명 : 공통 템플릿 추가
	*/
	@Override
	public int addTemplate(CommonTemplateVO templateVo) {
		return sqlSession.insert("commonTemplate.addTemplate", templateVo);
	}

	
	/**
	* Method : modifyTemplate
	* 작성자 : 이영은
	* 변경이력 :
	* @param templateVo
	* @return
	* Method 설명 : 공통 템플릿 수정
	*/
	@Override
	public int modifyTemplate(CommonTemplateVO templateVo) {
		return sqlSession.update("commonTemplate.modifyTemplate", templateVo);
	}

	
	/**
	* Method : deleteTemplate
	* 작성자 : 이영은
	* 변경이력 :
	* @param ctemplate_id
	* @return
	* Method 설명 : 공통 템플릿 삭제
	*/
	@Override
	public int deleteTemplate(int ctemplate_id) {
		return sqlSession.delete("commonTemplate.deleteTemplate", ctemplate_id);
	}

	

}
