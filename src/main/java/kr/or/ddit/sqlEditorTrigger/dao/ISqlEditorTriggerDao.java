/**
 * 
 */
package kr.or.ddit.sqlEditorTrigger.dao;

import java.sql.Connection;
import java.util.List;

import java.util.Map;

import kr.or.ddit.sqlEditorTrigger.model.MyTriggerCodeVO;
import kr.or.ddit.sqlEditorTrigger.model.TriggerDetailVO;

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
	

	List<MyTriggerCodeVO> getTriggerCode(Map<String, String> map, Connection conn);
	
	List<TriggerDetailVO> triggerDetail(String object_name);
	
	int deleteTrigger(String triggerName);

}
