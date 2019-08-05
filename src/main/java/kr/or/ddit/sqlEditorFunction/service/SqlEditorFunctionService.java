package kr.or.ddit.sqlEditorFunction.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.sqlEditorFunction.dao.ISqlEditorFunctionDao;
import kr.or.ddit.sqlEditorFunction.model.FunctionDetailVO;

@Service
public class SqlEditorFunctionService implements ISqlEditorFunctionService {

	@Resource(name = "sqlEditorFunctionDao")
	private ISqlEditorFunctionDao sqlEditorFunctionDao;

	@Override
	public boolean createFunction(String query, Connection conn) {
		return sqlEditorFunctionDao.createFunction(query, conn);
	}

	@Override
	public List<String> getFunctionCode(String functionName, Connection conn) {
		return sqlEditorFunctionDao.getFunctionCode(functionName, conn);
	}

	@Override
	public List<FunctionDetailVO> fucntionDetail(Map<String, String> map) {
		return sqlEditorFunctionDao.fucntionDetail(map);
	}

	@Override
	public int deleteFunction(String functionName) {
		return sqlEditorFunctionDao.deleteFunction(functionName);
	}
	
}
