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

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
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
public class SqlEditorTriggerDao implements ISqlEditorTriggerDao {

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

	

}
