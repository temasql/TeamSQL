package kr.or.ddit.login.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import kr.or.ddit.testenv.ControllerTestEnv;

public class LoginControllerTest extends ControllerTestEnv{

	/**
	* Method : LoginGetTest
	* 작성자 : OWNER
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 로그인 화면 요청 테스트
	*/
	@Test
	public void loginGetTest() throws Exception {
		/***Given***/
		/***When***/
//		mockMvc.perform(get("/signIn")).andExpect(view().name("tiles.login"));
		/***Then***/
//		assertEquals("", "");

	}

}
