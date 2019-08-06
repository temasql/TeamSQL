package kr.or.ddit.sqlEditorProcedure.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import kr.or.ddit.sqlEditorFunction.model.FunctionDetailVO;
import kr.or.ddit.sqlEditorProcedure.model.ProcedureDetailVO;


public interface ISqlEditorProcedureService {
		
	String getProcedureCode(Map<String, String> map); 
	
	ProcedureDetailVO procedureDetail(Map<String, String> map);
	
	int deleteProcedure(String procedure_name);
}
