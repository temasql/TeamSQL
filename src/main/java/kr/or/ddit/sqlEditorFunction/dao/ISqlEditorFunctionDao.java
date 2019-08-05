/**
 * 
 */
package kr.or.ddit.sqlEditorFunction.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import kr.or.ddit.sqlEditorFunction.model.FunctionDetailVO;

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
public interface ISqlEditorFunctionDao {
	
	boolean createFunction(String query, Connection conn);
	
	List<String> getFunctionCode(String functionName, Connection conn);
	
	List<FunctionDetailVO> fucntionDetail(Map<String, String> map);
	
}
