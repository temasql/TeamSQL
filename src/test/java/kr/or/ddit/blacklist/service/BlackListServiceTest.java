package kr.or.ddit.blacklist.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.blacklist.model.BlackListVO;
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
public class BlackListServiceTest extends LogicTestEnv{

	@Resource(name = "blackListService")
	private IBlackListService blackListService;
	
	/**
	* Method : insertBlackListTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 블랙리스트 등록 테스트
	*/
	@Test
	public void insertBlackListTest() {
		/***Given***/
		BlackListVO blackListVo = new BlackListVO();
		blackListVo.setUser_id_fk("TEST_ID20");
		blackListVo.setReason("테스트사유");
		blackListVo.setReg_user_id_fk("admin");
		
		/***When***/
		int insertBlackListCount = blackListService.insertBlackList(blackListVo);
		/***Then***/
		assertEquals(1, insertBlackListCount);
	}
	
	/**
	 * Method : blackListTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 블랙리스트 페이징 처리 테스트
	 */
	@Test
	public void blackListTest() {
		/***Given***/
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", "");
		pageMap.put("page", 1);
		pageMap.put("pageSize", 10);
		/***When***/
		Map<String, Object> resultMap = blackListService.blackList(pageMap);
		List<BlackListVO> blackList = (List<BlackListVO>) resultMap.get("blackList");
		int paginationSize = (int) resultMap.get("paginationSize");
		/***Then***/
		assertEquals(2, blackList.size());
		assertEquals(1, paginationSize);
	}
	
	/**
	 * Method : deleteBlackListMGTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 블랙리스트 해제 테스트
	 */
	@Test
	public void deleteBlackListMGTest() {
		/***Given***/
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", "");
		pageMap.put("page", 1);
		pageMap.put("pageSize", 10);
		
		Map<String, Object> resultMap = blackListService.blackList(pageMap);
		List<BlackListVO> blackList = (List<BlackListVO>) resultMap.get("blackList");
		
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("unlockUser", "admin");
		userMap.put("black_id", blackList.get(0).getBlack_id());
		
		/***When***/
		int deleteBlackListCount = blackListService.deleteBlackListMG(userMap);
		/***Then***/
		assertEquals(1, deleteBlackListCount);
	}
}
