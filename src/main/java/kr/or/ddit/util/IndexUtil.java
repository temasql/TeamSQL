package kr.or.ddit.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndexUtil {
	private static final Logger logger = LoggerFactory.getLogger(IndexUtil.class);
	
	public String createIndex(String param_name,String param_table, String param_indexType,
			String[] param_column, String[] param_order) {
		String query = "CREATE ";
		String index_type = "";
		String col = "";
		for(String column : param_column) {
			logger.debug("column : {}", column);
		}
		for(String order : param_order) {
			logger.debug("order : {}", order);
		}
		if (param_indexType.equals("UNIQUE")) {
			logger.debug("인덱스 체크 확인");
			index_type = param_indexType;
		} else if (param_indexType.equals("BITMAP")) {
			index_type = param_indexType;
		} else {
			index_type = " ";
		}
		logger.debug("인덱스 타입 : {}", index_type);
		
		
		query += index_type + " " +"INDEX " + param_name + " ON " + param_table + "(";
		logger.debug("여기빠끄 : {}",query);
		for (int i = 0; i < param_column.length; i++) {
			if (param_column.length > 0) {
				String order = param_order[i].equals("지정되지 않음") ? " " : param_order[i];
				col += param_column[i] + " " +order + ",";
			}
		}
		
		if(col.lastIndexOf(",") > 0 ) {
			query += col.substring(0, col.lastIndexOf(",")) + ")";
		}else {
			query += ")";
		}
		
		logger.debug("쿼리문 : {}",query);
		return query;
		
		
	}
	
	public String updateIndex(String update_name) {
		String query = "DROP INDEX " + update_name;
		
		
		logger.debug("편집쿼리문 : {}",query);
		return query;
		
		
	}
}
