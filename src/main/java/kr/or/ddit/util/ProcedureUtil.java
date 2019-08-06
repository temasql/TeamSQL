package kr.or.ddit.util;

import java.util.ArrayList;
import java.util.List;

public class ProcedureUtil {
	
	public String getCreateProcedureSql(String procedure_name, String[] param_name, String[] param_mode, 
										String[] param_type, String[] param_default) {
		String query = "CREATE OR REPLACE PROCEDURE " + procedure_name;
		if(param_name != null) {
			query += " \n( \n";
			for (int i = 0; i < param_name.length; i++) {
				String temp = param_mode[i].equals("<지정되지 않음>")? "" : param_mode[i];
				query += "\t" + param_name[i] + " " + temp + " " + param_type[i];
				if(param_default != null) {
					if(param_default[i].trim().length() > 0) {
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
		query += " AS \n"; 
		query += "BEGIN \n\tNULL; \nEND " + procedure_name + ";";
		return query;
	}
}
