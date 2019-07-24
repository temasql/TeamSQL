package kr.or.ddit.sqlEditor.dao;

import java.util.List;
import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class SqlEditorDao implements ISqlEditorDao{

	private static final Logger logger = LoggerFactory.getLogger(SqlEditorDao.class);
	
	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

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
		sqlSession.selectOne("plan.runPlan", value);
//		try {
//			sqlSession.selectOne("plan.runPlan", value);
//		} catch (Exception e) {
//			String errorMsg = e.getMessage();
//			logger.debug("errorMsg : {}", errorMsg);
//			int start = errorMsg.indexOf("ORA");
//		}
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
		return sqlSession.selectList("plan.runPlanView");
	}

}
