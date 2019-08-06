package kr.or.ddit.worksheet.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	/**
	 * 
	* Method : accountChange
	* 작성자 : 김범휘
	* 변경이력 :
	* @param session
	* @return
	* Method 설명 : 워크시트 변경(라디오버튼 클릭시)
	 */
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
	
	/**
	 * 
	* Method : selectRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param dragText
	* @param account_id
	* @param model
	* @param session
	* @return
	* Method 설명 : select 쿼리문 조회
	 */
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
		for (int i = 0; i < resultList.size(); i++) {
			for (int j = 0; j < resultList.get(i).size(); j++) {
				String temp = resultList.get(i).get(j);
				if(temp.contains("ORA")) {
					logger.debug("에로로로 : {}", temp);
					model.addAttribute("errorMsg", temp);
				}
			}
		}
		
		model.addAttribute("resultList", resultList);
		
		return "jsonView";
	}
	
	/**
	 * 
	* Method : anotherRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param dragText
	* @param account_id
	* @param model
	* @param session
	* @return
	* Method 설명 : select문, commit, rollback 제외한 쿼리문 실행
	 */
	@RequestMapping(path = "/anotherRun", method = RequestMethod.GET)
	public String anotherRun(String dragText, String account_id, Model model, HttpSession session) {
		AccountVO accountVO = accountServcie.getAccountOne(account_id);
		Connection conn = DBUtilForWorksheet.getConnection(account_id, accountVO.getAccount_pw(), session);
		
		if(dragText.contains(";")) {
			int end = dragText.indexOf(";");
			dragText = dragText.substring(0, end);
		}
		
		Map<String, Object> resultMap = worksheetService.anotherRun(dragText, conn);
		model.addAttribute("resultMap", resultMap);
		
		return "jsonView";
	}
	
	/**
	 * 
	* Method : ddlRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param dragText
	* @param account_id
	* @param model
	* @param session
	* @return
	* Method 설명 : DDL문 실행
	 */
	@RequestMapping(path = "/ddlRun", method = RequestMethod.GET)
	public String ddlRun(String dragText, String account_id, Model model, HttpSession session) {
		AccountVO accountVO = accountServcie.getAccountOne(account_id);
		Connection conn = DBUtilForWorksheet.getConnection(account_id, accountVO.getAccount_pw(), session);
		
		if(dragText.contains("CREATE") && dragText.contains("PROCEDURE")) {
			
		}else if(dragText.contains("CREATE") && dragText.contains("FUNCTION")) {
			
		}else if(dragText.contains("CREATE") && dragText.contains("TRIGGER")) {
			
		}else if(dragText.contains(";")) {
			int end = dragText.indexOf(";");
			dragText = dragText.substring(0, end);
		}
		
		logger.debug("냐냐냐 : {}", dragText);
		
		Map<String, String> resultMap = worksheetService.ddlRun(dragText, conn);
		model.addAttribute("resultMap", resultMap);
		
		return "jsonView";
	}
	
	/**
	 * 
	* Method : commitRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param dragText
	* @param account_id
	* @param model
	* @param session
	* @return
	* Method 설명 : commit문 실행
	 */
	@RequestMapping(path = "/commitRun", method = RequestMethod.GET)
	public String commitRun(String dragText, String account_id, Model model, HttpSession session) {
		AccountVO accountVO = accountServcie.getAccountOne(account_id);
		Connection conn = DBUtilForWorksheet.getConnection(account_id, accountVO.getAccount_pw(), session);
		
		if(dragText.contains(";")) {
			int end = dragText.indexOf(";");
			dragText = dragText.substring(0, end);
		}
		
		int resultCnt = worksheetService.commitRun(dragText, conn);
		model.addAttribute("resultCnt", resultCnt);
		
		return "jsonView";
	}
	
	/**
	 * 
	* Method : rollbackRun
	* 작성자 : 김범휘
	* 변경이력 :
	* @param dragText
	* @param account_id
	* @param model
	* @param session
	* @return
	* Method 설명 : rollback문 실행
	 */
	@RequestMapping(path = "/rollbackRun", method = RequestMethod.GET)
	public String rollbackRun(String dragText, String account_id, Model model, HttpSession session) {
		AccountVO accountVO = accountServcie.getAccountOne(account_id);
		Connection conn = DBUtilForWorksheet.getConnection(account_id, accountVO.getAccount_pw(), session);
		
		if(dragText.contains(";")) {
			int end = dragText.indexOf(";");
			dragText = dragText.substring(0, end);
		}
		
		int resultCnt = worksheetService.rollbackRun(dragText, conn);
		model.addAttribute("resultCnt", resultCnt);
		
		return "jsonView";
	}
	
	@RequestMapping(path = "/planRun", method = RequestMethod.GET)
	@ResponseBody
	public List<String> planRun(String dragText, String account_id, Model model, HttpSession session) {
		AccountVO accountVO = accountServcie.getAccountOne(account_id);
		Connection conn = DBUtilForWorksheet.getConnection(account_id, accountVO.getAccount_pw(), session);
		List<String> resultList = new ArrayList<String>();
		
		if(dragText.contains(";")) {
			int end = dragText.indexOf(";");
			dragText = dragText.substring(0, end);
		}
		
		String plan = "EXPLAIN PLAN FOR " + dragText;
		String result = worksheetService.planInsert(plan, conn);
		
		if(result.equals("Y"))
			resultList = worksheetService.planRun(conn);
		else
			resultList.add(result);
		
		return resultList;
	}

}
