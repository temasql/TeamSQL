/**
 * 
 */
package kr.or.ddit.sqlEditorTrigger.dao;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.sqlEditorTrigger.model.MyTriggerCodeVO;
import kr.or.ddit.sqlEditorTrigger.model.TriggerDetailVO;


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
public class SqlEditorTriggerDao implements ISqlEditorTriggerDao {


	private static final Logger logger = LoggerFactory.getLogger(SqlEditorTriggerDao.class);
	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	

	@Override
	public boolean createTrigger(String query, Connection conn) {
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
	public List<MyTriggerCodeVO> getTriggerCode(Map<String, String> map, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyTriggerCodeVO> list = new ArrayList<MyTriggerCodeVO>();
		
		try {
			
			String sql = "SELECT DESCRIPTION, TRIGGER_BODY FROM SYS.ALL_TRIGGERS WHERE OWNER = ? AND TRIGGER_NAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, map.get("accountId").trim());
			pstmt.setString(2, map.get("triggerName").trim());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MyTriggerCodeVO mVO = new MyTriggerCodeVO();
				mVO.setDescription(rs.getString("DESCRIPTION"));
				mVO.setTrigger_body(rs.getString("TRIGGER_BODY"));
				list.add(mVO);
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
	public List<TriggerDetailVO> triggerDetail(String object_name) {
		return sqlSession.selectList("sqlEditorTrigger.triggerDetail", object_name);
	}

	@Override
	public int deleteTrigger(String triggerName) {
		return sqlSession.update("sqlEditorTrigger.deleteTrigger", triggerName);
	}


}
