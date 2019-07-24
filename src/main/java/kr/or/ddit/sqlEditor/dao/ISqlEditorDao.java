package kr.or.ddit.sqlEditor.dao;

import java.util.List;

public interface ISqlEditorDao {

	/**
	 * 
	* Method : runPlan
	* 작성자 : 김범휘
	* 변경이력 :
	* @param value
	* Method 설명 : 실행계획 등록
	 */
	void runPlan(String value);
	
	/**
	 * 
	* Method : runPlanView
	* 작성자 : PC06
	* 변경이력 :
	* @return
	* Method 설명 : 실행계획 화면에 출력
	 */
	List<String> runPlanView();
	
}
