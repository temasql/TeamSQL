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
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.account.model.AccountVO;
import kr.or.ddit.account.service.IAccountService;
import kr.or.ddit.chat.team_chat_room.model.TeamChatRoomVO;
import kr.or.ddit.chat.team_chat_room.service.ITeamChatRoomService;
import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.crew.service.ICrewService;
import kr.or.ddit.dbObject.model.FuncProceVO;
import kr.or.ddit.dbObject.model.IndexVO;
import kr.or.ddit.dbObject.model.SequenceVO;
import kr.or.ddit.dbObject.model.TableVO;
import kr.or.ddit.dbObject.model.TriggerVO;
import kr.or.ddit.dbObject.model.ViewVO;
import kr.or.ddit.sqlEditor.service.ISqlEditorService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.FindAccountPwByMail;

@RequestMapping("/sqlEditor")
@Controller
public class SqlEditorController {
	private static final Logger logger = LoggerFactory.getLogger(SqlEditorController.class);
	
	@Resource(name = "accountService")
	private IAccountService accountService;
	
	@Resource(name = "userService")
	private IUserService userService;
	
	@Resource(name = "crewService")
	private ICrewService crewService;
	
	@Resource(name = "teamChatRoomService")
	private ITeamChatRoomService teamChatRoomService;
	
	@Resource(name = "sqlEditorService")
	private ISqlEditorService sqlEditorService;
	
	@RequestMapping(path =  "/sqlEditorMain", method = RequestMethod.GET)
	public String sqlEditorMain(HttpSession session, Model model) {
		logger.debug("안녕하세요");
		
		List<TableVO> tableList = new ArrayList<TableVO>();
		List<ViewVO> viewList = new ArrayList<ViewVO>();
		List<IndexVO> indexList = new ArrayList<IndexVO>();
		List<TriggerVO> triggerList = new ArrayList<TriggerVO>();
		List<SequenceVO> sequenceList = new ArrayList<SequenceVO>();
		List<FuncProceVO> functionList = new ArrayList<FuncProceVO>();
		List<FuncProceVO> procedureList = new ArrayList<FuncProceVO>();
		
		// 가짜 데이터 세팅
//		UserVO userInfo = new UserVO("TEST_ID20", "C", "N", "TEST_PW20", "TEST_NAME20", "TEST_MAIL20@TEST.COM", null);
//		session.setAttribute("USER_INFO", userInfo);
		
		// 로그인한 사용자의 DB계정 정보 가져와 model객체에 담아서 넘겨줌
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		logger.debug("userVO : {}", userVO);
		List<AccountVO> accountList = accountService.getAccountList(userVO.getUser_id());
		
		for (AccountVO accountVO : accountList) {
			List<TableVO> tempList1 = accountService.getAccountAllTable(accountVO.getAccount_id().toUpperCase());
			logger.debug("김범휘 : {}", accountVO.getAccount_id());
			for(TableVO tableVO : tempList1) {
				tableList.add(tableVO);
			}
			List<ViewVO> tempList2 = accountService.getAccountAllView(accountVO.getAccount_id().toUpperCase());
			for(ViewVO viewVO : tempList2) {
				viewList.add(viewVO);
			}
			List<IndexVO> tempList3 = accountService.getAccountAllIndex(accountVO.getAccount_id().toUpperCase());
			for(IndexVO indexVO : tempList3) {
				indexList.add(indexVO);
			}
			List<TriggerVO> tempList4 = accountService.getAccountAllTrigger(accountVO.getAccount_id().toUpperCase());
			for(TriggerVO triggerVO : tempList4) {
				triggerList.add(triggerVO);
			}
			List<SequenceVO> tempList5 = accountService.getAccountAllSequence(accountVO.getAccount_id().toUpperCase());
			for(SequenceVO sequenceVO : tempList5) {
				sequenceList.add(sequenceVO);
			}
			List<FuncProceVO> tempList6 = accountService.getAccountAllFunction(accountVO.getAccount_id().toUpperCase());
			for(FuncProceVO functionVO : tempList6) {
				functionList.add(functionVO);
			}
			List<FuncProceVO> tempList7 = accountService.getAccountAllProcedure(accountVO.getAccount_id().toUpperCase());
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
		String account_id = accountVO.getAccount_id().toUpperCase();
		String user_id = userVO.getUser_id();
		String real_account_id = account_id + "_" + user_id;
		String test_account_id = account_id.toLowerCase() + "_" + user_id;
		accountVO.setUser_id_fk(user_id);
		
		int cnt = accountService.getAccountCnt(real_account_id);
		int cnt2 = accountService.getAccountCnt(test_account_id);
		if(cnt > 0 || cnt2 > 0) {
			msg = "DB계정명이 중복됩니다.";
			model.addAttribute("msg", msg);
			return sqlEditorMain(session, model);
		}
		accountVO.setAccount_id(real_account_id);
		int result = accountService.insertAccount(accountVO);
		if(result > 0) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("name", real_account_id);
			map.put("pass", accountVO.getAccount_pw());
			int createRes = accountService.createAccount(map);
			int grantRes = accountService.grantAccount(map);
			
			CrewVO crewVO = new CrewVO(real_account_id, user_id);
			int crewCnt = crewService.insertCrew(crewVO);
			
			TeamChatRoomVO teamChatRoomVo = new TeamChatRoomVO(real_account_id, user_id, chatRoomName);
			int chatRoomCnt = teamChatRoomService.insertTeamChatRoom(teamChatRoomVo);
			
			if(createRes == 0 && grantRes == 0 && chatRoomCnt == 1 && crewCnt == 1) {
				msg = "DB계정이 생성되었습니다.";
			}else {
				msg = "DB계정 생성에 실패하였습니다.";
			}
		}else {
			msg = "DB계정 생성에 실패실패하였습니다.";
		}
		
		model.addAttribute("msg", msg);
		return sqlEditorMain(session, model);
	}
	
