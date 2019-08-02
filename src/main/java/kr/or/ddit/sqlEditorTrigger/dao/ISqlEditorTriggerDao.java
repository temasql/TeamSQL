/**
 * 
 */
package kr.or.ddit.sqlEditorTrigger.dao;

import java.sql.Connection;
import java.util.List;

/**
 * 
* ISqlEditorTriggerDao.java
*
* @author 김범휘
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자	      수정내용
* --------  ------------------------
* PC06      최초 생성
*
* </pre>
 */
public interface ISqlEditorTriggerDao {
	
	boolean createTrigger(String query, Connection conn);
	
	
}
