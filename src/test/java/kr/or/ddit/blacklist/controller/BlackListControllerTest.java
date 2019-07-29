package kr.or.ddit.blacklist.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.blacklist.model.BlackListVO;
import kr.or.ddit.blacklist.service.IBlackListService;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

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
public class BlackListControllerTest extends ControllerTestEnv{

	@Resource(name = "userService")
	private IUserService userService;
	
	@Resource(name = "blackListService")
	private IBlackListService blackListService;
	
	/**
	* Method : insertBlackListTest
	* 작성자 : 이중석
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 블랙리스트 등록 테스트
	*/
	@Test
	public void insertBlackListTest() throws Exception {
		/***Given***/
		UserVO userVo = userService.getUser("admin");
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/blackList/insertBlackList")
										.param("user_id_fk", "TEST_ID17")
										.param("reason", "테스트 사유")
										.sessionAttr("USER_INFO", userVo)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("jsonView", viewName);
	}
	
	/**
	* Method : deleteBlackListMGTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 블랙리스트 해제 테스트
	 * @throws Exception 
	*/
	@Test
	public void deleteBlackListMGTest() throws Exception {
		/***Given***/
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", "");
		pageMap.put("page", 1);
		pageMap.put("pageSize", 10);
		
		Map<String, Object> resultMap = blackListService.blackList(pageMap);
		List<BlackListVO> blackList = (List<BlackListVO>) resultMap.get("blackList");
		int black_id = blackList.get(0).getBlack_id();
		String black_id_str = String.valueOf(black_id);
		UserVO userVo = userService.getUser("admin");
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/blackList/deleteBlackList")
										.param("deleteCheck", black_id_str)
										.sessionAttr("USER_INFO", userVo)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("/admin/blackListMG/blackListMGMain.tiles", viewName);
	}

	

	/**
	* Method : blackListManagerGetTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 블랙리스트 메인 요청 화면 테스트
	 * @throws Exception 
	*/
	@Test
	public void blackListManagerGetTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/blackList/blackListManager")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("/admin/blackListMG/blackListMGMain.tiles", viewName);
	}
	
	/**
	 * Method : blackListManagerPostTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 블랙리스트 메인 페이징 처리 응답 화면
	 * @throws Exception 
	 */
	@Test
	public void blackListManagerPostTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/blackList/blackListManager")
										.param("searchfor", "")
										.param("page", "1")
										.param("pageSize", "10")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<BlackListVO> blackList = (List<BlackListVO>) mav.getModelMap().get("blackList");
		Map<String, Object> pageMap = (Map<String, Object>) mav.getModelMap().get("pageMap");
		int paginationSize = (int) mav.getModelMap().get("paginationSize");
		int page = (int) pageMap.get("page");
		int pageSize = (int) pageMap.get("pageSize");
		String search = (String) pageMap.get("search");
		/***Then***/
		assertEquals("admin/blackListMG/blackListMGAjaxHtml", viewName);
		assertEquals(2, blackList.size());
		assertEquals(1, paginationSize);
		assertEquals(1, page);
		assertEquals(10, pageSize);
		assertEquals("", search);
	}
	
}
