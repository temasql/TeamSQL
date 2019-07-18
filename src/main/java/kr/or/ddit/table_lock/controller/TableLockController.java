package kr.or.ddit.table_lock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@RequestMapping("/tableLock")
//@Controller
public class TableLockController {

	@RequestMapping(path =  "/view", method = RequestMethod.GET)
	public String viewGet() {
		return "";
	}
	
	@RequestMapping(path =  "/view", method = RequestMethod.POST)
	public String viewPost() {
		return "";
	}
}
