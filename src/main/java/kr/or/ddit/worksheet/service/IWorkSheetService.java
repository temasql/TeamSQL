package kr.or.ddit.worksheet.service;

import java.sql.Connection;
import java.util.List;

public interface IWorkSheetService {
	
	/**
	 * 
	* Method : selectRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 select문 처리
	 */
	List<List<String>> selectRun(String query, Connection conn);
	
	/**
	 * 
	* Method : anotherRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 select를 제외한 쿼리문 처리(commit, rollback제외)
	 */
	int anotherRun(String query, Connection conn);
	
}
