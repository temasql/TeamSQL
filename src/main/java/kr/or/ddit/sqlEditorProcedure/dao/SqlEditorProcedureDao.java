/**
 * 
 */
package kr.or.ddit.sqlEditorProcedure.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;


import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.stereotype.Repository;

import kr.or.ddit.sqlEditorFunction.model.FunctionDetailVO;
import kr.or.ddit.sqlEditorProcedure.model.ProcedureDetailVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


/**
* SqlEditorTable.java
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
@Repository
public class SqlEditorProcedureDao implements ISqlEditorProcedureDao {


	private static final Logger logger = LoggerFactory.getLogger(SqlEditorProcedureDao.class);
	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public String getProcedureCode(Map<String, String> map) {
		return sqlSession.selectOne("sqlEditorProcedure.getProcedureCode", map);
	}

	@Override
	public ProcedureDetailVO procedureDetail(Map<String, String> map) {
		return sqlSession.selectOne("sqlEditorProcedure.procedureDetail", map);
	}

	@Override
	public int deleteProcedure(String procedure_name) {
		return sqlSession.update("sqlEditorProcedure.deleteProcedure", procedure_name);
	}


}
