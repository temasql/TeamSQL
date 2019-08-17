package kr.or.ddit.blacklist.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
* Dao 테스트의 틀을 위한 클래스입니다.
* </pre>
*/
public class BlackListDaoTest extends LogicTestEnv{

	@Resource(name = "blackListDao")
	private IBlackListDao blackListDao;
	
	private static final Logger logger = LoggerFactory.getLogger(BlackListDaoTest.class);

	
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
		int insertBlackListCount = blackListDao.insertBlackList(blackListVo);
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
		List<BlackListVO> blackList = blackListDao.blackList(pageMap);
		
		/***Then***/
		assertEquals(2, blackList.size());
	}
	
	/**
	 * Method : blackListSearchCountTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 블랙리스트 페이징 처리 테스트
	 */
	@Test
	public void blackListSearchCountTest() {
		/***Given***/
		String search = "";
		/***When***/
		int blackListSearchCount = blackListDao.blackListSearchCount(search);
		/***Then***/
		assertEquals(2, blackListSearchCount);
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
		
		List<BlackListVO> blackList = blackListDao.blackList(pageMap);
		
		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("unlockUser", "admin");
		userMap.put("black_id", blackList.get(0).getBlack_id());
		
		/***When***/
		int deleteBlackListCount = blackListDao.deleteBlackListMG(userMap);
		/***Then***/
		assertEquals(1, deleteBlackListCount);
	}

	@Test
	public void loginBlackListUserCheck() {
		/***Given***/
		String user_id_fk = "js1450";
		/***When***/
		List<BlackListVO> blackList = blackListDao.loginBlackListUserCheck(user_id_fk);
		logger.debug("[{}]",blackList);
		/***Then***/
		assertNotNull(blackList);
		assertEquals(1, blackList.size());

	}
	
}
