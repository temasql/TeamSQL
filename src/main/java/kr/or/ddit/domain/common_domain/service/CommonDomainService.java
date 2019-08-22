package kr.or.ddit.domain.common_domain.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.domain.common_domain.dao.ICommonDomainDao;
import kr.or.ddit.domain.common_domain.model.CommonDomainVO;

@Service
public class CommonDomainService implements ICommonDomainService{

	@Resource(name = "commonDomainDao")
	private ICommonDomainDao commonDomainDao;

	
	/**
	* Method : allDomainList
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 전체 도메인 리스트
	*/
	@Override
	public List<CommonDomainVO> allDomainList() {
		return commonDomainDao.allDomainList();
	}

	
	/**
	* Method : domainPagingList
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 도메인 페이징 리스트
	*/
	@Override
	public Map<String, Object> domainPagingList(Map<String, Object> map) {
		
		List<CommonDomainVO> domainPagingList =commonDomainDao.domainPagingList(map);
		
		int domainCnt = commonDomainDao.domainCnt();
		int pageSize = (int) map.get("pageSize");
		int pagination = (int) Math.ceil((double)domainCnt/pageSize);
				
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("domainList", domainPagingList);
		resultMap.put("paginationSize", pagination);
		
		return resultMap;
	}


	/**
	* Method : addDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param domainVo
	* @return
	* Method 설명 : 공통 도메인 추가
	*/
	@Override
	public int addDomain(CommonDomainVO domainVo) {
		return commonDomainDao.addDomain(domainVo);
	}


	/**
	* Method : modifyDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param domainVo
	* @return
	* Method 설명 : 공통 도메인 수정
	*/
	@Override
	public int modifyDomain(CommonDomainVO domainVo) {
		return commonDomainDao.modifyDomain(domainVo);
	}


	/**
	* Method : deleteDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param cdomain_id
	* @return
	* Method 설명 : 공통 도메인 삭제
	*/
	@Override
	public int deleteDomain(int cdomain_id) {
		return commonDomainDao.deleteDomain(cdomain_id);
	}
	
	
	/**
	* Method : getName
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 도메인 등록 시 도메인명 존재 여부 체크
	*/
	@Override
	public String getName(Map<String, Object> map) {
		return commonDomainDao.getName(map);
	}

	
	/**
	* Method : findDomain
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 입력한 도메인 아이디와 일치하는 도메인명 검색
	*/
	@Override
	public String findDomain(Map<String, Object> map) {
		return commonDomainDao.findDomain(map);
	}
}
