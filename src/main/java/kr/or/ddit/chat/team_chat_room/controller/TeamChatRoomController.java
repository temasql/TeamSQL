package kr.or.ddit.chat.team_chat_room.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@RequestMapping("/teamChatRoom")
@Controller
public class TeamChatRoomController {

	@RequestMapping(path =  "/view", method = RequestMethod.GET)
	public String viewGet() {
		return "";
	}
	
	@RequestMapping(path =  "/view", method = RequestMethod.POST)
	public String viewPost() {
		return "";
	}
}