	@RequestMapping(path = "/deleteAccount", method = RequestMethod.POST)
	public String deleteAccount(String deletePw, String deleteId, HttpSession session, Model model) {
		String msg = "";
		logger.debug("deletePw : {}", deletePw);
		logger.debug("deleteId : {}", deleteId);
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		String delete_id = deleteId + "_" + userVO.getUser_id();
		
		AccountVO accountVO = accountService.getAccountOne(delete_id);
		
		if(accountVO.getAccount_pw().equals(deletePw)) {
			accountService.deleteAccount(delete_id);
			int result = accountService.deleteAccountByTable(delete_id);
			if(result > 0) {
				msg = "DB계정이 삭제되었습니다.";
			}else {
				msg = "DB계정 삭제에 실패하였습니다.";
			}
		}else {
			msg = "비밀번호가 일치하지 않습니다.";
		}
		model.addAttribute("msg", msg);
		return sqlEditorMain(session, model);
	}
	
	@RequestMapping(path = "/findPwAccount", method = RequestMethod.POST)
	public String findPwAccount(String user_id, String user_email, String findId, HttpSession session, Model model) {
		String msg = "";
		UserVO userVO = userService.getUser(user_id);
		if(userVO == null) {
			msg = "일치하는 회원정보가 없습니다.";
			return sqlEditorMain(session, model);
		}
		if(userVO.getUser_email().equals(user_email)) {
			String find_id = findId + "_" + user_id;
			AccountVO accountVO = accountService.getAccountOne(find_id);
			new FindAccountPwByMail().sendMail(user_email, user_id, find_id, accountVO.getAccount_pw());
			msg = "DB계정 비밀번호를 회원님의 메일으로 보내드렸습니다.";
		}else {
			msg = "일치하는 회원정보가 없습니다.";
		}
		model.addAttribute("msg", msg);
		return sqlEditorMain(session, model);
	}

	@RequestMapping(path = "/updatePwAccount", method = RequestMethod.POST)
	public String updatePwAccount(String originalPw, String updatePw, String reUpdatePw, String updateId, 
									HttpSession session, Model model) {
		String msg = "";
		
		if(!updatePw.equals(reUpdatePw)) {
			msg = "변경 비밀번호와 변경 비밀번호 확인이 일치하지 않습니다.";
			model.addAttribute("msg", msg);
			return sqlEditorMain(session, model);
		}
		
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		String update_id = updateId + "_" + userVO.getUser_id();
		AccountVO accountVO = accountService.getAccountOne(update_id);
		
		if(accountVO.getAccount_pw().equals(originalPw)) {
			String value = "ALTER USER " + update_id + " IDENTIFIED BY " + updatePw;
			int updateCnt = accountService.updateAccount(value);
			
			accountVO.setAccount_pw(updatePw);
			int updateTableCnt =  accountService.updateAccountByTable(accountVO);
			
			if(updateCnt == 0 && updateTableCnt == 1) {
				msg = "비밀번호가 변경되었습니다.";
			}else {
				msg = "비밀번호 변경을 실패하였습니다.";
			}
			
		}else {
			msg = "현재 비밀번호가 일치하지 않습니다.";
		}
		
		model.addAttribute("msg", msg);
		return sqlEditorMain(session, model);
	}
	
	@RequestMapping(path = "/runPlan", method = RequestMethod.GET)
	@ResponseBody
	public List<String> runPlan(String dragText) {
		logger.debug("runPlan 안뇽");
		logger.debug("dragText : {}", dragText);
		
		if(dragText.contains(";")) {
			int end = dragText.indexOf(";");
			dragText = dragText.substring(0, end);
		}
		
		String plan = "EXPLAIN PLAN FOR " + dragText;
		sqlEditorService.runPlan(plan);
		List<String> planList = sqlEditorService.runPlanView();
		
		return planList;
	}
	
//	@RequestMapping(path = "/test")
//	public String test() {
//		return "/sqlEditor/rightTest.tiles";
//	}
	
}
