package kr.or.ddit.worksheet.dao;

import java.util.List;
import java.util.Map;

public interface IWorksheetDao {
	
	/**
	 * 
	* Method : selectQuery
	* 작성자 : 김범휘
	* 변경이력 :
	* @param value
	* @return
	* Method 설명 : select에 관한 쿼리문 실행
	 */
	List<Map<String, String>> selectQuery(String value);
	
}
