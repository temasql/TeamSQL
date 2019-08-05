/**
 * 
 */
package kr.or.ddit.sqlEditorFunction.dao;

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
public class SqlEditorFunctionDao implements ISqlEditorFunctionDao {


	private static final Logger logger = LoggerFactory.getLogger(SqlEditorFunctionDao.class);
	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public boolean createFunction(String query, Connection conn) {
		Statement stmt = null;
		boolean result = false;
		
		try {
			stmt = conn.createStatement();
			result = stmt.execute(query);
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		} finally {
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
		}
		
		return result;
	}

	@Override
	public List<String> getFunctionCode(String functionName, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		
		String sql = "SELECT TEXT FROM USER_SOURCE WHERE TYPE = 'FUNCTION' AND NAME = ? ORDER BY NAME, LINE";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, functionName.trim().toUpperCase());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString("TEXT"));
			}
			
		} catch (SQLException e) {
			list = null;
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
		}
		
		return list;
	}

	@Override
	public List<FunctionDetailVO> fucntionDetail(Map<String, String> map) {
		return sqlSession.selectList("sqlEditorFunction.fucntionDetail", map);
	}

	@Override
	public int deleteFunction(String functionName) {
		return sqlSession.update("sqlEditorFunction.deleteFunction", functionName);
	}

}
