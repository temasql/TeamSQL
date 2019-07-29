package kr.or.ddit.history.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.history.model.ChangedVO;
import kr.or.ddit.history.model.HistoryVO;
import kr.or.ddit.page.model.PageVo;
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
public class HistoryServiceTest extends LogicTestEnv{

	@Resource(name = "historyService")
	private IHistoryService historyService;
	
	/**
	 * 
	* Method : serviceTest
	* 작성자 : PC20
	* 변경이력 :
	* Method 설명 : DB변경 이력 리스트 조회 테스트
	 */
	@Test
	public void serviceChangedListTest() {
		/***Given***/
		String user_id = "TEST_ID20";
		List<HistoryVO> totalList = new ArrayList<HistoryVO>();
		
		/***When***/
		List<HistoryVO> dbList = historyService.changedList(user_id);
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
	* Method : serviceChangedPagingListTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : DB계정명
	 */
	@Test
	public void serviceChangedPagingListTest() {
		/***Given***/
		Map<String, Object> pageMap = new HashMap<String, Object>();

		String object_owner = "TEAMSQL_TEST";
		PageVo pageVo = new PageVo(1, 10);
		
		pageMap.put("user_id", object_owner);
		pageMap.put("pageVo", pageVo);
		
		
		
		/***When***/
		List<HistoryVO> totalList = (List<HistoryVO>) historyService.changedPagingList(pageMap);
		
		/***Then***/
		
	}
	
	/**
	 * 
	* Method : daoChangedDetailListTest
	* 작성자 : PC20
	* 변경이력 :
	* Method 설명 : DB변경 상세 리스트 조회 테스트
	 */
	@Test
	public void daoChangedDetailListTest() {
		/***Given***/
		String user_id = "TEST_ID20";
		List<HistoryVO> totalList = new ArrayList<HistoryVO>();
		
		/***When***/
		List<HistoryVO> dbList = historyService.changedDetailList(user_id);
			for(HistoryVO hVo : dbList) {
				totalList.add(hVo);
		}
		/***Then***/
		assertNotNull(totalList);
		assertEquals(11, totalList.size());
	}
	
	/**
	 * 
	* Method : serviceChangedMainTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : DB계정, 마지막 변경일시, DB계정 생성자 조회 테스트
	 */
	@Test
	public void serviceChangedMainTest() {
		/***Given***/
		String user_id_fk = "TEST_ID20";
		/***When***/
		List<ChangedVO> changedMainList = historyService.changedMainList(user_id_fk); 
		/***Then***/
		assertNotNull(changedMainList);
		assertEquals(2, changedMainList.size());
	}
	

}
