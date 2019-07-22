package kr.or.ddit.template.user_template.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

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
* Dao 테스트의 틀을 위한 클래스입니다.
* </pre>
*/
public class UserTemplateDaoTest extends LogicTestEnv{

	@Resource(name = "userTemplateDao")
	private IUserTemplateDao userTemplateDao;
	
	/**
	* Method : insertCommonTemplateTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 
	*/
	@Test
	public void insertCommonTemplateTest() {
		/***Given***/
		/***When***/
		int commonInsertCount = userTemplateDao.insertCommonTemplate("TEST_ID20");
		/***Then***/
		assertEquals(10, commonInsertCount);
	}

}
