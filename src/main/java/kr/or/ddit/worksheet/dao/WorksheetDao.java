package kr.or.ddit.worksheet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WorksheetDao implements IWorksheetDao {

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
		if(query.contains("where")) query =  query.replace("where", " where");
		
		List<List<String>> resultList = new ArrayList<List<String>>();
		List<String> columnNameList = new ArrayList<String>();
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
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			resultList = null;
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
	public int anotherRun(String query, Connection conn) {
		Connection cc = conn;
		PreparedStatement pstmt = null;
		
		if(query.contains("set")) query = query.replace("set", " set");
		if(query.contains("from")) query =  query.replace("from", " from");
		if(query.contains("where")) query =  query.replace("where", " where");
		if(query.contains("values")) query =  query.replace("values", " values");
		
		int resultCnt = 0;
		
		try {
			pstmt = cc.prepareStatement(query);
			resultCnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try{ pstmt.close(); }catch(SQLException e){}
		}
		
		return resultCnt;
	}


	
}
