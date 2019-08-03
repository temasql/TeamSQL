package kr.or.ddit.calendar.team_calendar.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.crew.model.CrewVO;
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
	* Method 설명 : 캘린더 첫 접속 시 테스트 메서드
	*/
	@Test
	public void calendarTest() throws Exception {
		/***Given***/
		CrewVO crewVO = new CrewVO();
		crewVO.setAccount_id_fk("테스트 계정");
		crewVO.setUser_id_fk("TEST_ID1");
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/cal")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("calendar/calendar.jsp?name="+crewVO.getUser_id_fk(), viewName);

	}
	
	/**
	 * Method : controllerGetTest
	 * 작성자 : OWNER
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 일정 조회 테스트 메서드
	 */
	@Test
	public void readCalTest() throws Exception {
		/***Given***/
		CrewVO crewVO = new CrewVO();
		crewVO.setAccount_id_fk("테스트 계정");
		crewVO.setUser_id_fk("TEST_ID1");
		
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/readCal").sessionAttr("CREW_INFO", crewVO)).andReturn();
		HttpSession session = mvcResult.getRequest().getSession();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("jsonView", viewName);
		
	}
	
	/**
	* Method : deleteCalTest
	* 작성자 : 손주형
	* 변경이력 :
	* Method 설명 :
	 * @throws Exception 
	*/
	@Test
	public void deleteCalTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/deleteCal").param("id", "1")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
//		mav.;
		
		/***Then***/
//		assertEquals("1", viewname);
	}
}