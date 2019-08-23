package kr.or.ddit.template.common_template.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.template.common_template.dao.ICommonTemplateDao;
import kr.or.ddit.template.common_template.model.CommonTemplateVO;

@Service
public class CommonTemplateService implements ICommonTemplateService{

	@Resource(name = "commonTemplateDao")
	private ICommonTemplateDao commonTemplateDao;

	
	/**
	* Method : allTemplateList
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 전체 템플릿 리스트 조회
	*/
	@Override
	public List<CommonTemplateVO> allTemplateList() {
		return commonTemplateDao.allTemplateList();
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
	public Map<String, Object> templatePagingList(Map<String, Object> map) {
		List<CommonTemplateVO> templatePagingList = commonTemplateDao.templatePagingList(map);
		
		int templateCnt = commonTemplateDao.templateCnt();
		int pageSize = (int) map.get("pageSize");
		int pagination = (int) Math.ceil((double)templateCnt/pageSize);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("templateList", templatePagingList);
		resultMap.put("paginationSize", pagination);
		
		return resultMap;
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
		return commonTemplateDao.addTemplate(templateVo);
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
		return commonTemplateDao.modifyTemplate(templateVo);
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
		return commonTemplateDao.deleteTemplate(ctemplate_id);
	}


	/**
	* Method : getAbb
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 템플릿 등록 시 템플릿 약어 존재여부 체크
	*/
	@Override
	public String getAbb(Map<String, Object> map) {
		return commonTemplateDao.getAbb(map);
	}


	/**
	* Method : findTemplate
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 입력한 템플릿 아이디와 일치하는 약어 검색
	*/
	@Override
	public String findTemplate(Map<String, Object> map) {
		return commonTemplateDao.findTemplate(map);
	}

	

}
