package kr.or.ddit.domain.common_domain.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.domain.common_domain.model.CommonDomainVO;

public interface ICommonDomainService {
	
	/**
	* Method : allDomainList
	* 작성자 : 전체 도메인 리스트
	* 변경이력 :
	* @return
	* Method 설명 :
	*/
	List<CommonDomainVO> allDomainList();
	
	
	/**
	* Method : domainPagingList
	* 작성자 : 이영은
	* 변경이력 :
	* @param pageMap
	* @return
	* Method 설명 : 도메인 페이징 리스트
	*/
	Map<String, Object> domainPagingList(Map<String, Object> map);
	
	
	/**
	* Method : addDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param domainVo
	* @return
	* Method 설명 : 공통 도메인 추가
	*/
	int addDomain(CommonDomainVO domainVo);
}
