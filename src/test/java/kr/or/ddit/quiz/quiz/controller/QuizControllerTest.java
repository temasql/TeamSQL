package kr.or.ddit.quiz.quiz.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;

/**
* CommonsControllerTest.java
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
* 컨트롤러 테스트 틀을 만드는 클래스 입니다.
*
* </pre>
*/
public class QuizControllerTest extends ControllerTestEnv{

	/**
	* Method : controllerGetTest
	* 작성자 : OWNER
	* 변경이력 :
	* @throws Exception
	* Method 설명 : Get테스트 용
	*/
	@Test
	public void controllerGetTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("", viewName);

	}
	
	/**
	 * Method : controllerGetTest
	 * 작성자 : OWNER
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : Post테스트 용
	 */
	@Test
	public void controllerPostTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("", viewName);
		
	}

	/**
	* Method : insertEssayTest
	* 작성자 : 손주형
	* 변경이력 :
	* Method 설명 : 
	*/
	@Test
	public void insertEssayTest() {
		/***Given***/
		String test = "   select    *    from users;     ";
		
		

		/***When***/
		String trimTest = test.trim();
		
		trimTest = trimTest.replaceAll(" ", "");
		
		String[] arr = trimTest.split(" ");

		/***Then***/
		assertEquals("select", arr[0]);
		assertEquals("*", arr[1]);
		assertEquals("from", arr[2]);
		assertEquals("users;", arr[3]);
	}
	
	@Test
	public void StringTest() {
		String test1= "=";
		test1 += "&nbsp;";
		
		
		assertEquals("=&nbsp;", test1);
		
	}
}
