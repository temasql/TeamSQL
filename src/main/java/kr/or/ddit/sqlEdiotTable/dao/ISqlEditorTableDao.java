/**
 * 
 */
package kr.or.ddit.sqlEdiotTable.dao;

import java.sql.Connection;
import java.util.List;

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
public interface ISqlEditorTableDao {

	/**
	* Method : createTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 우클릭 이벤트로 테이블을 생성하는 메서드
	*/
	public int createTable(String query);
	
	/**
	* Method : selectTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 우클릭 이벤트로 테이블을 조회 하는메서드
	*/
	public List<List<String>> selectTable(String query, Connection conn);
}
