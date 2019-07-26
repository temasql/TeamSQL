package kr.or.ddit.chatBot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/chatBot")
@Controller
public class ChatBotController {
	
	/**
	 * 
	* Method : chatBot
	* 작성자 : PC20
	* 변경이력 :
	* @return
	* Method 설명 : 챗봇
	 */
	@RequestMapping(path =  "/chatBot", method = RequestMethod.GET)
	public String chatBot() {
		return "/chatBot/chatBot";
	}
	
	
}
