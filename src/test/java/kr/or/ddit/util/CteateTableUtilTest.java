package kr.or.ddit.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CteateTableUtilTest {

	private static final Logger logger = LoggerFactory.getLogger(CteateTableUtilTest.class);
	@Test
	public void getQueryTest() {
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
		/***Then***/
		logger.debug("\n{}", new CreateTableUtil().getQuery(array));
	}

}
