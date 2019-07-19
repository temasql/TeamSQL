package kr.or.ddit.user.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

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
		int insertCount = userService.insertUser(userVo);
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
	*/
	@Test
	public void updateUserTest() {
		/***Given***/
		UserVO userVo = userService.getUser("TEST_ID20");
		
		userVo.setUser_pw("TEST_PW21");
		userVo.setUser_name("TEST_ID21");
		userVo.setUser_email("diat1450@naver.com");
		userVo.setUser_path("TEST_PATH");
		/***When***/
		int updateCount = userService.updateUser(userVo);
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
		int deleteCount = userService.deleteUser(userVo.getUser_id());
		/***Then***/
		assertEquals(1, deleteCount);
	}


}
