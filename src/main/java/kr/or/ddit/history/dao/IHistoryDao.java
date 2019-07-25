package kr.or.ddit.history.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.history.model.HistoryVO;

public interface IHistoryDao {

	int insert(HistoryVO historyVo);
	
	/**
	 * 
	* Method : accountList
	* 작성자 : PC20
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : DB계정명 리스트 조회
	 */
	List<String> accountList(String user_id);
	
	/**
	 * 
	* Method : accountAndChangedList
	* 작성자 : PC20
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : DB 계정명 / 변경일자 리스트 조회
	 */
	List<HistoryVO> changedList(String account_id);
	
	List<HistoryVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
