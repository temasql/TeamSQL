package kr.or.ddit.board.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;

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
public class BoardControllerTest extends ControllerTestEnv{

	@Resource(name = "boardService")
	private IBoardService boardService;
	
	/**
	* Method : boardManager
	* 작성자 : 이영은
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시판 관리 메인 화면 요청 테스트
	*/
	@Test
	public void boardManager() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/board/manager")).andReturn();
	
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("/admin/boardMG.tiles", viewName);
	}
	
	

	/**
	* Method : addBoardTest
	* 작성자 : 이영은
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시판 추가 테스트
	*/
	@Test
	public void addBoardTest() throws Exception {
		/***Given***/
		UserVO userVo = new UserVO();
		userVo.setUser_id("admin");
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/board/addBoard")
																	.sessionAttr("USER_INFO", userVo)
																	.param("board_name", "테스트게시판4")
																	.param("board_use", "y")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("/admin/boardMG.tiles", viewName);
	}

	
	/**
	* Method : modifyBoard
	* 작성자 : 이영은
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 게시판 수정 테스트
	*/
	@Test
	public void modifyBoard() throws Exception {
		/***Given***/
		String user_id = "admin";
		UserVO userVo = new UserVO();
		userVo.setUser_id(user_id);
		
		int board_id = boardService.boardMaxCnt()-1;
		
		String board_id_str = new String().valueOf(board_id);
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(post("/board/modifyBoard")
																	.sessionAttr("USER_INFO", userVo)
																	.param("board_id", board_id_str)
																	.param("updateBoardName", "게시판수정")
																	.param("updateBoard_use", "n")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("/admin/boardMG.tiles", viewName);
	}
	
}
