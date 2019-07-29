package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

public class UserControllerTest extends ControllerTestEnv{

	@Resource(name = "userService")
	private IUserService userService;
	
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
		assertEquals("/user/signIn", viewName);
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
		assertEquals("/login/login.tiles", viewName);
	}
	
	/**
	* Method : profileTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 사용자 프로필 사진 테스트
	 * @throws Exception 
	*/
	@Test
	public void profileTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/profile").param("user_id", "TEST_ID20")).andReturn();;
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("profileView", viewName);

	}
	
	/**
	* Method : idCheckSuccessTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 아이디 중복 체크 성공 테스트
	 * @throws Exception 
	*/
	@Test
	public void idCheckSuccessTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/idCheck").param("user_id", "TESTID21")).andReturn();

		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String msg = (String) mav.getModel().get("msg");
		/***Then***/
		assertEquals("/user/signIn", viewName);
		assertEquals("사용가능한 아이디입니다.", msg);
	}
	
	/**
	 * Method : idCheckFailTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 아이디 중복 체크 실패 테스트
	 * @throws Exception 
	 */
	@Test
	public void idCheckFailTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/idCheck").param("user_id", "admin")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String msg = (String) mav.getModel().get("msg");
		/***Then***/
		assertEquals("/user/signIn", viewName);
		assertEquals("이미 사용중인 아이디입니다.", msg);
	}
	
	/**
	 * Method : adminIdCheckSuccessTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 아이디 중복 체크 성공 테스트
	 * @throws Exception 
	 */
	@Test
	public void adminIdCheckSuccessTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/adminIdCheck").param("user_id", "TESTID21")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String msg = (String) mav.getModel().get("msg");
		/***Then***/
		assertEquals("/admin/adminMG/adminMGInsert.tiles", viewName);
		assertEquals("사용가능한 아이디입니다.", msg);
	}
	
	/**
	 * Method : adminIdCheckFailTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 아이디 중복 체크 실패 테스트
	 * @throws Exception 
	 */
	@Test
	public void adminIdCheckFailTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/adminIdCheck").param("user_id", "admin")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String msg = (String) mav.getModel().get("msg");
		/***Then***/
		assertEquals("/admin/adminMG/adminMGInsert.tiles", viewName);
		assertEquals("이미 사용중인 아이디입니다.", msg);
	}
	
	/**
	* Method : findUserIdTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 아이디 찾기 ajax 테스트
	 * @throws Exception 
	*/
	@Test
	public void findUserIdTest() throws Exception {
		/***Given***/
		String user_name = "TEST_NAME20";
		String user_email = "TEST_MAIL20@TEST.COM";
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/user/findUserId")
									.param("user_name", user_name)
									.param("user_email", user_email)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("jsonView", viewName);
	}
	
	/**
	* Method : logoutTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 로그아웃 테스트
	 * @throws Exception 
	*/
	@Test
	public void logoutTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/logout")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("/login/login.tiles", viewName);
	}
	
	/**
	* Method : myPageMainTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 마이페이지 메인 테스트
	 * @throws Exception 
	*/
	@Test
	public void myPageMainTest() throws Exception {
		/***Given***/
		UserVO userVo = userService.getUser("TEST_ID20");
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/mypage")
									.sessionAttr("USER_INFO", userVo)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO user = (UserVO) mav.getModelMap().get("userVo");
		
		/***Then***/
		assertEquals("/user/myPageMain.tiles", viewName);
		assertNotNull(user);
		
	}
	
	/**
	* Method : modifyUserGetTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 회원 정보 수정 요청 화면
	 * @throws Exception 
	*/
	@Test
	public void modifyUserGetTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/modifyUser").param("user_id", "TEST_ID20")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO userVo = (UserVO) mav.getModelMap().get("userVo");
		/***Then***/
		assertEquals("/user/myPageModify.tiles", viewName);
		assertNotNull(userVo);
	}
	
	/**
	 * Method : modifyUserPostTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 회원 정보 수정 응답 화면
	 * @throws Exception 
	 */
	@Test
	public void modifyUserPostTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		/***When***/
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/modifyUser").file(file)
										.param("user_id", "TEST_ID20")
										.param("user_pw", "as12345@!as1")
										.param("user_name", "가가")
										.param("user_email", "js1450@gmail.com")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO userVo = (UserVO) mav.getModelMap().get("userVo");
		/***Then***/
		assertEquals("/user/myPageMain.tiles", viewName);
		assertNotNull(userVo);
	}

	/**
	* Method : userManagerGetTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 관리자 회원 관리 메인 요청 화면 테스트
	 * @throws Exception 
	*/
	@Test
	public void userManagerGetTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userManager")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("/admin/userMG.tiles", viewName);
	}
	
	/**
	 * Method : userManagerPostTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 관리자 회원관리 메인 페이징 처리 응답 화면
	 * @throws Exception 
	 */
	@Test
	public void userManagerPostTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/user/userManager")
										.param("searchfor", "")
										.param("page", "1")
										.param("pageSize", "10")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVO> userList = (List<UserVO>) mav.getModelMap().get("userList");
		Map<String, Object> pageMap = (Map<String, Object>) mav.getModelMap().get("pageMap");
		int paginationSize = (int) mav.getModelMap().get("paginationSize");
		int page = (int) pageMap.get("page");
		int pageSize = (int) pageMap.get("pageSize");
		String search = (String) pageMap.get("search");
		/***Then***/
		assertEquals("admin/userMGAjaxHtml", viewName);
		assertEquals(10, userList.size());
		assertEquals(2, paginationSize);
		assertEquals(1, page);
		assertEquals(10, pageSize);
		assertEquals("", search);
	}
	
	/**
	* Method : userManagerGetTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 관리자 회원 삭제 테스트
	 * @throws Exception 
	*/
	@Test
	public void deleteUserMGTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/deleteUserMG")
										.param("deleteCheck", "TEST_ID20")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("/admin/userMG.tiles", viewName);
	}
	
	
	/**
	* Method : adminManagerGetTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 관리자 관리 메인 요청 화면 테스트
	 * @throws Exception 
	*/
	@Test
	public void adminManagerGetTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/adminManager")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("/admin/adminMG/adminMGMain.tiles", viewName);
	}

	/**
	 * Method : adminManagerPostTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * Method 설명 : 관리자 관리 메인 페이징 처리 응답화면
	 * @throws Exception 
	 */
	@Test
	public void adminManagerPostTest() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/user/adminManager")
										.param("searchfor", "")
										.param("page", "1")
										.param("pageSize", "10")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVO> adminList = (List<UserVO>) mav.getModelMap().get("adminList");
		Map<String, Object> pageMap = (Map<String, Object>) mav.getModelMap().get("pageMap");
		int paginationSize = (int) mav.getModelMap().get("paginationSize");
		int page = (int) pageMap.get("page");
		int pageSize = (int) pageMap.get("pageSize");
		String search = (String) pageMap.get("search");
		/***Then***/
		assertEquals("/admin/adminMG/adminMGAjaxHtml", viewName);
		assertEquals(1, adminList.size());
		assertEquals(1, paginationSize);
		assertEquals(1, page);
		assertEquals(10, pageSize);
		assertEquals("", search);
	}

	/**
	* Method : insertAdminGetTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 관리자 추가 요청화면 테스트
	 * @throws Exception 
	*/
	@Test
	public void insertAdminGetTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/insertAdmin")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("/admin/adminMG/adminMGInsert.tiles", viewName);
	}
	
	/**
	* Method : insertAdminPostTest
	* 작성자 : 이중석
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 관리자 추가 응답화면 테스트
	*/
	@Test
	public void insertAdminPostTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/user/insertAdmin")
										.param("user_id", "js1450")
										.param("user_pw", "as12345@!as1")
										.param("user_name", "가가")
										.param("user_email", "js1450@gmail.com")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("/admin/adminMG/adminMGMain.tiles", viewName);
	}
	
	/**
	* Method : deleteAdminMGTest
	* 작성자 : 이중석
	* 변경이력 :
	* Method 설명 : 관리자 삭제 테스트
	 * @throws Exception 
	*/
	@Test
	public void deleteAdminMGTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/deleteAdminMG")
										.param("deleteCheck", "admin")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		/***Then***/
		assertEquals("/admin/adminMG/adminMGMain.tiles", viewName);
	}
	
	
}
