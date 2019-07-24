package kr.or.ddit.sqlEditor.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.testenv.LogicTestEnv;

/**
* CommonsDaoTest.java
*
* @author OWNER
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* OWNER 최초 생성
* 서비스 테스트의 틀을 위한 클래스입니다.
* </pre>
*/
public class SqlEditorServiceTest extends LogicTestEnv{

	private static final Logger logger = LoggerFactory.getLogger(SqlEditorServiceTest.class);
	
	@Resource(name = "sqlEditorService")
	private ISqlEditorService sqlEditorService;
	
	/**
	 * 
	* Method : runPlanViewTest
	* 작성자 : 김범휘
	* 변경이력 :
	* Method 설명 : 실행계획 등록 후 화면에 출력 테스트
	 */
	@Test
	public void runPlanViewTest() {
		/***Given***/
		String value1 = "EXPLAIN PLAN FOR SELECT * FROM users";
		/***When***/
		sqlEditorService.runPlan(value1);
		List<String> list = sqlEditorService.runPlanView();
		/***Then***/
		logger.debug("map : {}", list);
		assertNotNull(list);
		assertEquals(list.get(0), "Plan hash value: 3461732445");
	}

}
