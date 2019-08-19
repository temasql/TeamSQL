package kr.or.ddit.chat.team_chat.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.chat.team_chat.model.TeamChatVO;
import kr.or.ddit.chat.team_chat.service.ITeamChatService;
import kr.or.ddit.chat.team_chat_room.model.TeamChatRoomVO;
import kr.or.ddit.chat.team_chat_room.service.ITeamChatRoomService;
import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.crew.service.ICrewService;
import kr.or.ddit.user.model.UserVO;

@RequestMapping("/teamChat")
@Controller
public class TeamChatController {
	@Resource(name="crewService")
	private ICrewService crewService;
	@Resource(name="teamChatRoomService")
	private ITeamChatRoomService chatRoomService;
	@Resource(name="teamChatService")
	private ITeamChatService chatService;
	
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
		TeamChatRoomVO teamChatRoomVo = null;
		TeamChatVO teamChatVO = new TeamChatVO();
		
		model.addAttribute("userId", userVO.getUser_id());

		List<CrewVO> crewList = crewService.crewSelectList(userVO.getUser_id());
		List<String> crewListCopy = new ArrayList<String>();
		
		String account_id = null;
		try {
			// 채팅방 아이디 조회
			account_id = crewList.get(0).getAccount_id_fk();
			logger.debug("복제 안한거 : {}", account_id);
			teamChatRoomVo = chatRoomService.getChatRoomId(account_id);
			model.addAttribute("teamChatRoomVo", teamChatRoomVo);
			logger.debug("teamChatRoomVo : {}", teamChatRoomVo);
			
			session.setAttribute("CREWLIST", crewList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("outOfIndex");
			model.addAttribute("msg","계정을 생성 후 접속을 시도해 주세요.");
			return "/chatting/errorChatting";
		}
		int roomId = teamChatRoomVo.getChat_room_id();
		logger.debug("채팅 방번호 : {}", roomId);
		teamChatVO.setChat_room_id_fk(roomId);
		teamChatVO.setUser_id_fk(userVO.getUser_id());
		teamChatVO.setAccount_id_fk(account_id);
		teamChatVO.setChat_room_id_fk(teamChatRoomVo.getChat_room_id());
		session.setAttribute("TEAM_INFO", teamChatVO);
		List<TeamChatVO> userChatList = chatService.userChatList(teamChatVO);
		logger.debug("채팅 리스트 : {}", userChatList);
		
		for(int i=0; i<crewList.size(); i++) {
			String accountId = crewList.get(i).getAccount_id_fk().substring(0, crewList.get(i).getAccount_id_fk().lastIndexOf("_"));
			logger.debug("계정아이디 언더바 삭제 : {}", accountId);
			crewListCopy.add(accountId);
		}
		logger.debug("crewListCopy : {}", crewListCopy.get(0));
		logger.debug("crewList : {}", crewList.get(0));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd HH:mm");
		
		String temp = "";
		for(TeamChatVO ChatVO : userChatList) {
			String date = sdf.format(ChatVO.getChat_dt());
			if(userVO.getUser_id().equals(ChatVO.getUser_id_fk())) {
				temp += "<div class='myDiv'>";
				temp += "<div class='myDt'>" +date + "</div>";
				temp += "<p class='Mmessage'>" + ChatVO.getChat_content() + "</p>";
				temp += "</div>";
			}else {
				temp += "<div class='youDiv'>";
				temp += "<p class='youId'>"+ChatVO.getUser_id_fk()+"</p>";
				temp += "<p class='Ymessage'>" + ChatVO.getChat_content() + "</p>";
				temp += "<div class='youDt'>" + date + "</div>";
				temp += "</div>";
			}
		}
		
		model.addAttribute("chat_room_name", teamChatRoomVo.getChat_room_name());
		model.addAttribute("account_id", account_id);
		model.addAttribute("crewList", crewList);
		model.addAttribute("crewListCopy", crewListCopy);
		model.addAttribute("userChat", temp);
		
		return "/chatting/chatting";
	}
	
	/**
	* Method : viewPost
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : 사용자가 채팅 입력시 DB 저장 및 화면 출력
	*/
	@RequestMapping(path =  "/changeAccount", method = RequestMethod.POST)
	public String viewPost(Model model, HttpSession session, String account_id, String chat_room_id) {
		logger.debug("체인지 이벤트");
		logger.debug("account : {}", account_id);
		logger.debug("chat_room_id : {}", chat_room_id);
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		
//		account_id = account_id + "_" + userVO.getUser_id();
		
		List<CrewVO> crewList = crewService.crewSelectList(userVO.getUser_id());
		List<String> crewListCopy = new ArrayList<String>();
		
		TeamChatVO teamChatVO = new TeamChatVO();
		TeamChatRoomVO teamChatRoomVo = null;
		
		teamChatRoomVo = chatRoomService.getChatRoomId(account_id);
		model.addAttribute("teamChatRoomVo", teamChatRoomVo);
		int roomId = teamChatRoomVo.getChat_room_id();
		logger.debug("채팅 방번호 : {}", roomId);
		teamChatVO.setChat_room_id_fk(roomId);
		teamChatVO.setUser_id_fk(userVO.getUser_id());
		teamChatVO.setAccount_id_fk(account_id);
		teamChatVO.setChat_room_id_fk(teamChatRoomVo.getChat_room_id());
		session.setAttribute("TEAM_INFO", teamChatVO);
		List<TeamChatVO> userChatList = chatService.userChatList(teamChatVO);
		logger.debug("채팅 리스트 : {}", userChatList);
		logger.debug("채팅 crewList : {}", crewList);
		
		
		for(int i=0; i<crewList.size(); i++) {
			String accountId = crewList.get(i).getAccount_id_fk().substring(0, crewList.get(i).getAccount_id_fk().lastIndexOf("_"));
			logger.debug("계정아이디 언더바 삭제 : {}", accountId);
			crewListCopy.add(accountId);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd HH:mm");
		
		String temp = "";
		for(TeamChatVO ChatVO : userChatList) {
			String date = sdf.format(ChatVO.getChat_dt());
			if(userVO.getUser_id().equals(ChatVO.getUser_id_fk())) {
				temp += "<div class='myDiv'>";
				temp += "<div class='myDt'>" +date + "</div>";
				temp += "<p class='Mmessage'>" + ChatVO.getChat_content() + "</p>";
				temp += "</div>";
			}else {
				temp += "<div class='youDiv'>";
				temp += "<p class='youId'>"+ChatVO.getUser_id_fk()+"</p>";
				temp += "<p class='Ymessage'>" + ChatVO.getChat_content() + "</p>";
				temp += "<div class='youDt'>" + date + "</div>";
				temp += "</div>";
			}
		}
		
		logger.debug("채팅 crewListCopy : {}", crewListCopy);
		
		model.addAttribute("userId", userVO.getUser_id());
		model.addAttribute("chat_room_name", teamChatRoomVo.getChat_room_name());
		model.addAttribute("account_id", account_id);
		model.addAttribute("crewList", crewList);
		model.addAttribute("crewListCopy", crewListCopy);
		model.addAttribute("userChat", temp);
		
		return "/chatting/chatting";
	}
}
