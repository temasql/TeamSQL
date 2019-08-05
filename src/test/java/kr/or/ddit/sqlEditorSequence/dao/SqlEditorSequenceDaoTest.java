package kr.or.ddit.sqlEditorSequence.dao;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.sqlEdiotSequence.dao.ISqlEditorSequenceDao;
import kr.or.ddit.testenv.LogicTestEnv;
/**
 * 
* SqlEditorSequenceDaoTest.java
*
* @author 강호길
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* 강호길 최초 생성
*
* </pre>
 */
public class SqlEditorSequenceDaoTest extends LogicTestEnv {
	
	@Resource(name = "sqlEditorSequenceDao")
	private ISqlEditorSequenceDao sqlEditorSequenceDao;
	
	/**
	 * 
	* Method : createSequenceTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 시퀀스 생성 테스트
	 */
	@Test
	public void createSequenceTest() {
		/***Given***/
		String query = "CREATE SEQUENCE \"TEST6_GILHO2\".\"SEQUENCE11\"";
		/***When***/
		int createSeq = sqlEditorSequenceDao.createSequence(query);
		/***Then***/
		assertEquals(0, createSeq);
	}

}
