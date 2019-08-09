/**
 * 
 */
package kr.or.ddit.sqlEdiotView.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SqlEditorViewDao implements ISqlEditorViewDao {
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	* Method : createView
	* 작성자 : 이중석
	* 변경이력 :
	* @param viewQuery
	* @return
	* Method 설명 :
	*/
	@Override
	public boolean createView(String viewQuery, Connection conn) {
		Statement stmt = null;
		
		boolean result = false;
		
		try {
			stmt = conn.createStatement();
			result = stmt.execute(viewQuery);
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		} finally {
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
		}
		
		return result;
	}

	/**
	* Method : deleteView
	* 작성자 : 이중석
	* 변경이력 :
	* @param dropQuery
	* @return
	* Method 설명 :
	*/
	@Override
	public boolean deleteView(String dropQuery, Connection conn) {
		Statement stmt = null;
		
		boolean result = false;
		
		try {
			stmt = conn.createStatement();
			result = stmt.execute(dropQuery);
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		} finally {
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
		}
		
		return result;
	}

	/**
	* Method : updateViewTA
	* 작성자 : 이중석
	* 변경이력 :
	* @param view_name
	* @param conn
	* @return
	* Method 설명 :
	*/
	@Override
	public String updateView(String view_name, Connection conn) {
		Connection cc = conn;
		Statement stmt = null;
		ResultSet rs = null;
		String textArea = "";
		try {
			stmt = cc.createStatement();
			
			String query = "SELECT TEXT FROM USER_VIEWS WHERE VIEW_NAME = '" + view_name + "'"; 
			rs = stmt.executeQuery(query);
			int idx = rs.getRow();
			while(rs.next()) {
				textArea = rs.getString("TEXT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
		}
		return textArea;
	}
	
}
