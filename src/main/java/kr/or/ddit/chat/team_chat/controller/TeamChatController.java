package kr.or.ddit.chat.team_chat.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.user.model.UserVO;

@RequestMapping("/teamChat")
@Controller
public class TeamChatController {

	/**
	* Method : viewGet
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : 채팅화면으로 이동 및 DB에 저장되있는 채팅 내역 출력
	*/
	@RequestMapping(path =  "/ChattingView", method = RequestMethod.GET)
	public String viewGet(Model model, HttpSession session) {
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		session.setAttribute("userId", userVO.getUser_id());
		
		model.addAttribute("userId", userVO.getUser_id());
		
		
		return "/chatting/chatting";
	}
	
	/**
	* Method : viewPost
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : 사용자가 채팅 입력시 DB 저장 및 화면 출력
	*/
	@RequestMapping(path =  "/ChattingView", method = RequestMethod.POST)
	public String viewPost(Model model) {
		return "";
	}
}
