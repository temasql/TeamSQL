package kr.or.ddit.history.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
* 서비스 테스트의 틀을 위한 클래스입니다.
* </pre>
*/
public class HistoryServiceTest extends LogicTestEnv{

	
	@Resource(name = "historyService")
	private IHistoryService historyService;
	
	
	
	
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
		
		String user_id = "TEST_ID20";
		
		PageVo pageVo = new PageVo(1, 10);
		/***When***/
		// 해당 DB계정
		pageMap.put("user_id", user_id);
		// 페이지 번호
		pageMap.put("page", pageVo.getPage());
		// 한 페이지에 출력할 게시글 수
		pageMap.put("pageSize", pageVo.getPageSize());
		
		// 페이징리스트 담기
		Map<String, Object> resultMap = historyService.changedPagingList(pageMap);
		
		List<HistoryVO> changedPagingList = (List<HistoryVO>) resultMap.get("changedPagingList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		/***Then***/
		assertNotNull(changedPagingList);
		assertEquals(2, changedPagingList.size());
		assertNotNull(paginationSize);
		assertEquals(1, paginationSize);
		
	}
	
	/**
	 * 
	* Method : serviceChangedDetailPagingListTest
	* 작성자 : 강호길
	* 변경이력 :
	* Method 설명 : DB변경 상세 페이징 리스트 조회
	 */
	@Test
	public void serviceChangedDetailPagingListTest() {
		/***Given***/

		Map<String, Object> pageMap = new HashMap<String, Object>();
		
		// DB계정
		String object_owner = "TEAMSQL";
		
		PageVo pageVo = new PageVo(1, 10);
		/***When***/
		// 해당 DB계정명
		pageMap.put("object_owner", object_owner );
		// 해당 페이지 번호
		pageMap.put("page", pageVo.getPage());
		// 한 페이지에 출력할 게시글 수
		pageMap.put("pageSize", pageVo.getPageSize());
		
		// 페이징리스트에 담기
		Map<String, Object> resultMap = historyService.changedDetailPagingList(pageMap);
		
		// 페이지네이션
		List<HistoryVO> changedDetailPagingList = (List<HistoryVO>) resultMap.get("changedDetailPagingList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		/***Then***/
		assertNotNull(changedDetailPagingList);
		assertEquals(4, changedDetailPagingList.size());
		assertNotNull(paginationSize);
		assertEquals(1, paginationSize);
	}
	
	
	

}
