package kr.or.ddit.history.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/history")
@Controller
public class HistoryController {

	@RequestMapping(path =  "/historyList", method = RequestMethod.GET)
	public String viewGet() {
		return "/history/historyList.tiles";
	}
	
	@RequestMapping(path =  "/view", method = RequestMethod.POST)
	public String viewPost() {
		return "";
	}
}
