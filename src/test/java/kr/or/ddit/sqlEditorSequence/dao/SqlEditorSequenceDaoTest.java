package kr.or.ddit.sqlEditorSequence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.sqlEdiotSequence.dao.ISqlEditorSequenceDao;
import kr.or.ddit.sqlEdiotSequence.model.SelectSeqVO;
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
	private static final Logger logger = LoggerFactory.getLogger(SqlEditorSequenceDaoTest.class);
	
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

	@Test
	public void selectSequenceTest() {
		/***Given***/
		String sequence_owner = "TEST6_GILHO2";
		String sequence_name = "SEQUENCE3";
		/***When***/

		SelectSeqVO seqVO = new SelectSeqVO(sequence_owner, sequence_name);
		seqVO = sqlEditorSequenceDao.selectSequence(seqVO);
		
		/***Then***/
		assertNotNull(seqVO);
		logger.debug("계정명 : {}", seqVO.getSequence_owner());
		logger.debug("시퀀스명  : {}", seqVO.getSequence_name());
		logger.debug("최소값 : {}", seqVO.getMin_value());
		logger.debug("최대값 : {}", seqVO.getMax_value());
		logger.debug("증분 : {}", seqVO.getIncrement_by());
		logger.debug("주기 : {}", seqVO.getCycle_flag());
		logger.debug("정렬 : {}", seqVO.getOrder_flag());
		logger.debug("캐시 : {}", seqVO.getCache_size());
		logger.debug("마지막번호 : {}", seqVO.getLast_number());
	}
}
