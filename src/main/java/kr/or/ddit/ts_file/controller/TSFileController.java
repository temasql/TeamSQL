package kr.or.ddit.ts_file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@RequestMapping("/tsFile")
//@Controller
public class TSFileController {

	@RequestMapping(path =  "/view", method = RequestMethod.GET)
	public String viewGet() {
		return "";
	}
	
	@RequestMapping(path =  "/view", method = RequestMethod.POST)
	public String viewPost() {
		return "";
	}
}
