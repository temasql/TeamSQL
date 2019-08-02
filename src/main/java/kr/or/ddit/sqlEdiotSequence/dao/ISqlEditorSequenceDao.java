/**
 * 
 */
package kr.or.ddit.sqlEdiotSequence.dao;

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
public interface ISqlEditorSequenceDao {

	/**
	 * 
	* Method : createSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 우클릭 이벤트 시퀀스 생성
	 */
	int createSequence(String query);
	
	/**
	 * 
	* Method : selectSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 우클릭 이벤트 시퀀스 조회
	 */
	List<List<String>> selectSequence(String query, Connection conn);
	
}
