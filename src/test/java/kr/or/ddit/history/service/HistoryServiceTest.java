package kr.or.ddit.history.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.history.model.HistoryVO;
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
		List<HistoryVO> tempList = new ArrayList<HistoryVO>();
		List<HistoryVO> totalList = new ArrayList<HistoryVO>();
		
		/***When***/
		List<String> dbList = historyService.accountList(user_id);
		for(String account_id : dbList ) {
			tempList = historyService.changedList(account_id);
			for(HistoryVO hVo : tempList) {
				totalList.add(hVo);
			}
		}
		/***Then***/
		assertNotNull(totalList);
		assertEquals(2, totalList.size());
		assertEquals("TEAMSQL_TEST", totalList.get(0).getObject_owner());
	}

}
