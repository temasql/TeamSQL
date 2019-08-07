/**
 * 
 */
package kr.or.ddit.sqlEditorTable.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpSession;

import kr.or.ddit.sqlEdiotTable.dao.ISqlEditorTableDao;
import kr.or.ddit.sqlEdiotTable.model.SqlEditorTableVO;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.util.DBUtilForWorksheet;
import kr.or.ddit.util.SelectTableUtil;

/**
* SqlEditorTableDaoTest.java
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
public class SqlEditorTableDaoTest extends LogicTestEnv{

	@Resource(name = "sqlEditorTableDao")
	private ISqlEditorTableDao sqlEditorTableDao;
	
	
	/**
	* Method : createTableTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : JSON으로 받아온 테이블 이름과 컬럼데이터 배열로 테이블을 생성하는 테스트 
	*/
	@Test
	public void createTableTest() {
		/***Given***/
		String[][] array = {{
			 "PkCheck : 'false' " 		 // pkCheck    
			,"id : 'user_id' "      // colName
			,"dataType : 'VARCHAR2' "  // dataType
			,"Size : '123' "       // Size
			,"nullCheck : 'true' "          // nullCheck
			,"defaultVal : '123' "    	 	 // defaultValue
			,"comment : 'a' "      // comment
			,"TESTTABLE" // tableName     
			,"TeamSQL"			// account_id
		},{
			 "PkCheck : 'false' " 		 // pkCheck    
			,"id : 'user_id1' "      // colName
			,"dataType : 'VARCHAR2' "  // dataType
			,"Size : '123' "       // Size
			,"nullCheck : 'true' "          // nullCheck
			,"defaultVal : '123' "    	 	 // defaultValue
			,"comment : 'a' "      // comment
			,"TESTTABLE" // tableName     
			,"TeamSQL"			// account_id
		}};
		/***When***/
//		Map<String, Object> queryMap = new CreateTableUtil().getQuery(array); 
//		
//		String query = (String) queryMap.get("query"); 
//		int createCount = sqlEditorTableDao.createTable(query);
//		String drop = "DROP TABLE TEAMSQL.TESTTABLE";
//		sqlEditorTableDao.createTable(drop);
//		/***Then***/
//		assertEquals(0, createCount);
	}
	
	/**
	 * Method : createTableCommentTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : JSON으로 받아온 테이블 이름과 컬럼데이터 배열로 테이블을 생성하는 테스트 
	 */
	@Test
	public void createTableCommentTest() {
		/***Given***/
		String[][] array = {{
			 "PkCheck : 'false' " 		 // pkCheck    
			,"id : 'user_id' "      // colName
			,"dataType : 'VARCHAR2' "  // dataType
			,"Size : '123' "       // Size
			,"nullCheck : 'true' "          // nullCheck
			,"defaultVal : '123' "    	 	 // defaultValue
			,"comment : 'a' "      // comment
			,"TESTTABLE" // tableName     
			,"TeamSQL"			// account_id
		},{
			 "PkCheck : 'false' " 		 // pkCheck    
			,"id : 'user_id1' "      // colName
			,"dataType : 'VARCHAR2' "  // dataType
			,"Size : '123' "       // Size
			,"nullCheck : 'true' "          // nullCheck
			,"defaultVal : '123' "    	 	 // defaultValue
			,"comment : 'a' "      // comment
			,"TESTTABLE" // tableName     
			,"TeamSQL"			// account_id
		}};
		/***When***/
//		Map<String, Object> queryMap = new CreateTableUtil().getQuery(array); 
//		String query = (String) queryMap.get("query"); 
//		sqlEditorTableDao.createTable(query);
		
//		List<String> commentList = (List<String>) queryMap.get("commentQueryList"); 
//		int createCommentCount = sqlEditorTableDao.createTable(commentList.get(0));
		String drop = "DROP TABLE TEAMSQL.TESTTABLE";
		sqlEditorTableDao.createTable(drop);
		/***Then***/
//		assertEquals(0, createCommentCount);
	}
	
	private static final Logger logger = LoggerFactory.getLogger(SqlEditorTableDaoTest.class);
	
	/**
	* Method : selectTableTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : JDBC CONNECTION 테이블 우클릭 후 테이블 조회 테스트
	 * @throws SQLException 
	*/
	@Test
	public void selectTableTest() {
		MockHttpSession session = new MockHttpSession();
		Connection conn = DBUtilForWorksheet.getConnection("TeamSQL", "java", session);
		List<String> selectBoxList = new ArrayList<String>();
		selectBoxList.add("column");
		selectBoxList.add("data");
		selectBoxList.add("constraint");
		selectBoxList.add("trigger");
		selectBoxList.add("detail");
		selectBoxList.add("index");
		selectBoxList.add("DDL");
		
		String query = SelectTableUtil.selectQuery("column", "USERS");
		List<List<String>> selectList = sqlEditorTableDao.selectTable(query, conn);
		logger.debug("selectList ==>{}", selectList);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Method : selectTableColumndataTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 해당 테이블의 컬럼데이터 조회 테스트
	*/
	@Test
	public void selectTableColumndataTest() {
		/***Given***/
		MockHttpSession session = new MockHttpSession();
		Connection conn = DBUtilForWorksheet.getConnection("TeamSQL", "java", session);
		String tableName = "USERS";
		/***When***/
		List<SqlEditorTableVO> sqlEditorTableColumnDataList = sqlEditorTableDao.selectTableColumnData(tableName, conn);
		/***Then***/
		for (SqlEditorTableVO sqlEditorTableVO : sqlEditorTableColumnDataList) {
			logger.debug(">>[{}]<<", sqlEditorTableVO);
		}
	}
	
	/**
	* Method : selectTablePrimaryKeyTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 해당 테이블의 PK 키 조회 테스트
	*/
	@Test
	public void selectTablePrimaryKeyTest() {
		/***Given***/
		MockHttpSession session = new MockHttpSession();
		Connection conn = DBUtilForWorksheet.getConnection("TEST_asd24", "test", session);
		String tableName = "TESTT";
		/***When***/
		List<String> sqlEditorTablePrimaryKeyList = sqlEditorTableDao.selectTablePrimaryKey(tableName, conn);
		/***Then***/
		logger.debug("{}", sqlEditorTablePrimaryKeyList);
		/***When***/

		/***Then***/
	}
	
	/**
	* Method : primaryInjectionTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 컬럼리스트에서 PK인 컬럼을 찾아서 PK 값 true 만들기
	*/
	@Test
	public void primaryInjectionTest() {
		/***Given***/
		MockHttpSession session = new MockHttpSession();
		Connection conn = DBUtilForWorksheet.getConnection("TeamSQL", "java", session);
		String tableName = "USERS";
		/***When***/
		List<SqlEditorTableVO> sqlEditorTableColumnDataList = sqlEditorTableDao.selectTableColumnData(tableName, conn);
		List<String> sqlEditorTablePrimaryKeyList = sqlEditorTableDao.selectTablePrimaryKey(tableName, conn);
		logger.debug("columList =[{}]",sqlEditorTableColumnDataList);
		logger.debug("pkList =[{}]",sqlEditorTablePrimaryKeyList);
		/***Then***/
		for (SqlEditorTableVO sqlEditorTableColumn : sqlEditorTableColumnDataList) {
			for (String pk : sqlEditorTablePrimaryKeyList) {
				if(sqlEditorTableColumn.getColumn_name().equals(pk)) {
					sqlEditorTableColumn.setPk("true");
				}else {
					sqlEditorTableColumn.setPk("false");
				}
			}
			logger.debug("====[{}]====", sqlEditorTableColumn);
		}
	}

}
