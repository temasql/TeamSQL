package kr.or.ddit.worksheet.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface IWorksheetDao {
	
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
	Map<String, Object> anotherRun(String query, Connection conn);
	
	/**
	 * 
	* Method : ddlRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 DDL문 처리
	 */
	Map<String, String> ddlRun(String query, Connection conn);
	
	/**
	 * 
	* Method : commitRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 commit문 처리
	 */
	int commitRun(String query, Connection conn);
	
	/**
	 * 
	* Method : rollbackRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 rollback문 처리
	 */
	int rollbackRun(String query, Connection conn);
	

	/**
	 * 
	* Method : planInsert
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 실행계획 등록
	 */
	String planInsert(String query, Connection conn);
	

	/**
	 * 
	* Method : planRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param conn
	* @return
	* Method 설명 : 실행계획 실행
	 */
	List<String> planRun(Connection conn);
}
