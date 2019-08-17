package kr.or.ddit.blacklist.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.blacklist.model.BlackListVO;

public interface IBlackListDao {

	/**
	* Method : insertBlackList
	* 작성자 : 이중석
	* 변경이력 :
	* @param blackListVo
	* @return
	* Method 설명 : 블랙리스트 등록
	*/
	int insertBlackList(BlackListVO blackListVo);
	
	/**
	* Method : blackList
	* 작성자 : 이중석
	* 변경이력 :
	* @param pageMap
	* @return
	* Method 설명 : 블랙리스트 페이징 처리
	*/
	List<BlackListVO> blackList(Map<String, Object> pageMap);
	
	List<BlackListVO> loginBlackListUserCheck(String user_id_fk);

	/**
	* Method : blackListSearchCount
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id_fk
	* @return
	* Method 설명 : 블랙리스트 수
	*/
	int blackListSearchCount(String search);

	/**
	* Method : deleteBlackListMg
	* 작성자 : 이중석
	* 변경이력 :
	* @param userMap
	* @return
	* Method 설명 : 블랙리스트 해제
	*/
	int deleteBlackListMG(Map<String, Object> userMap);
}
