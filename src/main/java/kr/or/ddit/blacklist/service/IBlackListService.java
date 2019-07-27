package kr.or.ddit.blacklist.service;

import java.util.Map;

import kr.or.ddit.blacklist.model.BlackListVO;

public interface IBlackListService {
	
	int insertBlackList(BlackListVO BlackListVO);
	
	BlackListVO get(String id);
	
	Map<String, Object> blackList(Map<String, Object> pageMap);
	
	int blackListSearchCount(String user_id_fk);
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);

	/**
	* Method : deleteBlacListMG
	* 작성자 : 이중석
	* 변경이력 :
	* @param string
	* Method 설명 :
	*/
	int deleteBlackListMG(Map<String, Object> userMap);
}
