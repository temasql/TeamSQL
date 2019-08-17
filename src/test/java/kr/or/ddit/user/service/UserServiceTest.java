package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserServiceTest extends LogicTestEnv{

	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	* Method : insertUserTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	 * @throws IOException 
	 * @throws FileNotFoundException 
	*/
	@Test
	public void insertUserTest() throws FileNotFoundException, IOException {
		/***Given***/
		UserVO userVo = new UserVO();
		userVo.setUser_id("brown");
		userVo.setUser_pw("!a123456");
		userVo.setUser_name("브라운");
		userVo.setUser_email("diat1450@gmail.com");
		
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile profile = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		int insertCount = userService.insertUser(userVo, profile);
		/***Then***/
		assertEquals(1, insertCount);
	}
	
	/**
	* Method : getUserTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 아이디에 해당하는 유저의 정보 조회 테스트
	*/
	@Test
	public void getUserTest() {
		/***When***/
		UserVO userVo = userService.getUser("TEST_ID20");
		/***Then***/
		assertNotNull(userVo);
	}
	
	/**
	* Method : userListTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 회원 관리에서 일반 회원과 탈퇴하지 않고 블랙리스트가 아닌 회원만 조회 
	*/
	@Test
	public void userListTest() {
		/***Given***/
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", "");
		pageMap.put("page", 1);
		pageMap.put("pageSize", 10);
		
		/***When***/
		Map<String, Object> resultMap = userService.userList(pageMap);
		List<UserVO> userList = (List<UserVO>) resultMap.get("userList");
		int paginationSize = (int) resultMap.get("paginationSize");
		/***Then***/
		assertEquals(10, userList.size());
		assertEquals(2, paginationSize);
	}
	
	/**
	* Method : adminListTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 관리자 페이징 리스트 테스트
	*/
	@Test
	public void adminListTest() {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", "");
		pageMap.put("page", 1);
		pageMap.put("pageSize", 10);
		
		/***When***/
		Map<String, Object> resultMap = userService.adminList(pageMap);
		List<UserVO> adminList = (List<UserVO>) resultMap.get("adminList");
		int paginationSize = (int) resultMap.get("paginationSize");
		/***Then***/
		assertEquals(1, adminList.size());
		assertEquals(1, paginationSize);
	}
	
	/**
	* Method : updateUserTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 아이디에 해당하는 유저의 정보 수정 테스트
	 * @throws IOException 
	 * @throws FileNotFoundException 
	*/
	@Test
	public void updateUserTest() throws FileNotFoundException, IOException {
		/***Given***/
		UserVO userVo = userService.getUser("TEST_ID20");
		
		userVo.setUser_pw("TEST_PW21");
		userVo.setUser_name("TEST_ID21");
		userVo.setUser_email("diat1450@naver.com");
		userVo.setUser_path("TEST_PATH");
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile profile = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		/***When***/
		int updateCount = userService.updateUser(userVo, profile);
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
		UserVO userVo = userService.getUser("TEST_ID20");
		/***When***/
		int deleteCount = userService.deleteUser(userVo);
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
		List<String> findUserId = userService.findUserId(userVo);
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
		String findUserPw = userService.findUserPw(userVo);
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
		UserVO userVo = userService.getUser("TEST_ID20");
		
		userVo.setUser_pw("TEST_PW21");
		String encryptPw = KISA_SHA256.encrypt(userVo.getUser_pw());
		userVo.setUser_name("TEST_ID21");
		userVo.setUser_email("diat1450@naver.com");
		/***When***/
		int updateCount = userService.temporaryUpdateUserPw(userVo);
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
		int deleteUserMGCount = userService.deleteUserMG(user_id);
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
		int userSearchCount = userService.userSearchCount(search);
		/***Then***/
		assertEquals(18, userSearchCount);
	}
	


	
}
