/**
 * 
 */
package kr.or.ddit.sqlEdiotSequence.service;

import java.sql.Connection;
import java.util.List;
/**
 * 
* ISqlEditorTableService.java
*
* @author 강호길
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* 강호길 최초 생성
*
* </pre>
 */
public interface ISqlEditorSequenceService {

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
	
}
