package kr.or.ddit.sqlEditorIndex.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.sqlEditorIndex.model.IndexColVO;
import kr.or.ddit.sqlEditorIndex.model.IndexDetailVO;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.util.IndexUtil;

public class SqlEditorIndexDaoTest extends LogicTestEnv {

	private static final Logger logger = LoggerFactory.getLogger(SqlEditorIndexDaoTest.class);
	
	@Resource(name = "sqlEditorIndexDao")
	private ISqlEditorIndexDao sqlEditorIndexDao;
	
	/**
	 * 
	* Method : indexQueryDaoTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 쿼리 조회 테스트
	 */
	@Test
	public void indexQueryDaoTest() {
		/***Given***/
		String index_owner = "TEST6_GILHO2";
		String index_name = "INDEX1";
		Map<String, String> map = new HashMap<String, String>();
		map.put("index_owner", index_owner);
		map.put("index_name", index_name);
		/***When***/
		String indexQuery = sqlEditorIndexDao.indexQuery(map);
		/***Then***/
		assertNotNull(indexQuery);
		logger.debug("쿼리빠끄 : {}", indexQuery);
		
	}
	
	/**
	 * 
	* Method : indexColDaoTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 열 조회 테스트
	 */
	@Test
	public void indexColDaoTest() {
		/***Given***/
		String index_name = "INDEX1";
		String index_owner = "TEST6_GILHO2";
		/***When***/
		Map<String, String> map = new HashMap<String, String>();
		map.put("index_name", index_name);
		map.put("index_owner", index_owner);
		
		List<IndexColVO> idxVO = sqlEditorIndexDao.indexCol(map);
		
		/***Then***/
		assertNotNull(idxVO);
		logger.debug("포지션 : {}", idxVO.get(0).getColumn_position());
	}
	
	/**
	 * 
	* Method : indexDetailDaoTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 세부 정보 조회
	 */
	@Test
	public void indexDetailDaoTest() {
		/***Given***/
		String index_name = "INDEX1";
		String table_owner = "TEST6_GILHO2";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("index_name", index_name);
		map.put("table_owner", table_owner);
		
		logger.debug("인덱스번호 : {}", map.get("index_name"));
		logger.debug("테이블 계정 : {}", map.get("table_owner"));
		
		/***When***/
		IndexDetailVO idxVO = sqlEditorIndexDao.indexDetail(map);
		
		/***Then***/
		assertNotNull(idxVO);
	}
	
	/**
	 * 
	* Method : indexDeleteDaoTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 인덱스 삭제 테스트
	 */
	@Test
	public void indexDeleteDaoTest() {
		/***Given***/
		String table_owner = "TEST6_GILHO2";
		String index_name = "TABLE1_PK";
		
		String query = "\""+table_owner + "\" .\"" + index_name + "\"";
		/***When***/
		
		int deleteCnt = -1;
		deleteCnt = sqlEditorIndexDao.indexDelete(query);
		/***Then***/
		assertEquals(0, deleteCnt);
		logger.debug("삭제카운트 : {}", deleteCnt);
	}
	
	/**
	 * 
	* Method : indexTableDaoTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 인덱스 테이블 조회 테스트
	 */
	@Test
	public void indexTableDaoTest() {
		/***Given***/
		String index_owner = "TEAMSQL";
		/***When***/
		List<String> indexTable = sqlEditorIndexDao.indexTable(index_owner);
		/***Then***/
		assertNotNull(indexTable);
		assertEquals(27, indexTable.size());
		logger.debug("테이블개수 : {}", indexTable.size());
		logger.debug("인덱스테이블빠끄 : {}", indexTable.get(0));
	}
	
	/**
	 * 
	* Method : tableColumDaoTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 테이블 컬럼 조회 테스트
	 */
	@Test
	public void tableColumDaoTest() {
		/***Given***/
		String table_name = "ACCOUNT";
		String owner = "TEAMSQL";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("table_name", table_name);
		map.put("owner", owner);
		/***When***/
		List<String> tableColumn = sqlEditorIndexDao.tableColumn(map);
		/***Then***/
		assertNotNull(tableColumn);
		assertEquals(3, tableColumn.size());
	}
	
	/**
	 * 
	* Method : beforeIndexOwnerDaoTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 편집 전 데이터(계정명, 테이블명, 인덱스명, 정렬)
	 */
	@Test
	public void beforeIndexOwnerDaoTest() {
		/***Given***/
		String table_owner = "TEST6_GILHO2";
		String index_name = "INDEX3";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("table_owner", table_owner);
		map.put("index_name", index_name);
		
		/***When***/
		List<IndexColVO> beforeIndexOwner = sqlEditorIndexDao.beforeIndexOwner(map);

		/***Then***/
		assertNotNull(beforeIndexOwner);
		assertEquals(3, beforeIndexOwner.size());
	}
	
	/**
	 * 
	* Method : beforeIndexTypeDaoTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 편집 전 데이터("인덱스 유형")
	 */
	@Test
	public void beforeIndexTypeDaoTest() {

		/***Given***/
		String table_owner = "TEST6_GILHO2";
		String index_name = "INDEX3";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("table_owner", table_owner);
		map.put("index_name", index_name);
		
		/***When***/
		String beforeIndexType = sqlEditorIndexDao.beforeIndexType(map);

		/***Then***/
		assertNotNull(beforeIndexType);
		assertEquals("NONUNIQUE", beforeIndexType);

	}
	
	
}

