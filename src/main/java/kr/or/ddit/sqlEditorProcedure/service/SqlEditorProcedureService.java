package kr.or.ddit.sqlEditorProcedure.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.sqlEditorFunction.dao.ISqlEditorFunctionDao;
import kr.or.ddit.sqlEditorFunction.model.FunctionDetailVO;
import kr.or.ddit.sqlEditorProcedure.dao.ISqlEditorProcedureDao;
import kr.or.ddit.sqlEditorProcedure.model.ProcedureDetailVO;

@Service
public class SqlEditorProcedureService implements ISqlEditorProcedureService {

	@Resource(name = "sqlEditorProcedureDao")
	private ISqlEditorProcedureDao sqlEditorProcedureDao;

	@Override
	public String getProcedureCode(Map<String, String> map) {
		return sqlEditorProcedureDao.getProcedureCode(map);
	}

	@Override
	public ProcedureDetailVO procedureDetail(Map<String, String> map) {
		return sqlEditorProcedureDao.procedureDetail(map);
	}

	@Override
	public int deleteProcedure(String procedure_name) {
		return sqlEditorProcedureDao.deleteProcedure(procedure_name);
	}

	
}
