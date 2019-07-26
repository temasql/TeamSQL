package kr.or.ddit.worksheet.controller;

import java.sql.Connection;
import java.sql.SQLException;
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

import kr.or.ddit.account.model.AccountVO;
import kr.or.ddit.account.service.IAccountService;
import kr.or.ddit.util.DBUtilForWorksheet;
import kr.or.ddit.worksheet.service.IWorkSheetService;

@RequestMapping(path = "/worksheet")
@Controller
public class WorksheetController {
	
	private static final Logger logger = LoggerFactory.getLogger(WorksheetController.class);
	
	@Resource(name = "worksheetService")
	IWorkSheetService worksheetService;
	
	@Resource(name = "accountService")
	IAccountService accountServcie;
	
	@RequestMapping(path = "/accountChange", method = RequestMethod.GET)
	public String accountChange(HttpSession session) {
		Connection conn = (Connection) session.getAttribute("conn");
		try {
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.removeAttribute("conn");
		return "jsonView";
	}
	
	@RequestMapping(path = "/selectRun", method = RequestMethod.GET)
	public String selectRun(String dragText, String account_id, Model model, HttpSession session) {
		logger.debug("account_id : {}", account_id);
		AccountVO accountVO = accountServcie.getAccountOne(account_id);
		Connection conn = DBUtilForWorksheet.getConnection(account_id, accountVO.getAccount_pw(), session);
		logger.debug("ession.getAttribute('conn') : {}", session.getAttribute("conn"));
		
		if(dragText.contains(";")) {
			int end = dragText.indexOf(";");
			dragText = dragText.substring(0, end);
		}
		
		List<List<String>> resultList = worksheetService.selectRun(dragText, conn);
		model.addAttribute("resultList", resultList);
		
		return "jsonView";
	}
	
	@RequestMapping(path = "/anotherRun", method = RequestMethod.GET)
	public String anotherRun(String dragText, String account_id, Model model) {
//		logger.debug("anotherRun");
//		logger.debug("dragText : {}", dragText);
//		
//		String queryType = "";
//		
//		if(dragText.contains(";")) {
//			int end = dragText.indexOf(";");
//			dragText = dragText.substring(0, end);
//		}
//		
//		StringBuffer sb = new StringBuffer();
//		
//		if(dragText.contains("insert") || dragText.contains("INSERT")) {
//			queryType = "삽입";
//			
//			String[] tempArry = dragText.split("into");
//			sb.append(tempArry[0]);
//			sb.append(" into ");
//			String temp = account_id + "." + tempArry[1];
//			sb.append(temp);
//			
//		}else if(dragText.contains("update") || dragText.contains("UPDATE")) {
//			queryType = "변경";
//		}else if(dragText.contains("delete") || dragText.contains("DELETE")) {
//			queryType = "삭제";
//		}else if(dragText.contains("commit") || dragText.contains("COMMIT")) {
//			sb.append(dragText);
//			queryType = "커밋";
//		}else if(dragText.contains("rollback") || dragText.contains("ROLLBACK")) {
//			sb.append(dragText);
//			queryType = "롤백";
//		}
//		
//		logger.debug("sb.toString() : {}", sb.toString());
//		int resultCnt = worksheetService.anotherQuery(sb.toString());
//		
//		model.addAttribute("queryType", queryType);
//		model.addAttribute("resultCnt", resultCnt);
		
		return "jsonView";
	}

}
