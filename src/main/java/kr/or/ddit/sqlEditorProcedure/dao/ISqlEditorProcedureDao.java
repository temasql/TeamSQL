/**
 * 
 */
package kr.or.ddit.sqlEditorProcedure.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import kr.or.ddit.sqlEditorFunction.model.FunctionDetailVO;
import kr.or.ddit.sqlEditorProcedure.model.ProcedureDetailVO;

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
public interface ISqlEditorProcedureDao {
	
	String getProcedureCode(Map<String, String> map);
	
	ProcedureDetailVO procedureDetail(Map<String, String> map);
	
	int deleteProcedure(String procedure_name);
}
