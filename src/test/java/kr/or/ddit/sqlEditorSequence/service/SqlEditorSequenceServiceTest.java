package kr.or.ddit.sqlEditorSequence.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.sqlEdiotSequence.model.DetailSeqVO;
import kr.or.ddit.sqlEdiotSequence.model.SelectSeqVO;
import kr.or.ddit.sqlEdiotSequence.service.ISqlEditorSequenceService;
import kr.or.ddit.testenv.LogicTestEnv;
/**
 * 
* SqlEditorSequenceServiceTest.java
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
public class SqlEditorSequenceServiceTest extends LogicTestEnv{

	private static final Logger logger = LoggerFactory.getLogger(SqlEditorSequenceServiceTest.class);
	
	@Resource(name = "sqlEditorSequenceService")
	private ISqlEditorSequenceService sqlEditorSequenceService;
	
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
		String query = "CREATE SEQUENCE \"TEST6_GILHO2\".\"SEQUENCE13\"";
		/***When***/
		int createSeq = sqlEditorSequenceService.createSequence(query);
		/***Then***/
		assertEquals(0, createSeq);
	}
	
	/**
	 * 
	* Method : selectSequenceTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 시퀀스 조회 테스트
	 */
	@Test
	public void selectSequenceTest() {
		/***Given***/
		String sequence_owner = "TEST6_GILHO2";
		String sequence_name = "SEQUENCE3";
		SelectSeqVO seqVO = new SelectSeqVO(sequence_owner, sequence_name);
		/***When***/

		String seqQuery = sqlEditorSequenceService.selectSequence(seqVO); 
		
		
		/***Then***/
		assertNotNull(seqQuery);
		logger.debug("계정명 : {}", seqQuery);
//		logger.debug("시퀀스명  : {}", seqVO.getSequence_name());
//		logger.debug("최소값 : {}", seqVO.getMin_value());
//		logger.debug("최대값 : {}", seqVO.getMax_value());
//		logger.debug("증분 : {}", seqVO.getIncrement_by());
//		logger.debug("주기 : {}", seqVO.getCycle_flag());
//		logger.debug("정렬 : {}", seqVO.getOrder_flag());
//		logger.debug("캐시 : {}", seqVO.getCache_size());
//		logger.debug("마지막번호 : {}", seqVO.getLast_number());
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
		
		DetailSeqVO seqTable = sqlEditorSequenceService.selectSequenceTable(mapp);
		
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
		
		SelectSeqVO seqVO = sqlEditorSequenceService.beforeSequence(map);
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
		String sequence_name = "SEQUENCE15";
		String query = "\""+ sequence_owner +"\" .\""+ sequence_name + "\"";
		/***When***/
		
		
		int deleteCnt = sqlEditorSequenceService.deleteSequence(query);
		/***Then***/
		logger.debug("삭제카운트 : {}", deleteCnt);
	}
}
