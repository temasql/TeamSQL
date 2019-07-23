package kr.or.ddit.sqlEditor.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import kr.or.ddit.dbObject.model.FuncProceVO;
import kr.or.ddit.dbObject.model.IndexVO;
import kr.or.ddit.dbObject.model.SequenceVO;
import kr.or.ddit.dbObject.model.TableVO;
import kr.or.ddit.dbObject.model.TriggerVO;
import kr.or.ddit.dbObject.model.ViewVO;
import kr.or.ddit.user.model.UserVO;

@RequestMapping("/sqlEditor")
@Controller
public class SqlEditorController {
	private static final Logger logger = LoggerFactory.getLogger(SqlEditorController.class);
	
	@Resource(name = "accountService")
	private IAccountService accountService;
	
	@RequestMapping(path =  "/sqlEditorMain", method = RequestMethod.GET)
	public String sqlEditorMain(HttpSession session, Model model) {
		
		List<TableVO> tableList = new ArrayList<TableVO>();
		List<ViewVO> viewList = new ArrayList<ViewVO>();
		List<IndexVO> indexList = new ArrayList<IndexVO>();
		List<TriggerVO> triggerList = new ArrayList<TriggerVO>();
		List<SequenceVO> sequenceList = new ArrayList<SequenceVO>();
		List<FuncProceVO> functionList = new ArrayList<FuncProceVO>();
		List<FuncProceVO> procedureList = new ArrayList<FuncProceVO>();
		
		// 가짜 데이터 세팅
		UserVO userInfo = new UserVO("TEST_ID20", "C", "N", "TEST_PW20", "TEST_NAME20", "TEST_MAIL20@TEST.COM", null);
		session.setAttribute("USER_INFO", userInfo);
		
		// 로그인한 사용자의 DB계정 정보 가져와 model객체에 담아서 넘겨줌
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		logger.debug("userVO : {}", userVO);
		List<AccountVO> accountList = accountService.getAccountList(userVO.getUser_id());
		
		for (AccountVO accountVO : accountList) {
			List<TableVO> tempList1 = accountService.getAccountAllTable(accountVO.getAccount_id());
			for(TableVO tableVO : tempList1) {
				tableList.add(tableVO);
			}
			List<ViewVO> tempList2 = accountService.getAccountAllView(accountVO.getAccount_id());
			for(ViewVO viewVO : tempList2) {
				viewList.add(viewVO);
			}
			List<IndexVO> tempList3 = accountService.getAccountAllIndex(accountVO.getAccount_id());
			for(IndexVO indexVO : tempList3) {
				indexList.add(indexVO);
			}
			List<TriggerVO> tempList4 = accountService.getAccountAllTrigger(accountVO.getAccount_id());
			for(TriggerVO triggerVO : tempList4) {
				triggerList.add(triggerVO);
			}
			List<SequenceVO> tempList5 = accountService.getAccountAllSequence(accountVO.getAccount_id());
			for(SequenceVO sequenceVO : tempList5) {
				sequenceList.add(sequenceVO);
			}
			List<FuncProceVO> tempList6 = accountService.getAccountAllFunction(accountVO.getAccount_id());
			for(FuncProceVO functionVO : tempList6) {
				functionList.add(functionVO);
			}
			List<FuncProceVO> tempList7 = accountService.getAccountAllProcedure(accountVO.getAccount_id());
			for(FuncProceVO procedureVO : tempList7) {
				procedureList.add(procedureVO);
			}
		}
		
		model.addAttribute("tableList", tableList);
		model.addAttribute("viewList", viewList);
		model.addAttribute("indexList", indexList);
		model.addAttribute("triggerList", triggerList);
		model.addAttribute("sequenceList", sequenceList);
		model.addAttribute("functionList", functionList);
		model.addAttribute("procedureList", procedureList);
		
		for (AccountVO accountVO : accountList) {
			String temp = accountVO.getAccount_id();
			int end = temp.indexOf(userVO.getUser_id());
			String account_id = temp.substring(0, end - 1);
			accountVO.setAccount_id(account_id);
		}
		model.addAttribute("accountList", accountList);
		
		return "/sqlEditor/sqlEditorMain.tiles";
	}
	
	@RequestMapping(path =  "/addAccount", method = RequestMethod.POST)
	public String addAccount(AccountVO accountVO , HttpSession session, Model model, String chatRoomName) {
		
		String msg = "";
		logger.debug("/account/addAccount 안뇽");
		logger.debug("account_id : {}", accountVO.getAccount_id());
		logger.debug("account_pw : {}", accountVO.getAccount_pw());
		logger.debug("chatRoomName : {}", chatRoomName);
		
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		String account_id = accountVO.getAccount_id();
		String user_id = userVO.getUser_id();
		String real_account_id = account_id + "_" + user_id;
		accountVO.setUser_id_fk(user_id);
		accountVO.setAccount_id(real_account_id);
		
		int result = accountService.insertAccount(accountVO);
		if(result > 0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", real_account_id);
			map.put("pass", accountVO.getAccount_pw());
			int createRes = accountService.createAccount(map);
			int grantRes = accountService.grantAccount(map);
			if(createRes == 0 || grantRes == 0) {
				msg = "DB계정 생성 성공";
			}else {
				msg = "DB계정 생성 실패(Grant)";
			}
		}else {
			msg = "DB계정 생성 실패(Insert)";
		}
		
		model.addAttribute("msg", msg);
		return sqlEditorMain(session, model);
	}
	
	@RequestMapping(path = "/test")
	public String test() {
		return "/sqlEditor/test.tiles";
	}
	
}
