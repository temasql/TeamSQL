package kr.or.ddit.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.sqlEditorTrigger.model.MyTriggerVO;

public class CteateFunctionUtilTest {

	private static final Logger logger = LoggerFactory.getLogger(CteateFunctionUtilTest.class);
	@Test
	public void getQueryTest() {
		/***Given***/
		String function_name = "FUNCTIONTEST1";
		String returnType = "VARCHAR2";
		String[] param_name = {"PARAM1", "PARAM2"};
		String[] param_mode = {"IN", "<지정되지 않음>"}; 
		String[] param_type = {"VARCHAR2", "VARCHAR2"};
		String[] param_default = {"1","2"};
		
		/***When***/
		String query = "CREATE OR REPLACE FUNCTION " + function_name;
		if(param_name != null) {
			query += " \n( \n";
			for (int i = 0; i < param_name.length; i++) {
				String temp = param_mode[i].equals("<지정되지 않음>")? "" : param_mode[i];
				query += "\t" + param_name[i] + " " + temp + " " + param_type[i];
				if(param_default != null) {
					if(param_default[i] != null) {
						query += " DEFAULT " + param_default[i];
					}
				}
				if(i == param_name.length-1) {
					query += " \n";
				}else {
					query += ", \n";
				}
			}
			query += ")";
		}
		query += " RETURN " + returnType + " AS \n"; 
		query += "BEGIN \n\tRETURN NULL; \nEND " + function_name + ";";
		/***Then***/
		logger.debug(query);
	}

}
