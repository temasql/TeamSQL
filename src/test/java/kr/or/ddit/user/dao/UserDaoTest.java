package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

/**
*
* @author 이중석
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* 이중석 최초 생성
*
* </pre>
*/
public class UserDaoTest extends LogicTestEnv{

	@Resource(name = "userDao")
	private IUserDao userDao;
	

	/**
	* Method : insertUserTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	*/
	@Test
	public void insertUserTest() {
		/***Given***/
		UserVO userVo = new UserVO();
		userVo.setUser_id("brown");
		userVo.setUser_pw("!a123456");
		userVo.setUser_name("브라운");
		userVo.setUser_email("diat1450@gmail.com");
		
		/***When***/
		int insertCount = userDao.insertUser(userVo);
		/***Then***/
		assertEquals(1, insertCount);
	}
	
	/**
	* Method : getUserTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 아이디에 해당하는 사용자의 정보 조회 테스트
	*/
	@Test
	public void getUserTest() {
		/***When***/
		UserVO userVo = userDao.getUser("TEST_ID20");
		/***Then***/
		assertNotNull(userVo);
	}
	
	/**
	* Method : userListTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 사용자 페이징 처리 테스트
	* 				
	*/
	@Test
	public void userListTest() {
		/***Given***/
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", "");
		pageMap.put("page", 1);
		pageMap.put("pageSize", 10);
		/***When***/
		List<UserVO> userList = userDao.userList(pageMap);
		
		/***Then***/
		assertEquals(10, userList.size());
	}
	
	/**
	* Method : adminListTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 관리자 페이징 처리 테스트
	*/
	@Test
	public void adminListTest() {
		/***Given***/
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", "");
		pageMap.put("page", 1);
		pageMap.put("pageSize", 10);
		/***When***/
		List<UserVO> userList = userDao.adminList(pageMap);
		
		/***Then***/
		assertEquals(1, userList.size());
	}
	
	/**
	* Method : updateUserTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 사용자 정보 수정 테스트
	*/
	@Test
	public void updateUserTest() {
		/***Given***/
		UserVO userVo = userDao.getUser("TEST_ID20");
		
		userVo.setUser_pw("TEST_PW21");
		userVo.setUser_name("TEST_ID21");
		userVo.setUser_email("diat1450@naver.com");
		userVo.setUser_path("TEST_PATH");
		/***When***/
		int updateCount = userDao.updateUser(userVo);
		/***Then***/
		assertEquals(1, updateCount);
	}
	
	/**
	* Method : deleteUser
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 사용자 삭제 테스트
	*/
	@Test
	public void deleteUser() {
		/***Given***/
		UserVO userVo = userDao.getUser("TEST_ID20");
		/***When***/
		int deleteCount = userDao.deleteUser(userVo);
		/***Then***/
		assertEquals(1, deleteCount);
	}

	
	/**
	* Method : findUserId
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 사용자의 이름과 이메일을 입력하여 아이디 조회
	*/
	@Test
	public void findUserId() {
		/***Given***/
		UserVO userVo = new UserVO();
		userVo.setUser_name("TEST_NAME20");
		userVo.setUser_email("TEST_MAIL20@TEST.COM");
		/***When***/
		String findUserId = userDao.findUserId(userVo);
		/***Then***/
		assertEquals("TEST_ID20", findUserId);
	}
	
	/**
	 * Method : findUserPw
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 사용자의 아이디와 이메일을 입력하여 비밀번호 조회
	 */
	@Test
	public void findUserPw() {
		/***Given***/
		UserVO userVo = new UserVO();
		userVo.setUser_id("TEST_ID20");
		userVo.setUser_email("TEST_MAIL20@TEST.COM");
		/***When***/
		String findUserPw = userDao.findUserPw(userVo);
		/***Then***/
		assertEquals("TEST_PW20", findUserPw);
	}
	
	/**
	* Method : temporaryUpdateUserPwTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : PW찾기 후 발송한 임시비밀번호를 암호화
	*/
	@Test
	public void temporaryUpdateUserPwTest() {
		/***Given***/
		UserVO userVo = userDao.getUser("TEST_ID20");
		
		userVo.setUser_pw("TEST_PW21");
		String encryptPw = KISA_SHA256.encrypt(userVo.getUser_pw());
		userVo.setUser_name("TEST_ID21");
		userVo.setUser_email("diat1450@naver.com");
		/***When***/
		int updateCount = userDao.temporaryUpdateUserPw(userVo);
		/***Then***/
		assertEquals(1, updateCount);
	}
	
	
	/**
	* Method : deleteUserMGTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 관리자가 체크박스로 사용자 탈퇴
	*/
	@Test
	public void deleteUserMGTest() {
		/***Given***/
		String user_id = "TEST_ID20";
		/***When***/
		int deleteUserMGCount = userDao.deleteUserMG(user_id);
		/***Then***/
		assertEquals(1, deleteUserMGCount);
	}
	
	/**
	* Method : userSearchCountTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 검색어에 해당하는 사용자 수 테스트
	*/
	@Test
	public void userSearchCountTest() {
		/***Given***/
		String search = "";
		/***When***/
		int userSearchCount = userDao.userSearchCount(search);
		/***Then***/
		assertEquals(18, userSearchCount);
	}
	
	/**
	 * Method : adminSearchCountTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 검색어에 해당하는 관리자 수 테스트
	 */
	@Test
	public void adminSearchCountTest() {
		/***Given***/
		String search = "";
		/***When***/
		int adminSearchCount = userDao.adminSearchCount(search);
		/***Then***/
		assertEquals(1, adminSearchCount);
	}
	
	
	/**
	* Method : insertAdminTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 관리자 등록 테스트
	*/
	@Test
	public void insertAdminTest() {
		/***Given***/
		UserVO adminVo = new UserVO();
		adminVo.setUser_id("testAdminId");
		adminVo.setUser_pw("testAdminPw1!");
		adminVo.setUser_name("어드민");
		adminVo.setUser_email("admin@admin.com");
		/***When***/
		int insertAdminCount = userDao.insertAdmin(adminVo);
		/***Then***/
		assertEquals(1, insertAdminCount);

	}
	

}
