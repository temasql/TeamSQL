package kr.or.ddit.history.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.history.model.ChangedVO;
import kr.or.ddit.history.model.HistoryTempVO;
import kr.or.ddit.history.model.HistoryVO;
import kr.or.ddit.testenv.LogicTestEnv;

/**
* CommonsDaoTest.java
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
* OWNER 최초 생성
* Dao 테스트의 틀을 위한 클래스입니다.
* </pre>
*/
public class HistoryDaoTest extends LogicTestEnv{
	
	
	@Resource(name = "historyDao")
	private IHistoryDao historyDao;
	
	/**
	 * 
	* Method : daoListTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : DB변경 이력 리스트 조회 테스트
	 */
	@Test
	public void daoChangedListListTest() {
		/***Given***/
		String user_id = "TEST_ID20";
		List<HistoryVO> totalList = new ArrayList<HistoryVO>();
		
		/***When***/
		List<HistoryVO> dbList = historyDao.changedList(user_id);
			for(HistoryVO hVo : dbList) {
				totalList.add(hVo);
		}
		/***Then***/
		assertNotNull(totalList);
		assertEquals(2, totalList.size());
		assertEquals("TEAMSQL_TEST", totalList.get(0).getObject_owner());
		assertNotNull(totalList.get(0).getExec_dtm());
	}
	
	/**
	 * 
	* Method : daoChangedPagListTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : DB변경 이력 페이징리스트 조회 테스트
	 */
	@Test
	public void daoChangedPagingListTest() {
		/***Given***/
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("page", 1);
		pageMap.put("pageSize", 10);
		pageMap.put("user_id", "TEST_ID20");
		
		/***When***/
		List<HistoryVO> historyList = historyDao.changedPagingList(pageMap);
		/***Then***/
		assertNotNull(historyList);
		assertEquals(2, historyList.size());

	}
	
	/**
	 * 
	* Method : daoChangedDetailListTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : DB변경 상세 리스트 조회 테스트
	 */
	@Test
	public void daoChangedDetailListTest() {
		/***Given***/
		String user_id = "TEST_ID20";
		List<HistoryVO> totalList = new ArrayList<HistoryVO>();
		
		/***When***/
		List<HistoryVO> dbList = historyDao.changedDetailList(user_id);
			for(HistoryVO hVo : dbList) {
				totalList.add(hVo);
		}
		/***Then***/
		assertNotNull(totalList);
	}
	
	/**
	 * 
	* Method : daoAccountCntTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : DB변경 계정 전체수 조회
	 */
	@Test
	public void daoAccountCntTest() {
		/***Given***/
		String user_id_fk = "TEST_ID20";
		/***When***/
		int accountCnt = historyDao.accountCnt(user_id_fk);
		/***Then***/
		assertNotNull(accountCnt);
		assertEquals(1, accountCnt);
	}
	/**
	 * 
	* Method : daoHistoryCntTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : 변경 이력 전체 수 조회
	 */
	@Test
	public void daoHistoryCntTest() {
		/***Given***/
		String object_owner= "TEAMSQL";
		/***When***/
		int historyCnt = historyDao.historyCnt(object_owner);
		/***Then***/
		assertNotNull(historyCnt);
		assertEquals(4, historyCnt);
	}
	
	/**
	 * 
	* Method : daoChangedPagingListTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : DB변경 페이징 리스트 조회 테스트
	 */
	@Test
	public void daoChangedDetailPagingListTest() {
		/***Given***/
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("page", 1);
		pageMap.put("pageSize", 10);
		pageMap.put("object_owner", "TEAMSQL_TEST");
		
		/***When***/
		List<HistoryVO> historyList = historyDao.changedDetailPagingList(pageMap);
		/***Then***/
		assertEquals(7, historyList.size());
	}
	
	/**
	 * 
	* Method : daoChangedMainTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : DB계정, 마지막 변경일시, DB계정 생성자 조회 테스트
	 */
	@Test
	public void daoChangedMainTest() {
		/***Given***/
		String user_id_fk = "TEST_ID20";
		/***When***/
		List<ChangedVO> changedMainList = historyDao.changedMainList(user_id_fk); 
		/***Then***/
		assertNotNull(changedMainList);
		assertEquals(2, changedMainList.size());
	}
	
	@Test
	public void getAccountIdListTest() {
		/***Given***/
		String user_id = "TEST_ID20";
		/***When***/
		List<String> list = historyDao.getAccountIdList(user_id);
		/***Then***/
		assertNotNull(list);
		assertEquals(1, list.size());
	}
	
	@Test
	public void getLastDateAndNameTest() {
		/***Given***/
		String account_id = "BB_kkk123";
		/***When***/
		HistoryTempVO hVO = historyDao.getLastDateAndName(account_id);
		/***Then***/
		assertNotNull(hVO);
		//assertEquals(hVO.getName(), "TEST_NAME20");
	}
	

}
