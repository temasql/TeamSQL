package kr.or.ddit.sqlEditor.controller;

import java.sql.Connection;
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
import org.springframework.web.bind.annotation.RequestBody;
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

import kr.or.ddit.sqlEdiotTable.model.SqlEditorTableVO;
import kr.or.ddit.sqlEdiotSequence.model.SelectSeqVO;
import kr.or.ddit.sqlEdiotSequence.service.ISqlEditorSequenceService;
import kr.or.ddit.sqlEdiotTable.service.ISqlEditorTableService;
import kr.or.ddit.sqlEditor.service.ISqlEditorService;
import kr.or.ddit.sqlEditorFunction.model.FunctionDetailVO;
import kr.or.ddit.sqlEditorFunction.service.ISqlEditorFunctionService;
import kr.or.ddit.sqlEditorTrigger.model.MyTriggerVO;

import kr.or.ddit.sqlEditorTrigger.model.MyTriggerCodeVO;
import kr.or.ddit.sqlEditorTrigger.model.MyTriggerVO;
import kr.or.ddit.sqlEditorTrigger.model.TriggerDetailVO;

import kr.or.ddit.sqlEditorTrigger.service.ISqlEditorTriggerService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.DBUtilForWorksheet;
import kr.or.ddit.util.DataTypeUtil;
import kr.or.ddit.util.FindAccountPwByMail;
import kr.or.ddit.util.FunctionUtil;
import kr.or.ddit.util.TriggerUtil;

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
	
	@Resource(name = "sqlEditorTableService")
	private ISqlEditorTableService sqlEditorTableService;
	
	@Resource(name = "sqlEditorTriggerService")
	private ISqlEditorTriggerService sqlEditorTriggerService;
	
	@Resource(name = "sqlEditorSequenceService")
	private ISqlEditorSequenceService sqlEditorSequenceService;
	
	@Resource(name = "sqlEditorFunctionService")
	private ISqlEditorFunctionService sqlEditorFunctionService;
	
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
		
		// 로그인한 사용자의 DB계정 정보 가져와 model객체에 담아서 넘겨줌
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		logger.debug("userVO : {}", userVO);
		
		List<String> myAccountList = crewService.getMyAccountList(userVO.getUser_id());
		for (String account_id : myAccountList) {
			List<TableVO> tempList1 = accountService.getAccountAllTable(account_id.toUpperCase());
			logger.debug("김범휘 : {}", account_id);
			for(TableVO tableVO : tempList1) {
				tableList.add(tableVO);
			}
			List<ViewVO> tempList2 = accountService.getAccountAllView(account_id.toUpperCase());
			for(ViewVO viewVO : tempList2) {
				viewList.add(viewVO);
			}
			List<IndexVO> tempList3 = accountService.getAccountAllIndex(account_id.toUpperCase());
			for(IndexVO indexVO : tempList3) {
				indexList.add(indexVO);
			}
			List<TriggerVO> tempList4 = accountService.getAccountAllTrigger(account_id.toUpperCase());
			for(TriggerVO triggerVO : tempList4) {
				triggerList.add(triggerVO);
			}
			List<SequenceVO> tempList5 = accountService.getAccountAllSequence(account_id.toUpperCase());
			for(SequenceVO sequenceVO : tempList5) {
				sequenceList.add(sequenceVO);
			}
			List<FuncProceVO> tempList6 = accountService.getAccountAllFunction(account_id.toUpperCase());
			for(FuncProceVO functionVO : tempList6) {
				functionList.add(functionVO);
			}
			List<FuncProceVO> tempList7 = accountService.getAccountAllProcedure(account_id.toUpperCase());
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
		
		model.addAttribute("myAccountList", myAccountList);

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
		
		AccountVO accountVO = accountService.getAccountOne(deleteId);
		
		if(accountVO.getAccount_pw().equals(deletePw)) {
			accountService.deleteAccount(deleteId);
			int result = accountService.deleteAccountByTable(deleteId);
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
		logger.debug("findId : {}", findId);
		String msg = "";
		UserVO userVO = userService.getUser(user_id);
		if(userVO == null) {
			msg = "일치하는 회원정보가 없습니다.";
			return sqlEditorMain(session, model);
		}
		if(userVO.getUser_email().equals(user_email)) {
			AccountVO accountVO = accountService.getAccountOne(findId);
			new FindAccountPwByMail().sendMail(user_email, user_id, findId, accountVO.getAccount_pw());
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
		
		AccountVO accountVO = accountService.getAccountOne(updateId);
		
		if(accountVO.getAccount_pw().equals(originalPw)) {
			String value = "ALTER USER " + updateId + " IDENTIFIED BY " + updatePw;
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
	
	@RequestMapping(path =  "/createTriggerReady", method = RequestMethod.GET)
	@ResponseBody
	public List<String> createTrigger(String account_id) {
		List<TableVO> tempList = accountService.getAccountAllTable(account_id.toUpperCase());
		List<String> tableList = new ArrayList<String>();
		
		for(TableVO tVO : tempList) {
			tableList.add(tVO.getTable_name());
		}
		
		return tableList;
	}
	
	@RequestMapping(path =  "/getColumns", method = RequestMethod.GET)
	@ResponseBody
	public List<String> getColumns(String tableName, String account_id, HttpSession session) {
		logger.debug("tableName : {}", tableName);
		logger.debug("account_id : {}", account_id);
		AccountVO accountVO = accountService.getAccountOne(account_id);
		Connection conn = DBUtilForWorksheet.getConnection(account_id, accountVO.getAccount_pw(), session);
		
		List<String> columnList = sqlEditorTableService.getColumns(tableName, conn);
		
		return columnList;
	}
	
	/**
	* Method : appendDataAjax
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 : ajax를 통해 DataTypeUtil 클래스를 호출하여 json으로 전송
	*/
	@RequestMapping("/appendDataAjax")
	public String appendDataAjax(Model model) {
		List<String> dataTypeList = DataTypeUtil.tableDataType();
		model.addAttribute("dataTypeList", dataTypeList);
		return "jsonView";
	}
	
	/**
	* Method : createTable
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 : 테이블 생성
	*/
	@ResponseBody
	@RequestMapping("/createTable")
	public String createTable(HttpSession session,  Model model,
			@RequestBody String[][] array) {
		
		sqlEditorTableService.createTable(array);
		return "jsonView";

	}
	
	/**
	* Method : selectTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param account_id
	* @param select
	* @param model
	* @param session
	* @return
	* Method 설명 : 테이블 조회
	*/
	@RequestMapping(path = "/selectTable", method = RequestMethod.GET)
	public String selectTable(String account_id, String select, String TableName, Model model, HttpSession session) {
		
		AccountVO accountVO = accountService.getAccountOne(account_id);
		Connection conn = DBUtilForWorksheet.getConnection(account_id, accountVO.getAccount_pw(), session);
		
		List<List<String>> resultList = sqlEditorTableService.selectTable(select, TableName, conn);
		
		model.addAttribute("resultList", resultList);
		
		return "jsonView";
	}
	

	/**
	* Method : updateTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param TableName
	* @param account_id
	* @param select
	* @param model
	* @param session
	* @return
	* Method 설명 : 테이블 편집
	*/
	@RequestMapping(path = "/updateTable", method = RequestMethod.GET)
	public String updateTable(String TableName, String account_id, String select, Model model, HttpSession session) {
		logger.debug("account_id ==> [{}]", account_id);
		logger.debug("TableName ==> [{}]", TableName);
		logger.debug("select ==> [{}]", select);
		AccountVO accountVO = accountService.getAccountOne(account_id);
		Connection conn = DBUtilForWorksheet.getConnection(account_id, accountVO.getAccount_pw(), session);
		// 데이터 타입 받는 리스트 추가해야함
		Map<String, Object> updateTableMap = sqlEditorTableService.updateTable(select, TableName, conn);
		List<String> dataTypeList =  (List<String>) updateTableMap.get("dataTypeList");
		
		model.addAttribute("dataTypeList", dataTypeList);
		logger.debug("conn ===>{}", conn);
		String html = (String) updateTableMap.get("html");
		model.addAttribute("columnDataList", (List<SqlEditorTableVO>)updateTableMap.get("columnDataList"));
		logger.debug("dataList ==>[{}]", (List<SqlEditorTableVO>)updateTableMap.get("columnDataList"));
		return html;
	}
	
	@ResponseBody
	@RequestMapping(path = "/updateTable", method = RequestMethod.POST)
	public String updateTable(HttpSession session,  Model model,
			@RequestBody String[][] array) {
		
		sqlEditorTableService.updateTable(array);
		return "jsonView";

	}
	
	@RequestMapping(path = "/createTriggerReady", method = RequestMethod.POST)
	@ResponseBody
	public String createTriggerReady(MyTriggerVO triggerVO, HttpSession session) {
		logger.debug("triggerVO : {}", triggerVO);
		String query = new TriggerUtil().getCreateTriggerSql(triggerVO);
		logger.debug("query : {}", query);
		return query;
	}
	

	@RequestMapping(path = "/readTrigger", method = RequestMethod.POST)
	@ResponseBody
	public String readTrigger(String triggerName, String accountId, HttpSession session) {
		AccountVO accountVO = accountService.getAccountOne(accountId);
		Connection conn = DBUtilForWorksheet.getConnection(accountId, accountVO.getAccount_pw(), session);
		
		logger.debug("triggerName : {}", triggerName);
		logger.debug("accountId : {}", accountId.toUpperCase());
		Map<String, String> map = new HashMap<String, String>();
		map.put("accountId", accountId.toUpperCase());
		map.put("triggerName", triggerName.toUpperCase());
		List<MyTriggerCodeVO> list = sqlEditorTriggerService.getTriggerCode(map, conn);
		logger.debug("list : {}", list);
		if(list.size() > 1) {
			return "";
		}else {
			return "CREATE OR REPLACE TRIGGER " + list.get(0).getDescription() + list.get(0).getTrigger_body();
		}
	}
	
	@RequestMapping(path = "/triggerDetail", method = RequestMethod.POST)
	@ResponseBody
	public TriggerDetailVO triggerDetail(String triggerName, String accountId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("object_name", triggerName.trim().toUpperCase());
		map.put("owner", accountId.trim().toUpperCase());
		List<TriggerDetailVO> list = sqlEditorTriggerService.triggerDetail(map);
		if(list.size() > 1)
			return null;
		else
			return list.get(0);
	}
	
	@RequestMapping(path = "/deleteTrigger", method = RequestMethod.POST)
	@ResponseBody
	public int deleteTrigger(String triggerName, String accountId) {
		String trigger_name = accountId + "." + triggerName;
		int resultCnt = -1;
		resultCnt = sqlEditorTriggerService.deleteTrigger(trigger_name);
		return resultCnt;
	}
	
	// 시퀀스 생성
	@RequestMapping(path = "/createSequence", method = RequestMethod.POST)
	@ResponseBody
	public int createSequence(String query) {
		
		int createSequence = -1;
		createSequence = sqlEditorSequenceService.createSequence(query);
		
	
		return createSequence;
	}
	
	// 시퀀스 쿼리 조회
	@RequestMapping(path = "/readSequenceQuery", method = RequestMethod.POST)
	@ResponseBody
	public String readSequenceQuery(String sequenceOwner, String sequenceName, HttpSession session) {
		
		SelectSeqVO seqVO = new SelectSeqVO(sequenceOwner, sequenceName);
		
		return " ";
		
	}
	
	
	@RequestMapping(path = "/createFunction", method = RequestMethod.POST)
	@ResponseBody
	public String createFunction(String account_id, String function_name, String returnType,
								String[] param_name, String[] param_mode, String[] param_type, String[] param_default) {
		
		String query = new FunctionUtil().getCreateFunctionSql
								(function_name, returnType, param_name, param_mode, param_type, param_default);
		
		return query;
	}
	
	@RequestMapping(path = "/readFunction", method = RequestMethod.POST)
	@ResponseBody
	public String readFunction(String functionName, String accountId, HttpSession session) {
		AccountVO accountVO = accountService.getAccountOne(accountId);
		Connection conn = DBUtilForWorksheet.getConnection(accountId, accountVO.getAccount_pw(), session);
		
		logger.debug("functionName : {}", functionName);
		logger.debug("accountId : {}", accountId.toUpperCase());
		List<String> list = sqlEditorFunctionService.getFunctionCode(functionName, conn);
		logger.debug("list : {}", list);
		
		String result = "";
		if(list != null) {
			for(String temp : list)
				result += temp + "\n";
		}
		return result;
	}
	
	@RequestMapping(path = "/fucntionDetail", method = RequestMethod.POST)
	@ResponseBody
	public FunctionDetailVO fucntionDetail(String functionName, String accountId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("object_name", functionName.trim().toUpperCase());
		map.put("owner", accountId.trim().toUpperCase());
		
		List<FunctionDetailVO> list = sqlEditorFunctionService.fucntionDetail(map);
		
		return list.get(0);
	}
	
//	@RequestMapping(path = "/deleteFunction", method = RequestMethod.POST)
//	@ResponseBody
//	public int deleteFunction(String functionName, String accountId) {
//		String function_name = accountId.toUpperCase() + "." + functionName.toUpperCase();
//		int resultCnt = -1;
//		resultCnt = sqlEditorTriggerService.deleteTrigger(trigger_name);
//		return resultCnt;
//	}
	
}
