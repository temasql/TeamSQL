package kr.or.ddit.sqlEditorSequence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.sqlEdiotSequence.dao.ISqlEditorSequenceDao;
import kr.or.ddit.sqlEdiotSequence.model.DetailSeqVO;
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

	/**
	 * 
	* Method : selectSequenceTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 시퀀스 쿼리 조회 테스트
	 */
	@Test
	public void selectSequenceTest() {
		/***Given***/
		String sequence_owner = "TEST6_GILHO2";
		String sequence_name = "SEQUENCE3";
		SelectSeqVO seqVO = new SelectSeqVO(sequence_owner, sequence_name);
		/***When***/

		String seqQuery = sqlEditorSequenceDao.selectSequence(seqVO);
		
		/***Then***/
		assertNotNull(seqQuery);
		logger.debug("SQL 쿼리 : {}", seqQuery);
	}
	
	/**
	 * 
	* Method : selectSequenceTableTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 시퀀스 세부 정보 조회 테스트
	 */
	@Test
	public void selectSequenceTableTest() {
		/***Given***/
		String sequence_name = "SEQUENCE3";
		String sequence_owner= "TEST6_GILHO2";
		Map<String, String> mapp = new HashMap<String, String>();
		mapp.put("sequence_name", sequence_name);
		mapp.put("sequence_owner", sequence_owner);
		
		/***When***/
		
		DetailSeqVO seqTable = sqlEditorSequenceDao.selectSequenceTable(mapp);
		
		/***Then***/
		assertNotNull(seqTable);
		logger.debug("세부 정보 조회(캐시크기) : {}", seqTable.getCache_size());
	}
	
	/**
	 * 
	* Method : beforeSequenceTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 시퀀스 편집 전 데이터 테스트
	 */
	@Test
	public void beforeSequenceTest() {
	
		/***Given***/
		String sequence_owner = "TEST6_GILHO2";
		String sequence_name = "SEQUENCE15";
		/***When***/
		Map<String, String> map = new HashMap<String, String>();
		map.put("sequence_owner",sequence_owner);
		map.put("sequence_name",sequence_name);
		
		SelectSeqVO seqVO = sqlEditorSequenceDao.beforeSequence(map);
		/***Then***/
		assertNotNull(seqVO);
		assertEquals("1", seqVO.getMin_value());
		logger.debug("편집 전 주기 : {} ",seqVO.getOrder_flag());
	}
	
	/**
	 * 
	* Method : deleteSequenceTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 시퀀스 삭제 테스트
	 */
	@Test
	public void deleteSequenceTest() {
		/***Given***/
		String sequence_owner = "TEST6_GILHO2";
		String sequence_name = "SEQUENCE11";
		String query = "\""+ sequence_owner +"\" .\""+ sequence_name + "\"";
		/***When***/
		
		
		int deleteCnt = sqlEditorSequenceDao.deleteSequence(query);
		/***Then***/
		logger.debug("삭제카운트 : {}", deleteCnt);
	}
	
	
}
