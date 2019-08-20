package kr.or.ddit.template.common_template.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.template.common_template.model.CommonTemplateVO;

public interface ICommonTemplateDao {

	/**
	* Method : allTemplateList
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 전체 템플릿 리스트 조회
	*/
	List<CommonTemplateVO> allTemplateList();
	
	
	/**
	* Method : templateCnt
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 템플릿 갯수
	*/
	int templateCnt();
	
	
	/**
	* Method : templatePagingList
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 템플릿 페이징 리스트
	*/
	List<CommonTemplateVO> templatePagingList(Map<String, Object> map);
	
	
	/**
	* Method : addTemplate
	* 작성자 : 이영은
	* 변경이력 :
	* @param templateVo
	* @return
	* Method 설명 : 공통 템플릿 추가
	*/
	int addTemplate(CommonTemplateVO templateVo);
	
	
	/**
	* Method : modifyTemplate
	* 작성자 : 이영은
	* 변경이력 :
	* @param templateVo
	* @return
	* Method 설명 : 공통 템플릿 수정
	*/
	int modifyTemplate(CommonTemplateVO templateVo);
	
	
	/**
	* Method : deleteTemplate
	* 작성자 : 이영은
	* 변경이력 :
	* @param ctemplate_id
	* @return
	* Method 설명 : 공통 템플릿 삭제
	*/
	int deleteTemplate(int ctemplate_id);
}
