/**
 * 
 */
package kr.or.ddit.sqlEditorTable.service;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.sqlEdiotTable.dao.ISqlEditorTableDao;
import kr.or.ddit.sqlEdiotTable.service.ISqlEditorTableService;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.util.CreateTableUtil;

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
public class SqlEditorTableServiceTest extends LogicTestEnv{

	@Resource(name = "sqlEditorTableService")
	private ISqlEditorTableService sqlEditorTableService;
	
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
			"" 		 // pkCheck    
			,"user_id"      // colName
			,"VARCHAR2"  // dataType
			,""       // Size
			,""          // nullCheck
			,""    	 	 // defaultValue
			,""      // comment
			,"TESTTABLE" // tableName      
		},{
			"" 		 // pkCheck    
			,"user_name"      // colName
			,"VARCHAR2"  // dataType
			,""       // Size
			,"true"          // nullCheck
			,""    	 	 // defaultValue
			,"user_name입니다"      // comment
			,"TESTTABLE" // tableName      
		},{
			"" 		 // pkCheck    
			,"user_age"      // colName
			,"NUMBER"  // dataType
			,""       // Size
			,""          // nullCheck
			,""    	 	 // defaultValue
			,"나이"      // comment
			,"TESTTABLE" // tableName      
		},{
			"true" 		 // pkCheck    
			,"profile"      // colName
			,"CLOB"  // dataType
			,"10"       // Size
			,""          // nullCheck
			,""    	 	 // defaultValue
			,"프로필 사진"      // comment
			,"TESTTABLE" // tableName      
		}};
		/***When***/
		Map<String, Object> queryMap = new CreateTableUtil().getQuery(array); 
		
		String query = (String) queryMap.get("query"); 
		int createCount = sqlEditorTableService.createTable(array);
		String drop = "DROP TABLE TESTTABLE";
		sqlEditorTableDao.createTable(drop);
		/***Then***/
		assertEquals(0, createCount);
	}

}
