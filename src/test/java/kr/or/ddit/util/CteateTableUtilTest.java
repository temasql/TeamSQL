package kr.or.ddit.util;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CteateTableUtilTest {

	private static final Logger logger = LoggerFactory.getLogger(CteateTableUtilTest.class);
	@Test
	public void getQueryTest() {
		/***Given***/
		String[][] array = {{
			" : 'true' " 		 // pkCheck    
			," : 'user_id' "      // colName
			," : 'VARCHAR2' "  // dataType
			," : '' "       // Size
			," : '' "          // nullCheck
			," : '' "    	 	 // defaultValue
			," : '' "      // comment
			,"TESTTABLE " // tableName      
			,"TEST" // schema
		},{
			" : 'true' " 		 // pkCheck    
			," : 'user_id2' "      // colName
			," : 'VARCHAR2' "  // dataType
			," : '' "       // Size
			," : '' "          // nullCheck
			," : '' "    	 	 // defaultValue
			," : '' "      // comment
			,"TESTTABLE " // tableName      
			,"TEST" // schema
		}};
		/***When***/
		/***Then***/
//		logger.debug("\n{}", new CreateTableUtil().getUpdateQuery(array).get("query"));
		List<String> queryList = (List<String>) new CreateTableUtil().getUpdateQuery(array).get("query");
		String query = queryList.get(0);
		int endIndex =query.indexOf("ADD"); 
		query = query.substring(0, endIndex);
		logger.debug("h\n{}",query + "DROP CONSTRAINT PK_" + query.substring(query.indexOf(".") + 1, endIndex));
//		logger.debug("\n{}", new CreateTableUtil().getUpdateQuery(array).get("pkQuery"));
	}
	
	@Test
	public void getPkQueryTest() {
		/***Given***/
		String[][] array = {{
			" : 'true' " 		 // pkCheck    
			," : 'user_id' "      // colName
			," : 'VARCHAR2' "  // dataType
			," : '' "       // Size
			," : '' "          // nullCheck
			," : '' "    	 	 // defaultValue
			," : '' "      // comment
			,"TESTTABLE " // tableName      
			,"TEST" // schema
		},{
			" : 'true' " 		 // pkCheck    
			," : 'user_id2' "      // colName
			," : 'VARCHAR2' "  // dataType
			," : '' "       // Size
			," : '' "          // nullCheck
			," : '' "    	 	 // defaultValue
			," : '' "      // comment
			,"TESTTABLE " // tableName      
			,"TEST" // schema
		}};
		/***When***/
		/***Then***/
		logger.debug("\n{}", new CreateTableUtil().getUpdateQuery(array).get("pkQuery"));
	}

}
