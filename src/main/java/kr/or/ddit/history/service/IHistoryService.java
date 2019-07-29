package kr.or.ddit.history.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.history.model.ChangedVO;
import kr.or.ddit.history.model.HistoryVO;

public interface IHistoryService {
	
	/**
	 * 
	* Method : accountAndChangedList
	* 작성자 : 강호길
	* 변경이력 :
	* @param user_id 사용자 아이디
	* @return
	* Method 설명 : DB 계정명 / 변경일자 리스트 조회
	 */
	List<HistoryVO> changedList(String user_id);
	
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
	* Method : changedDetailList
	* 작성자 : 강호길
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : DB변경 상세 리스트 조회
	 */
	List<HistoryVO> changedDetailList(String object_owner);
	
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
}
