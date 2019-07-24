package kr.or.ddit.sqlEditor.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.sqlEditor.dao.ISqlEditorDao;

@Service
public class SqlEditorService implements ISqlEditorService{

	@Resource(name = "sqlEditorDao")
	private ISqlEditorDao sqlEditorDao;

	/**
	 * 
	* Method : runPlan
	* 작성자 : 김범휘
	* 변경이력 :
	* @param value
	* Method 설명 : 실행계획 등록
	 */
	@Override
	public void runPlan(String value) {
		sqlEditorDao.runPlan(value);
	}

	/**
	 * 
	* Method : runPlanView
	* 작성자 : 김범휘
	* 변경이력 :
	* @return
	* Method 설명 : 실행계획 화면에 출력
	 */
	@Override
	public List<String> runPlanView() {
		return sqlEditorDao.runPlanView();
	}

}
