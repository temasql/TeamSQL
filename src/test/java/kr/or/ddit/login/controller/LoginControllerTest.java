package kr.or.ddit.login.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

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
		MvcResult mvcResult = mockMvc.perform(get("/login")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("/login/login.tiles", viewName);

	}

}
