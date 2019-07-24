package kr.or.ddit.chatBot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/chatBot")
@Controller
public class ChatBotController {

	@RequestMapping(path =  "/chatBot", method = RequestMethod.GET)
	public String viewGet() {
		return "/chatBot/chatBot";
	}
	
	@RequestMapping(path =  "/view", method = RequestMethod.POST)
	public String viewPost() {
		return "";
	}
}
