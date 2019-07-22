package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;

public class UserControllerTest extends ControllerTestEnv{

	
	/**
	* Method : signInGetTest
	* 작성자 : 이중석
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 회원가입 버튼 클릭했을때 해당 url로 넘어가는지 테스트
	*/
	@Test
	public void signInGetTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/signIn")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("/user/signIn.tiles", viewName);
	}
	
	
	/**
	* Method : signInPostTest
	* 작성자 : 이중석
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 회원가입에 필요한 정보를 입력 후 가입 테스트
	*/
	@Test
	public void signInPostTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		/***When***/
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/signIn").file(file)
										.param("user_id", "js1450")
										.param("user_pw", "as12345@!as1")
										.param("user_name", "가가")
										.param("user_email", "js1450@gmail.com")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("/user/login.tiles", viewName);
	}
	
	

}
