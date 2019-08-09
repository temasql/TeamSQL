/**
 * 
 */
package kr.or.ddit.sqlEdiotView.service;

import java.sql.Connection;

/**
* ISqlEditorTable.java
*
* @author 이중석
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* 이중석 최초 생성
*
* </pre>
*/
public interface ISqlEditorViewService {

	/**
	* Method : createView
	* 작성자 : 이중석
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 우클릭 이벤트로 뷰를 생성하는 메서드
	*/
	int createView(String view_name, String viewQuery, Connection conn);

	/**
	* Method : deleteView
	* 작성자 : 이중석
	* 변경이력 :
	* @param view_name
	* @param conn
	* Method 설명 :
	*/
	int deleteView(String view_name, Connection conn);

	/**
	* Method : updateView
	* 작성자 : 이중석
	* 변경이력 :
	* @param view_name
	* @param conn
	* @return
	* Method 설명 :
	*/
	String updateView(String view_name, Connection conn);

	/**
	* Method : updateViewPost
	* 작성자 : 이중석
	* 변경이력 :
	* @param view_name
	* @param conn
	* @return
	* Method 설명 :
	*/
	String updateViewPost(String oldVN, String view_name,String viewQuery, Connection conn);
	
}
