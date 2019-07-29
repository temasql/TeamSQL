package kr.or.ddit.blacklist.service;

import java.util.Map;

import kr.or.ddit.blacklist.model.BlackListVO;

public interface IBlackListService {
	
	/**
	* Method : insertBlackList
	* 작성자 : 이중석
	* 변경이력 :
	* @param BlackListVO
	* @return
	* Method 설명 : 블랙리스트 등록
	*/
	int insertBlackList(BlackListVO BlackListVO);
	
	/**
	* Method : blackList
	* 작성자 : 이중석
	* 변경이력 :
	* @param pageMap
	* @return
	* Method 설명 : 블랙리스트 페이징 처리
	*/
	Map<String, Object> blackList(Map<String, Object> pageMap);
	

	/**
	* Method : deleteBlacListMG
	* 작성자 : 이중석
	* 변경이력 :
	* @param string
	* Method 설명 : 블랙리스트 해제
	*/
	int deleteBlackListMG(Map<String, Object> userMap);
}
