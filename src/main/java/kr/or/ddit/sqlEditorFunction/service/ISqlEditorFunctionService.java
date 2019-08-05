package kr.or.ddit.sqlEditorFunction.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import kr.or.ddit.sqlEditorFunction.model.FunctionDetailVO;


public interface ISqlEditorFunctionService {
	
	boolean createFunction(String query, Connection conn);
	
	List<String> getFunctionCode(String functionName, Connection conn);
	
	List<FunctionDetailVO> fucntionDetail(Map<String, String> map);
	
	int deleteFunction(String functionName);
}
