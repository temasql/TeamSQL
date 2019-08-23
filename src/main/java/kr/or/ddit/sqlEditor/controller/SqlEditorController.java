package kr.or.ddit.sqlEditor.controller;

import java.sql.Connection;
import java.sql.SQLException;
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
import kr.or.ddit.sqlEdiotSequence.model.DetailSeqVO;
import kr.or.ddit.sqlEdiotSequence.model.SelectSeqVO;
import kr.or.ddit.sqlEdiotSequence.service.ISqlEditorSequenceService;
import kr.or.ddit.sqlEdiotTable.service.ISqlEditorTableService;
import kr.or.ddit.sqlEdiotView.service.ISqlEditorViewService;
import kr.or.ddit.sqlEditor.service.ISqlEditorService;
import kr.or.ddit.sqlEditorFunction.model.FunctionDetailVO;
import kr.or.ddit.sqlEditorFunction.service.ISqlEditorFunctionService;
import kr.or.ddit.sqlEditorIndex.model.IndexColVO;
import kr.or.ddit.sqlEditorIndex.model.IndexDetailVO;
import kr.or.ddit.sqlEditorIndex.service.ISqlEditorIndexService;
import kr.or.ddit.sqlEditorProcedure.model.ProcedureDetailVO;
import kr.or.ddit.sqlEditorProcedure.service.ISqlEditorProcedureService;
import kr.or.ddit.sqlEditorTrigger.model.MyTriggerCodeVO;
import kr.or.ddit.sqlEditorTrigger.model.MyTriggerVO;
import kr.or.ddit.sqlEditorTrigger.model.TriggerDetailVO;
import kr.or.ddit.sqlEditorTrigger.service.ISqlEditorTriggerService;
import kr.or.ddit.testData.service.ITestDataService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.DBUtilForWorksheet;
import kr.or.ddit.util.DataTypeUtil;
import kr.or.ddit.util.FindAccountPwByMail;
import kr.or.ddit.util.FunctionUtil;
import kr.or.ddit.util.IndexUtil;
import kr.or.ddit.util.ProcedureUtil;
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
	
	@Resource(name = "sqlEditorProcedureService")
	private ISqlEditorProcedureService sqlEditorProcedureService;
	
	@Resource(name = "sqlEditorViewService")
	private ISqlEditorViewService sqlEditorViewService;
	
	@Resource(name = "testDataService")
	private ITestDataService testDataService;
	
	@Resource(name = "sqlEditorIndexService")
	private ISqlEditorIndexService sqlEditorIndexService;
	
	@RequestMapping(path =  "/sqlEditorMain", method = RequestMethod.GET)
	public String sqlEditorMain(HttpSession session, Model model) {
		
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
		model.addAttribute("accountListSize", myAccountList.size());
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
				accountService.grantCreateView(real_account_id);
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
		Connection conn = (Connection) session.getAttribute("conn");
		try {
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.removeAttribute("conn");
		
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
	* Method 설명 : 테이블 생성 json 형식의 2차원 배열을 받아서 처리
	*/
	@ResponseBody
	@RequestMapping("/createTable")
	public String createTable(HttpSession session,  Model model,
			@RequestBody String[][] array) {
		for (String[] strings : array) {
			logger.debug("array : {}", strings);
			
		}
		// json으로 받은 2차원 배열을 매개변수로 전달
		sqlEditorTableService.createTable(array, "create");
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
		
		logger.debug("account_id : {}", account_id);
		AccountVO accountVO = accountService.getAccountOne(account_id);
		logger.debug("accountVO : {}", accountVO);
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
	* Method 설명 : 테이블 편집 요청
	*/
	@RequestMapping(path = "/updateTable", method = RequestMethod.GET)
	public String updateTable(String TableName, String account_id, String select, Model model, HttpSession session) {
		AccountVO accountVO = accountService.getAccountOne(account_id);
		Connection conn = DBUtilForWorksheet.getConnection(account_id, accountVO.getAccount_pw(), session);
		
		model.addAttribute("dataTypeList", DataTypeUtil.tableDataType());
		model.addAttribute("columnDataList", sqlEditorTableService.updateTable(TableName, conn));
		return "sqlEditor/ajaxHtml/updateTableAjaxHtml";
	}
	
	/**
	* Method : updateTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param session
	* @param model
	* @param array
	* @return
	* Method 설명 : 테이블 편집 응답
	*/
	@ResponseBody
	@RequestMapping(path = "/updateTable", method = RequestMethod.POST)
	public String updateTable(HttpSession session,  Model model,
			@RequestBody String[][] array) {
		sqlEditorTableService.updateTable(array, "update");
		return "jsonView";
	}
	
	/**
	* Method : deleteTable
	* 작성자 : 이중석
	* 변경이력 :
	* @param account_id
	* @param tableName
	* @param session
	* @param model
	* @return
	* Method 설명 : 테이블 삭제
	*/
	@RequestMapping("/deleteTable")
	public String deleteTable(String account_id, String tableName, HttpSession session, Model model) {
		sqlEditorTableService.deleteTable(account_id, tableName);
		return sqlEditorMain(session, model);
	}
	
	/**
	* Method : tableExport
	* 작성자 : 이중석
	* 변경이력 :
	* @param tableName
	* @param account_id
	* @param session
	* @param model
	* @return
	* Method 설명 : 테이블 export
	*/
	@RequestMapping("/tableExport")
	public String tableExport(String tableName, String account_id, HttpSession session
			, Model model, String[] exportChecked) {
		String data = sqlEditorTableService.tableExport(tableName, account_id, session, exportChecked);
		model.addAttribute("data", data);
		return "jsonView";
	}
	
	
	/**
	* Method : createVO
	* 작성자 : 이영은
	* 변경이력 :
	* @param tableName
	* @param account_id
	* @param session
	* @param model
	* @return
	* Method 설명 : 자바 모델 생성
	*/
	@RequestMapping("/createVO")
	public String createVO(String tableName, String account_id, HttpSession session, Model model) {
		AccountVO accountVO = accountService.getAccountOne(account_id);
		Connection conn = DBUtilForWorksheet.getConnection(account_id, accountVO.getAccount_pw(), session);
		String data =  sqlEditorTableService.createVO(tableName, conn);
		model.addAttribute("data", data);
		return "jsonView";
	}
	
	
	/**
	* Method : createView
	* 작성자 : 이중석
	* 변경이력 :
	* @param session
	* @param model
	* @param array
	* @return
	* Method 설명 : 뷰 생성
	*/
	@RequestMapping("/createView")
	public String createView(String sc_id, String view_name, String viewQuery
			,HttpSession session, Model model) {
		AccountVO accountVO = accountService.getAccountOne(sc_id);
		Connection conn = DBUtilForWorksheet.getConnection(sc_id, accountVO.getAccount_pw(), session);
		sqlEditorViewService.createView(view_name, viewQuery, conn);
		
		return sqlEditorMain(session, model);
	}
	
	/**
	* Method : deleteView
	* 작성자 : 이중석
	* 변경이력 :
	* @param sc_id
	* @param view_name
	* @param session
	* @param model
	* @return
	* Method 설명 : 뷰삭제
	*/
	@RequestMapping("/deleteView")
	public String deleteView(String sc_id, String view_name,HttpSession session, Model model) {
		AccountVO accountVO = accountService.getAccountOne(sc_id.trim());
		Connection conn = DBUtilForWorksheet.getConnection(sc_id.trim(), accountVO.getAccount_pw(), session);
		
		sqlEditorViewService.deleteView(view_name, conn);
		
		return sqlEditorMain(session, model);
	}
	
	/**
	* Method : updateView
	* 작성자 : 이중석
	* 변경이력 :
	* @param view_name
	* @param sc_id
	* @param session
	* @param model
	* @return
	* Method 설명 : 뷰 편집 요청
	*/
	@RequestMapping("/updateViewTA")
	public String updateViewTA(String view_name, String sc_id, HttpSession session, Model model) {
		AccountVO accountVO = accountService.getAccountOne(sc_id.trim());
		Connection conn = DBUtilForWorksheet.getConnection(sc_id.trim(), accountVO.getAccount_pw(), session);
		
		String query = sqlEditorViewService.updateView(view_name, conn);
		model.addAttribute("data", query);
		return "jsonView";
	}
	
	/**
	* Method : updateView
	* 작성자 : 이중석
	* 변경이력 :
	* @param view_name
	* @param sc_id
	* @param session
	* @param model
	* @return
	* Method 설명 : 뷰 편집 응답
	*/
	@RequestMapping("/updateView")
	public String updateView(String oldVN, String view_name, String sc_id, String viewQuery, HttpSession session, Model model) {
		AccountVO accountVO = accountService.getAccountOne(sc_id.trim());
		logger.debug("sc_id b: {}", sc_id);
		Connection conn = DBUtilForWorksheet.getConnection(sc_id.trim(), accountVO.getAccount_pw(), session);
		
		sqlEditorViewService.updateViewPost(oldVN,view_name,viewQuery, conn);
		return sqlEditorMain(session, model);
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
	
	/**
	 * 
	* Method : createSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 시퀀스 생성
	 */
	@RequestMapping(path = "/createSequence", method = RequestMethod.POST)
	@ResponseBody
	public int createSequence(String query) {

		int createSequence = -1;
		createSequence = sqlEditorSequenceService.createSequence(query);

		return createSequence;
	}

	/**
	 * 
	* Method : readSequenceQuery
	* 작성자 : 강호길
	* 변경이력 :
	* @param sequence_owner
	* @param sequence_name
	* @return
	* Method 설명 : 시퀀스 쿼리 조회
	 */
	@RequestMapping(path = "/readSequenceQuery", method = RequestMethod.POST)
	@ResponseBody
	public String readSequenceQuery(String sequence_owner, String sequence_name) {

		// 매개변수
		SelectSeqVO seqVO = new SelectSeqVO(sequence_owner, sequence_name);
		// 조회 쿼리
		String seqQuery = sqlEditorSequenceService.selectSequence(seqVO);
		return seqQuery;

	}

	/**
	 * 
	* Method : readSequenceTable
	* 작성자 : 강호길
	* 변경이력 :
	* @param sequence_name
	* @param sequence_owner
	* @return
	* Method 설명 : 시퀀스 세부 정보 조회
	 */
	@RequestMapping(path = "/readSequenceTable", method = RequestMethod.POST)
	@ResponseBody
	public DetailSeqVO readSequenceTable(String sequence_name, String sequence_owner) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("sequence_owner", sequence_owner);
		map.put("sequence_name", sequence_name);

		// 세부 정보 조회 쿼리
		DetailSeqVO seqTable = sqlEditorSequenceService.selectSequenceTable(map);
		return seqTable;

	}

	/**
	 * 
	* Method : beforeSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param sequence_owner
	* @param sequence_name
	* @return
	* Method 설명 : 시퀀스 편집 뷰
	 */
	@RequestMapping(path = "/beforeSequence", method = RequestMethod.POST)
	@ResponseBody
	public SelectSeqVO beforeSequence(String sequence_owner, String sequence_name) {
		Map<String, String> map = new HashMap<String, String>();

		map.put("sequence_owner", sequence_owner);
		map.put("sequence_name", sequence_name);

		SelectSeqVO seqVO = sqlEditorSequenceService.beforeSequence(map);
		return seqVO;
	}

	/**
	 * 
	* Method : updateSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param query
	* @return
	* Method 설명 : 시퀀스 편집
	 */
	@RequestMapping(path = "/updateSequence", method = RequestMethod.POST)
	@ResponseBody
	public int updateSequence(String owner, String query, HttpSession session) {
		
		logger.debug("시퀀스쿼리빠끄 : {}", query);

		logger.debug("계정빠끄 : {}", owner);
		AccountVO accountVO = accountService.getAccountOne(owner);
		logger.debug("뷔오빠끄 : {}", accountVO.getAccount_pw());
		Connection conn = DBUtilForWorksheet.getConnection(owner, accountVO.getAccount_pw(), session);
		
		int updateSequence = -1;

		updateSequence = sqlEditorSequenceService.updateSequence(query,conn);
		logger.debug("시퀀스성공빠끄 : {}",updateSequence);
		return updateSequence;
	}

	/**
	 * 
	* Method : deleteSequence
	* 작성자 : 강호길
	* 변경이력 :
	* @param sequence_owner
	* @param sequence_name
	* @return
	* Method 설명 : 시퀀스 삭제
	 */
	@RequestMapping(path = "/deleteSequence", method = RequestMethod.POST)
	@ResponseBody
	public int deleteSequence(String sequence_owner, String sequence_name) {
		String query = "\"" + sequence_owner + "\" .\"" + sequence_name + "\"";
		int deleteSequence = -1;
		deleteSequence = sqlEditorSequenceService.deleteSequence(query);
		return deleteSequence;
	}

	/**
	 * 
	* Method : createIndex
	* 작성자 : 강호길
	* 변경이력 :
	* @param param_owner
	* @param param_name
	* @param param_table
	* @param param_indexType
	* @param param_column
	* @param param_order
	* @param session
	* @param model
	* @return
	* Method 설명 : 인덱스 생성
	 */
	@RequestMapping(path = "/createIndex", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public int createIndex(String param_owner, String param_name, String param_table, String param_indexType,
		String[] param_column, String[] param_order, HttpSession session, Model model) {

		AccountVO accountVO = accountService.getAccountOne(param_owner);
		logger.debug("인덱스생성계정 : {}", param_owner);
		Connection conn = DBUtilForWorksheet.getConnection(param_owner, accountVO.getAccount_pw(), session);

		String query = new IndexUtil().createIndex(param_name, param_table, param_indexType, param_column, param_order);
		int createCnt = sqlEditorIndexService.createIndex(query, conn);

		model.addAttribute("ddlQuery", query);
		
		return createCnt;

	}
		
	/**
	 * 
	* Method : updateIndex
	* 작성자 : 강호길
	* 변경이력 :
	* @param update_owner
	* @param update_name
	* @param low_owner
	* @param update_table
	* @param update_indexType
	* @param update_column
	* @param update_order
	* @param session
	* @param model
	* @return
	* Method 설명 : 인덱스 편집 생성
	 */
	@RequestMapping(path = "/updateIndex", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public int updateIndex(String update_owner, String update_name, String low_owner, String update_table, String update_indexType,
		String[] update_column, String[] update_order, HttpSession session, Model model) {
		logger.debug("편집빠끄");
		logger.debug("편집owner : {}", update_owner);
		logger.debug("편집name : {}", update_name);
		logger.debug("편집table : {}", update_table);
		logger.debug("편집type : {}", update_indexType);
		logger.debug("편집column : {}", update_column[0]);
		logger.debug("편집order : {}", update_order[0]);
		logger.debug("편집owner소문자1 : {}", low_owner);
		AccountVO accountVO = accountService.getAccountOne(low_owner);
		logger.debug("권한가진VO : {}", accountVO.getAccount_pw());
		Connection conn = DBUtilForWorksheet.getConnection(low_owner, accountVO.getAccount_pw(), session);
		
		
		String deleteQuery = new IndexUtil().updateIndex(update_name);
		logger.debug("삭제쿼리 : {}",deleteQuery);
		int createCnt = sqlEditorIndexService.updateIndex(deleteQuery, conn);
		String query = new IndexUtil().createIndex(update_name, update_table, update_indexType, update_column, update_order);
		logger.debug("생성쿼리 : {}",query);
		createCnt = sqlEditorIndexService.createIndex(query, conn);
		
		model.addAttribute("ddlQuery", query);
		
		return createCnt;
		
	}
		
	/**
	 * 
	* Method : getTable_name
	* 작성자 : 강호길
	* 변경이력 :
	* @param table_owner
	* @param index_name
	* @return
	* Method 설명 : 인덱스 테이블명 조회
	 */
	@RequestMapping(path = "/getTable_name", method = RequestMethod.POST)
	@ResponseBody
	public List<IndexColVO> getTable_name(String table_owner, String index_name) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("table_owner", table_owner);
		map.put("index_name", index_name);
		
		
		logger.debug("!!ff : {}", table_owner );
		logger.debug("!!ff : {}", index_name );
		
		List<IndexColVO> idxVO = sqlEditorIndexService.beforeIndexOwner(map);
		
		return idxVO;
	}

	/**
	 * 
	* Method : ddlQuery
	* 작성자 : 강호길
	* 변경이력 :
	* @param param_owner
	* @param param_name
	* @param param_table
	* @param param_indexType
	* @param param_column
	* @param param_order
	* @param session
	* @param model
	* @return
	* Method 설명 : 인덱스 DDL 출력
	 */
	@RequestMapping(path = "/ddlQuery", method = RequestMethod.POST)
	@ResponseBody
	public String ddlQuery(String param_owner, String param_name, String param_table, String param_indexType,
			String[] param_column, String[] param_order, HttpSession session, Model model) {

		String query = new IndexUtil().createIndex(param_name, param_table, param_indexType, param_column, param_order);
		
		return query;

	}

	/**
	 * 
	* Method : readIndexQuery
	* 작성자 : 강호길
	* 변경이력 :
	* @param index_owner
	* @param index_name
	* @return
	* Method 설명 : 인덱스 쿼리 조회
	 */
	@RequestMapping(path = "/readIndexQuery", method = RequestMethod.POST)
	@ResponseBody
	public String readIndexQuery(String index_owner, String index_name) {

		// 매개변수
		Map<String, String> map = new HashMap<String, String>();
		map.put("index_owner", index_owner);
		map.put("index_name", index_name);

		// 조회 쿼리
		String idxQuery = sqlEditorIndexService.indexQuery(map);

		return idxQuery;

	}

	/**
	 * 
	* Method : readIndexDetail
	* 작성자 : 강호길
	* 변경이력 :
	* @param index_name
	* @param table_owner
	* @return
	* Method 설명 : 인덱스 세부 정보 조회
	 */
	@RequestMapping(path = "/readIndexTable", method = RequestMethod.POST)
	@ResponseBody
	public IndexDetailVO readIndexDetail(String index_name, String table_owner) {

		// 매개변수
		Map<String, String> map = new HashMap<String, String>();
		map.put("index_name", index_name);
		map.put("table_owner", table_owner);

		// 조회 쿼리
		IndexDetailVO idxVO = sqlEditorIndexService.indexDetail(map);
		logger.debug("세부정보빠끄 : {}", idxVO.getOwner());
		return idxVO;

	}

	/**
	 * 
	* Method : readIndexCol
	* 작성자 : 강호길
	* 변경이력 :
	* @param index_name
	* @param index_owner
	* @return
	* Method 설명 : 인덱스 열 조회
	 */
	@RequestMapping(path = "/readIndexCol", method = RequestMethod.POST)
	@ResponseBody
	public List<IndexColVO> readIndexCol(String index_name, String index_owner) {

		// 매개변수
		Map<String, String> map = new HashMap<String, String>();
		map.put("index_name", index_name);
		map.put("index_owner", index_owner);

		// 조회 쿼리
		List<IndexColVO> idxVO = sqlEditorIndexService.indexCol(map);

		return idxVO;
	}

	/**
	 * 
	* Method : indexDelete
	* 작성자 : 강호길
	* 변경이력 :
	* @param index_owner
	* @param index_name
	* @return
	* Method 설명 : 인덱스 삭제
	 */
	@RequestMapping(path = "/indexDelete", method = RequestMethod.POST)
	@ResponseBody
	public int indexDelete(String index_owner, String index_name) {

		String query = "\"" + index_owner + "\" .\"" + index_name + "\"";

		int deleteCnt = -1;
		deleteCnt = sqlEditorIndexService.indexDelete(query);

		return deleteCnt;
	}

	/**
	 * 
	* Method : indexTable
	* 작성자 : 강호길
	* 변경이력 :
	* @param index_owner
	* @return
	* Method 설명 : 인덱스 테이블 조회
	 */
	@RequestMapping(path = "/indexTable", method = RequestMethod.POST)
	@ResponseBody
	public List<String> indexTable(String index_owner) {

		List<String> idxTable = sqlEditorIndexService.indexTable(index_owner);
		logger.debug("인덱스오너빠끄 : {}", index_owner);
		return idxTable;
	}

	/**
	 * 
	* Method : indexTblCol
	* 작성자 : 강호길
	* 변경이력 :
	* @param owner
	* @param table_name
	* @return
	* Method 설명 : 인덱스 테이블 컬럼 조회
	 */
	@RequestMapping(path = "/indexTblCol", method = RequestMethod.POST)
	@ResponseBody
	public List<String> indexTblCol(String owner, String table_name) {
		logger.debug("빠끄빠끄");
		Map<String, String> map = new HashMap<String, String>();
		map.put("owner", owner);
		logger.debug("오너빠끄 : {}", owner);
		map.put("table_name", table_name);
		logger.debug("테이블네임빠끄 : {}", table_name);
		
		List<String> tableColumn = sqlEditorIndexService.tableColumn(map);
		
		return tableColumn;
		
	}
	
	/**
	 * 
	* Method : beforeIndex
	* 작성자 : 강호길
	* 변경이력 :
	* @param table_owner
	* @param index_name
	* @param table_name
	* @param model
	* @param session
	* @return
	* Method 설명 : 인덱스 편집 뷰
	 */
	@RequestMapping(path = "/beforeIndex", method = RequestMethod.POST)
	public String beforeIndex(String table_owner, String index_name,String table_name, Model model, HttpSession session) {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("table_owner", table_owner);
		map.put("index_name", index_name);
		map.put("owner", table_owner);
		map.put("table_name", table_name);
		logger.debug("테이블계정명 : {}", table_owner);
		
		List<IndexColVO> idxVO = sqlEditorIndexService.beforeIndexOwner(map);
		String tbl_name = sqlEditorIndexService.beforeIndexType(map);
		List<String> col_name = sqlEditorIndexService.tableColumn(map);
		
		model.addAttribute("idxVO", idxVO);
		model.addAttribute("tbl_name", tbl_name);
		model.addAttribute("col_name", col_name);
		return "jsonView";
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
	
	@RequestMapping(path = "/deleteFunction", method = RequestMethod.POST)
	@ResponseBody
	public int deleteFunction(String functionName, String accountId) {
		String function_name = accountId.toUpperCase() + "." + functionName.toUpperCase();
		int resultCnt = -1;
		resultCnt = sqlEditorFunctionService.deleteFunction(function_name);
		return resultCnt;
	}
	
	@RequestMapping(path = "/createProcedure", method = RequestMethod.POST)
	@ResponseBody
	public String createProcedure(String account_id, String procedure_name, String[] param_name2, 
										String[] param_mode2, String[] param_type2, String[] param_default2) {
		
		String query = new ProcedureUtil().
				getCreateProcedureSql(procedure_name, param_name2, param_mode2, param_type2, param_default2);
		
		return query;
	}
	
	@RequestMapping(path = "/readProcedure", method = RequestMethod.POST)
	@ResponseBody
	public String readProcedure(String procedureName, String accountId) {
		logger.debug("procedureName : {}", procedureName);
		logger.debug("accountId : {}", accountId.toUpperCase());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("procedure_name", procedureName.toUpperCase());
		map.put("account_id", accountId.toUpperCase());
		String result = sqlEditorProcedureService.getProcedureCode(map);
		
		return result;
	}
	
	@RequestMapping(path = "/procedureDetail", method = RequestMethod.POST)
	@ResponseBody
	public ProcedureDetailVO procedureDetail(String procedureName, String accountId) {
		logger.debug("pVOdddddddd");
		Map<String, String> map = new HashMap<String, String>();
		map.put("object_name", procedureName.trim().toUpperCase());
		map.put("owner", accountId.trim().toUpperCase());
		
		ProcedureDetailVO pVO = sqlEditorProcedureService.procedureDetail(map);
		logger.debug("pVO : {}", pVO);
		return pVO;
	}
	
	@RequestMapping(path = "/deleteProcedure", method = RequestMethod.POST)
	@ResponseBody
	public int deleteProcedure(String procedureName, String accountId) {
		String procedure_name = accountId.toUpperCase() + "." + procedureName.toUpperCase();
		int resultCnt = -1;
		resultCnt = sqlEditorProcedureService.deleteProcedure(procedure_name);
		return resultCnt;
	}
	
	@RequestMapping("/resultPopup")
	public String resultPopup(String dragText, String account_id, Model model) {
		logger.debug("aasdqwe123123123 : {}", dragText);
		logger.debug("aasdqwe123123123 : {}", account_id);
		model.addAttribute("dragText", dragText);
		model.addAttribute("account_id", account_id);
		return "sqlEditor/resultPopup";
	}
	
	@RequestMapping(path = "/createTestData", method = RequestMethod.POST)
	@ResponseBody
	public List<String> createTestData(String[] column_name, String[] data_type, String[] isNull, 
																String table_name, int dataCnt) {
		List<String> resultList = testDataService.getTestData(column_name, data_type, isNull, table_name, dataCnt);
		logger.debug("resultList : {}", resultList.toString());
		
		return resultList;
	}
	
}
