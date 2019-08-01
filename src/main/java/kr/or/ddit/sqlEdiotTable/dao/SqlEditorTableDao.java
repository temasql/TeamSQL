/**
 * 
 */
package kr.or.ddit.sqlEdiotTable.dao;

import java.sql.Connection;
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
public class SqlEditorTableDao implements ISqlEditorTableDao {

	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	/**
	* Method : createTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 테이블패키지 우클릭 후 테이블 생성 
	*/
	@Override
	public int createTable(String query) {
		return sqlSession.update("sqlEditorTable.createTable", query);
	}
	
	/**
	* Method : selectTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 테이블 우클릭 후 테이블 조회 
	*/
	@Override
	public List<List<String>> selectTable(String query, Connection conn) {
		
		Connection cc = conn;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<List<String>> resultList = new ArrayList<List<String>>();
		List<String> columnNameList = new ArrayList<String>();
		try {
			stmt = cc.createStatement();
			
			rs = stmt.executeQuery(query);
			int columnCount = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				columnNameList.add(rs.getMetaData().getColumnName(i)); 
			}
			resultList.add(columnNameList);
			
			while(rs.next()) {
				List<String> dataList = new ArrayList<String>();
				for (int i = 0; i < columnNameList.size(); i++) {
					dataList.add(rs.getString(columnNameList.get(i)));
				}
				resultList.add(dataList);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
		}
		return resultList;
	}

}
