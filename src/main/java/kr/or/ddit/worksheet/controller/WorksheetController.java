package kr.or.ddit.worksheet.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.worksheet.service.IWorkSheetService;

@RequestMapping(path = "/worksheet")
@Controller
public class WorksheetController {
	
	private static final Logger logger = LoggerFactory.getLogger(WorksheetController.class);
	
	@Resource(name = "worksheetService")
	IWorkSheetService worksheetService;
	
	@RequestMapping(path = "/run", method = RequestMethod.GET)
	public String run(String dragText, Model model) {
		logger.debug("dragText : {}", dragText);
		
		if(dragText.contains(";")) {
			int end = dragText.indexOf(";");
			dragText = dragText.substring(0, end);
		}
		
		List<Map<String, String>> resultList = worksheetService.selectQuery(dragText);
		logger.debug("resultList : {}", resultList);
		
		Iterator<String> iterator = resultList.get(0).keySet().iterator();
		
		for (int i = 0; i < resultList.size(); i++) {
			resultList.get(i).keySet();
		}
		
		model.addAttribute("resultList", resultList);
		model.addAttribute("iterator", iterator);
		return "jsonView";
	}

}
