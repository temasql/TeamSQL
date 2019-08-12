package kr.or.ddit.chat.team_chat.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.crew.service.ICrewService;
import kr.or.ddit.user.model.UserVO;

@RequestMapping("/teamChat")
@Controller
public class TeamChatController {
	@Resource(name="crewService")
	private ICrewService crewService;
	private static final Logger logger = LoggerFactory.getLogger(TeamChatController.class);
	
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
		
		model.addAttribute("userId", userVO.getUser_id());

		//DB계정을 생성 안했을 시 공백으로 초기화
		List<CrewVO> crewList = crewService.crewSelectList(userVO.getUser_id());
		if(crewList.get(0).getAccount_id_fk()==null) {
			crewList.get(0).setAccount_id_fk("");
		}
		
		for(CrewVO crewVO : crewList) {
			crewVO.setAccount_id_fk(crewVO.getAccount_id_fk().substring(0, crewVO.getAccount_id_fk().lastIndexOf("_"+userVO.getUser_id())));
		}
		
		//리스트에서 첫번째 계정을 하드 코딩
		session.setAttribute("crewAccount", crewList.get(0).getAccount_id_fk());
		
		model.addAttribute("crewList", crewList);
		
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
