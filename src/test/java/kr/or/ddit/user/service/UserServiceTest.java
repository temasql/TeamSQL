package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVO;

public class UserServiceTest extends LogicTestEnv{

	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	* Method : signInTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	 * @throws IOException 
	 * @throws FileNotFoundException 
	*/
	@Test
	public void signInTest() throws FileNotFoundException, IOException {
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
		String findUserId = userService.findUserId(userVo);
		/***Then***/
		assertEquals("TEST_ID20", findUserId);
	}

	/**
	 * Method : findUserPw
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 사용자의 아이디와 이메일을 입력하여 아이디 조회
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
	
}
