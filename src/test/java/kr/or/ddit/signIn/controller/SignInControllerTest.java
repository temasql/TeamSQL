package kr.or.ddit.signIn.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;

import kr.or.ddit.testenv.ControllerTestEnv;

public class SignInControllerTest extends ControllerTestEnv{

	/**
	* Method : SignInGetTest
	* 작성자 : OWNER
	* 변경이력 :
	* @throws Exception
	* Method 설명 :
	*/
	@Test
	public void signInGetTest() throws Exception {
		/***Given***/
		/***When***/
		mockMvc.perform(get("/login")).andExpect(view().name("tiles.login"));
		/***Then***/
		assertEquals("", "");

	}

}
