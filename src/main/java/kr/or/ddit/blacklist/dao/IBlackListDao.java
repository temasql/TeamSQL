package kr.or.ddit.blacklist.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.blacklist.model.BlackListVO;

public interface IBlackListDao {

	int insertBlackList(BlackListVO blackListVo);
	
	BlackListVO get(String id);
	
	List<BlackListVO> blackList(Map<String, Object> pageMap);
	
	List<BlackListVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);

	/**
	* Method : blackListSearchCount
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id_fk
	* @return
	* Method 설명 :
	*/
	int blackListSearchCount(String search);

	/**
	* Method : deleteBlackListMg
	* 작성자 : 이중석
	* 변경이력 :
	* @param userMap
	* @return
	* Method 설명 :
	*/
	int deleteBlackListMG(Map<String, Object> userMap);
}
