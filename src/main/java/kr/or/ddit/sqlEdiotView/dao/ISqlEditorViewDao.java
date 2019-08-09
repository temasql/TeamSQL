/**
 * 
 */
package kr.or.ddit.sqlEdiotView.dao;

import java.sql.Connection;

public interface ISqlEditorViewDao {

	/**
	* Method : createView
	* 작성자 : 이중석
	* 변경이력 :
	* @param viewQuery
	* @return
	* Method 설명 :
	*/
	boolean createView(String viewQuery, Connection conn);

	/**
	* Method : deleteView
	* 작성자 : 이중석
	* 변경이력 :
	* @param dropQuery
	* @return
	* Method 설명 :
	*/
	boolean deleteView(String dropQuery, Connection conn);

	/**
	* Method : updateViewTA
	* 작성자 : 이중석
	* 변경이력 :
	* @param view_name
	* @param conn
	* @return
	* Method 설명 :
	*/
	String updateView(String view_name, Connection conn);
	
	
}
