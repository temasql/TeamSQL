package kr.or.ddit.crew.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

public class CrewControllerTest extends ControllerTestEnv{

	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	* Method : mainInviteCrewTest
	* 작성자 : 이중석
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 메인화면에서 초대장이 있을시 이동하는 URL 테스트
	*/
	@Test
	public void mainInviteCrewTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/crew/crewMain")
									.param("inviteCheck", "true")
									.param("account_id_fk", "testDB")
									.param("user_id_fk", "TEST_ID15")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("/crew/crewMain.tiles", viewName);
	}
	
	/**
	 * Method : crewManagerGetTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 구성원 페이지 메인 요청 화면
	 */
	@Test
	public void crewManagerGetTest() throws Exception {
		/***Given***/
		UserVO userVo = userService.getUser("TEST_ID20"); 
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/crew/crewManager")
									.sessionAttr("USER_INFO", userVo)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String selected = (String) mav.getModelMap().get("selected");
		/***Then***/
		assertEquals("/crew/crewMain.tiles", viewName);
		assertEquals("testDB", selected);
	}
	
	
	/**
	 * Method : crewManagerPostTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 구성원 메인 페이징 처리 응답 화면
	 * @throws Exception 
	 */
	@Test
	public void crewManagerPostTest() throws Exception {
		/***Given***/
		UserVO userVo = userService.getUser("TEST_ID20"); 
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/crew/crewManager")
										.param("searchfor", "")
										.param("page", "1")
										.param("account_id_fk", "testDB")
										.param("pageSize", "10")
										.sessionAttr("USER_INFO", userVo)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		List<CrewVO> crewList = (List<CrewVO>) mav.getModelMap().get("crewList");
		Map<String, Object> pageMap = (Map<String, Object>) mav.getModelMap().get("pageMap");
		int paginationSize = (int) mav.getModelMap().get("paginationSize");
		int page = (int) pageMap.get("page");
		int pageSize = (int) pageMap.get("pageSize");
		String search = (String) pageMap.get("search");
		/***Then***/
		assertEquals("crew/crewMainAjaxHtml", viewName);
		assertEquals(1, crewList.size());
		assertEquals(1, paginationSize);
		assertEquals(1, page);
		assertEquals(10, pageSize);
		assertEquals("", search);
	}
	
	/**
	* Method : inviteCrewTest
	* 작성자 : 이중석
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 메인화면에서 초대장이 있을시 이동하는 URL 테스트
	*/
	@Test
	public void inviteCrewTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/crew/inviteCrew")
									.param("user_id", "TEST_ID15")
									.param("ac_id", "testDB")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String user_id = (String) mav.getModelMap().get("user_id");
		/***Then***/
		assertEquals("jsonView", viewName);
		assertEquals(" ", user_id);
	}

}
