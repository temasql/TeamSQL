package kr.or.ddit.calendar.team_calendar.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.calendar.team_calendar.dao.ITeamCalendarDao;
import kr.or.ddit.calendar.team_calendar.model.TeamCalendarVO;
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
public class TeamCalendarControllerTest extends ControllerTestEnv{
	private static final Logger logger = LoggerFactory.getLogger(TeamCalendarControllerTest.class);

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
	
}
