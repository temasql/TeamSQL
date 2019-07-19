package kr.or.ddit.sqlEditor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/sqlEditor")
@Controller
public class SqlEditorController {

	@RequestMapping(path =  "/sqlEditorMain", method = RequestMethod.GET)
	public String viewGet() {
		return "/sqlEditor/sqlEditorMain.tiles";
	}
	
//	@RequestMapping(path =  "/view", method = RequestMethod.POST)
//	public String viewPost() {
//		return "";
//	}
}
