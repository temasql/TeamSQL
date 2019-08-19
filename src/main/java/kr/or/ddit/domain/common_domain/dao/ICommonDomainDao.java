package kr.or.ddit.domain.common_domain.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.domain.common_domain.model.CommonDomainVO;

public interface ICommonDomainDao {

	/**
	* Method : allDomainList
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 전체 도메인 리스트 조회
	*/
	List<CommonDomainVO> allDomainList();
	
	
	/**
	* Method : domainCnt
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 도메인 갯수
	*/
	int domainCnt();
	
	
	/**
	* Method : domainPagingList
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 도메인 페이징 리스트
	*/
	List<CommonDomainVO> domainPagingList(Map<String, Object> map);
	
	
	/**
	* Method : addDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param domainVo
	* @return
	* Method 설명 : 공통 도메인 추가
	*/
	int addDomain(CommonDomainVO domainVo);


	/**
	* Method : modifyDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param domainVo
	* @return
	* Method 설명 : 공통 도메인 수정
	*/
	int modifyDomain(CommonDomainVO domainVo);
	
	
	/**
	* Method : deleteDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param cdomain_id
	* @return
	* Method 설명 : 공통 도메인 삭제
	*/
	int deleteDomain(int cdomain_id);
}
