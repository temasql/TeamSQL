package kr.or.ddit.chat.team_chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@RequestMapping("/teamChat")
//@Controller
public class TeamChatController {

	@RequestMapping(path =  "/view", method = RequestMethod.GET)
	public String viewGet() {
		return "";
	}
	
	@RequestMapping(path =  "/view", method = RequestMethod.POST)
	public String viewPost() {
		return "";
	}
}
