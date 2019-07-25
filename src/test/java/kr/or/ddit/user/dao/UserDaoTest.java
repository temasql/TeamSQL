package kr.or.ddit.user.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserDaoTest extends LogicTestEnv{

	@Resource(name = "userDao")
	private IUserDao userDao;
	
	/**
	* Method : signInTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	*/
	@Test
	public void signInTest() {
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
	* Method 설명 : 아이디에 해당하는 유저의 정보 조회 테스트
	*/
	@Test
	public void getUserTest() {
		/***When***/
		UserVO userVo = userDao.getUser("TEST_ID20");
		/***Then***/
		assertNotNull(userVo);
	}
	
	/**
	* Method : updateUserTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 아이디에 해당하는 유저의 정보 수정 테스트
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
	 * Method 설명 : 사용자의 아이디와 이메일을 입력하여 아이디 조회
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
	
	
	

}
