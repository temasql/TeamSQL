/**
 * 
 */
package kr.or.ddit.sqlEdiotTable.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.sqlEdiotTable.model.SqlEditorTableVO;

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
	
	private static final Logger logger = LoggerFactory.getLogger(SqlEditorTableDao.class);
	/**
	* Method : getConstraint
	* 작성자 : 이중석
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 해당 테이블의 제약조건  조회
	*/
	public List<String> getData(String query, String checked) {
		logger.debug("Dao query = {}", query);
		List<SqlEditorTableVO> constraintList= sqlSession.selectList("sqlEditorTable.getDDLTable", query);
		List<String> dataList = new ArrayList<String>();
		logger.debug("dao Check: {}", checked);
		for (SqlEditorTableVO sqlEditorTableVO : constraintList) {
			switch (checked) {
			case "CONSTRAINT":
				logger.debug("==CONSTRAINT==");
				dataList.add(sqlEditorTableVO.getTABLE_NAME());
				break;
			case "INDEX":
				logger.debug("==INDEX==");
				dataList.add(sqlEditorTableVO.getINDEX_NAME());
				break;
			case "VIEW":
				logger.debug("==VIEW==");
				dataList.add(sqlEditorTableVO.getVIEW_NAME());
				break;
			case "TRIGGER":
				logger.debug("==TRIGGER==");
				dataList.add(sqlEditorTableVO.getTRIGGER_NAME());
				break;

			}
		}
		logger.debug("==dataList : {}==", dataList);
		return dataList;
	}
	
	/**
	* Method : gettDDL
	* 작성자 : 이중석
	* 변경이력 :
	* @param account_id
	* @param constraint
	* @return
	* Method 설명 : 해당 테이블의 제약조건 DDL 조회
	*/
	@Override
	public String getDDL(String checked, String account_id, String target ) {
		String query = "SELECT DBMS_METADATA.GET_DDL('" + checked + "','" + target + "', '" + account_id.toUpperCase() + "') DDL FROM DUAL";
		List<SqlEditorTableVO> ddlList= sqlSession.selectList("sqlEditorTable.getDDLTable", query);
		String ddl = "";
		for (SqlEditorTableVO sqlEditorTableVO : ddlList) {
			ddl += sqlEditorTableVO.getDDL().replaceAll("\n", "\r\n");
			ddl += ";\r\n";
		}
		return ddl;
	}
	
	public String getCommentDDL(String account_id, String tableName) {
		
		String query = "SELECT DBMS_METADATA.GET_DEPENDENT_DDL('COMMENT','" + tableName.toUpperCase() + "','" + account_id.toUpperCase() + "') DDL " + 
						"FROM DBA_TABLES " + 
						"WHERE OWNER = '" + account_id.toUpperCase() + "' " +
						"AND TABLE_NAME = '" + tableName + "'"; 
		List<SqlEditorTableVO> ddlList = sqlSession.selectList("sqlEditorTable.getDDLTable", query);
		
		String ddl = "";
		for (SqlEditorTableVO sqlEditorTableVO : ddlList) {
			ddl += sqlEditorTableVO.getDDL().replaceAll("\n", "\r\n");
			ddl += ";\r\n";
		}
		return ddl;
	}
	@Override
	public List<String> getColumns(String tableName, Connection conn) {
		Connection cc = conn;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<String> columnList = new ArrayList<String>();
		
		try {
			String query = "SELECT CNAME FROM SYS.COL WHERE TNAME = ?";
			pstmt = cc.prepareStatement(query);
			pstmt.setString(1, tableName.toUpperCase());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				columnList.add(rs.getString("CNAME"));
			}
			
		} catch (SQLException e) {
			columnList = null;
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
		}
		
		return columnList;
	}

	/**
	* Method : selectTableColumnData
	* 작성자 : 이중석
	* 변경이력 :
	* @param tableName
	* @return
	* Method 설명 :
	*/
	@Override
	public List<SqlEditorTableVO> selectTableColumnData(String tableName, Connection conn) {
		
		Connection cc = conn;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<SqlEditorTableVO> columnDataList= new ArrayList<SqlEditorTableVO>();
		try {
			stmt = cc.createStatement();
			
			StringBuffer query = new StringBuffer(); 
					query.append("SELECT A.COLUMN_NAME, A.DATA_TYPE, A.DATA_LENGTH, A.NULLABLE, ");  
					query.append("A.DATA_DEFAULT, B.COMMENTS FROM USER_TAB_COLUMNS A, USER_COL_COMMENTS B "); 
					query.append("WHERE A. TABLE_NAME = B.TABLE_NAME AND A.COLUMN_NAME = B.COLUMN_NAME ");
					query.append("AND A.TABLE_NAME = '" + tableName+ "' ORDER BY A.COLUMN_ID");
			rs = stmt.executeQuery(query.toString());
			
			while(rs.next()) {
				SqlEditorTableVO tableVo = new SqlEditorTableVO();
				tableVo.setColumn_name(rs.getString("COLUMN_NAME"));
				tableVo.setData_type(rs.getString("DATA_TYPE"));
				tableVo.setData_length(rs.getInt("DATA_LENGTH"));
				tableVo.setNullable(rs.getString("NULLABLE"));
				tableVo.setData_default(rs.getString("DATA_DEFAULT"));
				tableVo.setComments(rs.getString("COMMENTS"));
				columnDataList.add(tableVo);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
		}
		return columnDataList;
	}

	/**
	* Method : selectTablePrimaryKey
	* 작성자 : 이중석
	* 변경이력 :
	* @param tableName
	* @return
	* Method 설명 :
	*/
	@Override
	public List<String> selectTablePrimaryKey(String tableName, Connection conn) {
		Connection cc = conn;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<String> primaryKeyList= new ArrayList<String>();
		try {
			stmt = cc.createStatement();
			
			StringBuffer query = new StringBuffer(); 
					query.append("SELECT B.COLUMN_NAME ");  
					query.append("FROM USER_CONSTRAINTS A, USER_CONS_COLUMNS B "); 
					query.append("WHERE A.CONSTRAINT_NAME = B.CONSTRAINT_NAME ");
					query.append("AND A.TABLE_NAME = '" + tableName+ "' AND A.CONSTRAINT_TYPE = 'P'");
			rs = stmt.executeQuery(query.toString());
			while(rs.next()) {
				primaryKeyList.add(rs.getString("COLUMN_NAME"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
		}
		
		return primaryKeyList;
	}

	/**
	* Method : createVO
	* 작성자 : 이영은
	* 변경이력 :
	* @param tableName
	* @param conn
	* @return
	* Method 설명 : 자바 모델 생성
	*/
	@Override
	public String createVO(String tableName, Connection conn) {
		Connection cc = conn;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<String> createVOList= new ArrayList<String>();
		StringBuffer voStr = new StringBuffer();
		try {
			stmt = cc.createStatement();
			
			StringBuffer query = new StringBuffer(); 
					query.append("select 'private ' || decode(lower(data_type), 'number', 'int ', 'date', 'Date ', 'String ') || ");  
					query.append("lower(column_name) || ';' as javaVO "); 
					query.append("from cols ");
					query.append("where lower(table_name) = '" + tableName+ "'");
			rs = stmt.executeQuery(query.toString());
			while(rs.next()) {
				createVOList.add(rs.getString("JAVAVO"));
			}
			
			for (String var : createVOList) {
				voStr.append(var + "\r\n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
		}
		
		return voStr.toString();
	}

	
}
