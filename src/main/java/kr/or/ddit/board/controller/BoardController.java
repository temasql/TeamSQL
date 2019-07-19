package kr.or.ddit.board.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVO;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	
	/**
	* Method : viewGet
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 관리 메인 화면 요청
	*/
	@RequestMapping("/manager")
	public String viewGet() {
		return "/admin/boardMG.tiles";
	}
	
	
	/**
	* Method : addBoard
	* 작성자 : 이영은
	* 변경이력 :
	* @param session
	* @param board_name
	* @param board_use
	* @return
	* Method 설명 : 게시판 추가
	*/
	@RequestMapping(path =  "/addBoard", method = RequestMethod.POST)
	public String addBoard(HttpSession session, String board_name, String board_use) {
		String user_id = ((UserVO) session.getAttribute("USER_INFO")).getUser_id();
		
		BoardVO boardVo = new BoardVO(user_id, board_name, board_use);
		
		if(boardService.addBoard(boardVo) == 1 ) {
			session.setAttribute("boardList", boardService.boardList());
			return "/admin/boardMG.tiles";
		} else {
			return "/admin/boardMG.tiles";
		}
	}
	
	
	/**
	* Method : modifyBoard
	* 작성자 : 이영은
	* 변경이력 :
	* @param session
	* @param board_id
	* @param updateBoardName
	* @param updateUse_yn
	* @return
	* Method 설명 : 게시판 수정
	*/
	@RequestMapping(path = "/modifyBoard", method = RequestMethod.POST)
	public String modifyBoard(HttpSession session, int board_id, String updateBoardName, String updateBoard_use) {
		String user_id = ((UserVO) session.getAttribute("USER_INFO")).getUser_id();
		
		BoardVO boardVo = new BoardVO(board_id, user_id, updateBoardName, updateBoard_use);
		
		int updateCnt = boardService.modifyBoard(boardVo);
		
		if(updateCnt == 1) {
			List<BoardVO> boardList = boardService.boardList();
			session.setAttribute("boardList", boardList);
		}
		return "/admin/boardMG.tiles";
	}

	
}
