package kr.or.ddit.history.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.history.model.ChangedVO;
import kr.or.ddit.history.model.HistoryTempVO;

public interface IHistoryService {
	
	
	/**
	 * 
	* Method : changedMain
	* 작성자 : 강호길
	* 변경이력 :
	* @param user_id_fk
	* @return
	* Method 설명 : DB계정, 마지막 변경일시, DB계정 생성자 조회 
	 */
	List<ChangedVO> changedMainList (String user_id_fk);
	
	/**
	 * 
	* Method : changedPagingList
	* 작성자 : 강호길
	* 변경이력 :
	* @param pageMap
	* @return
	* Method 설명 : DB 계정명 / 변경일자 페이징 리스트 조회
	 */
	Map<String, Object> changedPagingList(Map<String, Object> pageMap);
	
	
	/**
	 * 
	* Method : changedPagingList
	* 작성자 : 강호길
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : DB변경 이력 상세 페이징 리스트 조회
	 */
	Map<String, Object> changedDetailPagingList(Map<String, Object> pageMap);
	
	List<String> getAccountIdList(String user_id);
	
	HistoryTempVO getLastDateAndName(String account_id);
	
}
