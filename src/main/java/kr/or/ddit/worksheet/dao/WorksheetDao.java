package kr.or.ddit.worksheet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorksheetDao implements IWorksheetDao {
	
	private static final Logger logger = LoggerFactory.getLogger(WorksheetDao.class);

	/**
	 * 
	* Method : selectRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 select문 처리
	 */
	@Override
	public List<List<String>> selectRun(String query, Connection conn) {
		Connection cc = conn;
		Statement stmt = null;
		ResultSet rs = null;
		
		if(query.contains("from")) query = query.replace("from", " from");
		if(query.contains("FROM")) query = query.replace("FROM", " FROM");
		if(query.contains("where")) query =  query.replace("where", " where");
		if(query.contains("WHERE")) query =  query.replace("WHERE", " WHERE");
		
		List<List<String>> resultList = new ArrayList<List<String>>();
		List<String> columnNameList = new ArrayList<String>();
		List<String> errorList = new ArrayList<String>();
		try {
			cc.setAutoCommit(false);
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
			
			errorList = null;
			
		} catch (SQLException e) {
			e.printStackTrace();
			resultList.clear();
			errorList.add(e.getMessage());
			resultList.add(errorList);
		} finally {
			if(rs!=null) try{ rs.close(); }catch(SQLException e){}
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
		}
		return resultList;
	}

	/**
	 * 
	* Method : anotherRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 select를 제외한 쿼리문 처리(commit, rollback제외)
	 */
	@Override
	public Map<String, Object> anotherRun(String query, Connection conn) {
		Connection cc = conn;
		PreparedStatement pstmt = null;
		
		String resultStr = "";
		String result = "";
		int resultCnt = 0;
		
		// enter 치환
		if(query.contains("set")) query = query.replace("set", " set");
		if(query.contains("SET")) query = query.replace("SET", " SET");
		if(query.contains("from")) query =  query.replace("from", " from");
		if(query.contains("FROM")) query =  query.replace("FROM", " FROM");
		if(query.contains("where")) query =  query.replace("where", " where");
		if(query.contains("WHERE")) query =  query.replace("WHERE", " WHERE");
		if(query.contains("values")) query =  query.replace("values", " values");
		if(query.contains("VALUES")) query =  query.replace("VALUES", " VALUES");
		
		if(query.contains("insert") || query.contains("INSERT")) resultStr = "INSERT";
		if(query.contains("update") || query.contains("UPDATE")) resultStr = "UPDATE";
		if(query.contains("delete") || query.contains("DELETE")) resultStr = "DELETE";
		
		try {
			cc.setAutoCommit(false);
			pstmt = cc.prepareStatement(query);
			resultCnt = pstmt.executeUpdate();
			result = "Y";
		} catch (SQLException e) {
			resultCnt = 0;
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultCnt", resultCnt);
		resultMap.put("resultStr", resultStr);
		resultMap.put("result", result);
		
		return resultMap;
	}

	/**
	 * 
	* Method : commitRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 commit문 처리
	 */
	@Override
	public int commitRun(String query, Connection conn) {
		Connection cc = conn;
		int resultCnt = 0;
		try {
			cc.setAutoCommit(false);
			cc.commit();
			resultCnt = 1;
		} catch (SQLException e) {
			resultCnt = 0;
			e.printStackTrace();
		}
		return resultCnt;
	}

	/**
	 * 
	* Method : rollbackRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 rollback문 처리
	 */
	@Override
	public int rollbackRun(String query, Connection conn) {
		Connection cc = conn;
		int resultCnt = 0;
		try {
			cc.setAutoCommit(false);
			cc.rollback();
			resultCnt = 1;
		} catch (SQLException e) {
			resultCnt = 0;
			e.printStackTrace();
		}
		return resultCnt;
	}

	/**
	 * 
	* Method : ddlRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 워크시트 DDL문 처리
	 */
	@Override
	public Map<String, String> ddlRun(String query, Connection conn) {
		Connection cc = conn;
		PreparedStatement pstmt = null;
		
		String resultStr = "";
		String result = "";
		String objectType = "";
		
		if(query.contains("as")) query =  query.replace("as", " as");
		if(query.contains("AS")) query =  query.replace("AS", " AS");
		if(query.contains("add")) query =  query.replace("add", " add");
		if(query.contains("ADD")) query =  query.replace("ADD", " ADD");
		if(query.contains("drop")) query =  query.replace("drop", " drop");
		if(query.contains("DROP")) query =  query.replace("DROP", " DROP");
		if(query.contains("modify")) query =  query.replace("modify", " modify");
		if(query.contains("MODIFY")) query =  query.replace("MODIFY", " MODIFY");
		
		if(query.contains("create") || query.contains("CREATE")) resultStr = "CREATE";
		if(query.contains("alter") || query.contains("ALTER")) resultStr = "ALTER";
		if(query.contains("drop") || query.contains("DROP")) resultStr = "DROP";
		if(query.contains("rename") || query.contains("RENAME")) resultStr = "RENAME";
		
		if(query.contains("table") || query.contains("TABLE")) objectType = "TABLE";
		if(query.contains("sequence") || query.contains("SEQUENCE")) objectType = "SEQUENCE";
		if(query.contains("trigger") || query.contains("TRIGGER")) objectType = "TRIGGER";
		if(query.contains("index") || query.contains("INDEX")) objectType = "INDEX";
		if(query.contains("procedure") || query.contains("PROCEDURE")) objectType = "PROCEDURE";
		if(query.contains("view") || query.contains("VIEW")) objectType = "VIEW";
		if(query.contains("function") || query.contains("FUNCTION")) objectType = "FUNCTION";
		
		
		try {
			cc.setAutoCommit(false);
			pstmt = cc.prepareStatement(query);
			pstmt.executeUpdate();
			result = "Y";
		} catch (SQLException e) {
			e.printStackTrace();
			logger.debug("에러메세지 : {}", e.getMessage());
			result = e.getMessage();
		} finally {
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
		}
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("resultStr", resultStr);
		resultMap.put("result", result);
		resultMap.put("objectType", objectType);
		
		return resultMap;
	}

	/**
	 * 
	* Method : planInsert
	* 작성자 : 김범휘
	* 변경이력 :
	* @param query
	* @param conn
	* @return
	* Method 설명 : 실행계획 등록
	 */
	@Override
	public String planInsert(String query, Connection conn) {
		Connection cc = conn;
		Statement stmt = null;
		String result = "";
		
		try {
			cc.setAutoCommit(false);
			stmt = cc.createStatement();
			stmt.executeQuery(query);
			result = "Y";
		} catch (SQLException e) {
			e.printStackTrace();
			result = e.getMessage();
		} finally {
			if(stmt!=null) try{ stmt.close(); }catch(SQLException e){}
		}
		return result;
	}

	/**
	 * 
	* Method : planRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param conn
	* @return
	* Method 설명 : 실행계획 실행
	 */
	@Override
	public List<String> planRun(Connection conn) {
		Connection cc = conn;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<String> resultList = new ArrayList<String>();
		
		try {
			cc.setAutoCommit(false);
			stmt = cc.createStatement();
			rs = stmt.executeQuery("select * from table(dbms_xplan.display)");
			
			while(rs.next()) {
				resultList.add(rs.getString("PLAN_TABLE_OUTPUT"));
			}
			
		} catch (SQLException e) {
			resultList = null;
			e.printStackTrace();
		}
		
		return resultList;
	}

}
