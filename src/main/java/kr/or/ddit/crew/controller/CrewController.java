package kr.or.ddit.crew.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.account.model.AccountVO;
import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.crew.service.ICrewService;
import kr.or.ddit.invite.model.InviteVO;
import kr.or.ddit.invite.service.IInviteService;
import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

@RequestMapping("/crew")
@Controller
public class CrewController {

	@Resource(name = "inviteService")
	private IInviteService inviteService;

	@Resource(name = "crewService")
	private ICrewService crewService;
	
	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	* Method : mainInviteCrew
	* 작성자 : 이중석
	* 변경이력 :
	* @param inviteCheck
	* @param invite
	* @return
	* Method 설명 : 메인화면에서 초대장이 있을시 이동하는 URL
	*/
	@RequestMapping(path =  "/crewMain")
	public String mainInviteCrew(String inviteCheck, InviteVO invite) {
		
		if (inviteCheck.equals("true")) {
			CrewVO crewVo = new CrewVO(invite.getAccount_id_fk(), invite.getUser_id_fk());
			// 크루에 insert하기
			crewService.insertCrew(crewVo);
			
			// 초대장을 지운다.
			inviteService.deleteInvite(invite.getInvite_id());
		} else if(inviteCheck.equals("false")) {
			
			// 초대장을 지운다.
			inviteService.deleteInvite(invite.getInvite_id());
			return "main.tiles";
		}
		
		return "/crew/crewMain.tiles";
	}
	
	/**
	* Method : crewManagerGet
	* 작성자 : 이중석
	* 변경이력 :
	* @param model
	* @param session
	* @return
	* Method 설명 : 구성원 페이지 메인 요청 화면
	*/
	@RequestMapping(path = "/crewManager", method = RequestMethod.GET)
	public String crewManagerGet(Model model, HttpSession session) {
		
		String user_id = ((UserVO)session.getAttribute("USER_INFO")).getUser_id();
		model.addAttribute("crewSelectList", crewService.crewSelectList(user_id));
		String select = "";
		if (crewService.crewSelectList(user_id) != null && crewService.crewSelectList(user_id).size() > 0) {
			select = crewService.crewSelectList(user_id).get(0).getAccount_id_fk();
		}
		model.addAttribute("selected", select);
		return "/crew/crewMain.tiles";
	}
	
	private static final Logger logger = LoggerFactory.getLogger(CrewController.class);
	
	/**
	* Method : crewManager
	* 작성자 : 이중석
	* 변경이력 :
	* @param search
	* @param pageVo
	* @param model
	* @param account_id_fk
	* @param session
	* @return
	* Method 설명 : 구성원 메인 페이징 처리 응답 화면
	*/
	@RequestMapping(path = "/crewManager", method = RequestMethod.POST)
	public String crewManager(@RequestParam(name = "searchfor", defaultValue = "") String search,
			PageVo pageVo, Model model, String account_id_fk, HttpSession session) {

		String user_id = ((UserVO)session.getAttribute("USER_INFO")).getUser_id();
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", search);
		pageMap.put("account_id_fk", account_id_fk);
		pageMap.put("user_id", user_id);
		pageMap.put("page", pageVo.getPage());
		pageMap.put("pageSize", pageVo.getPageSize());

		Map<String, Object> resultMap = crewService.crewList(pageMap);
		List<UserVO>crewList = (List<UserVO>) resultMap.get("crewList");
		int paginationSize = (int) resultMap.get("paginationSize");
		AccountVO accountVo = (AccountVO) resultMap.get("accountVo");
		
		logger.debug("accountVo =============================>>>>>>>>>>>>>>>>>>>>>>>[{}]", accountVo);
		model.addAttribute("crewList", crewList);
		model.addAttribute("pageMap", pageMap);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("accountVo", accountVo);
		return "crew/crewMainAjaxHtml";
	}
	
	
	
	/**
	* Method : inviteCrew
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @param ac_id
	* @param model
	* @return
	* Method 설명 : 구성원 초대 화면
	*/
	@RequestMapping(path = "/inviteCrew", method = RequestMethod.POST)
	public String inviteCrew(String user_id, String ac_id, Model model) {
		CrewVO crewVo = new CrewVO(ac_id, user_id);
		if(crewService.getCrew(crewVo) != null) {
			model.addAttribute("overCrew", "true");
			return "jsonView";
		}
		
		if(userService.getUser(user_id) == null) {
			model.addAttribute("user_id", null);
			return "jsonView";
			
		}
		if(inviteService.insertCrew(crewVo) == 0){
			model.addAttribute("user_id", null);
			return "jsonView";
		}
		model.addAttribute("user_id", " ");
		
		return "jsonView";
	}
	
	
	/**
	* Method : deleteCrew
	* 작성자 : 이중석
	* 변경이력 :
	* @param deleteCheck
	* @param acc_id
	* @param model
	* @param session
	* @return
	* Method 설명 : 구성원 삭제 화면
	*/
	@RequestMapping("/deleteCrew")
	public String deleteUserMG(String[] deleteCheck,String acc_id ,  Model model, HttpSession session) {

		for (int i = 0; i < deleteCheck.length; i++) {
			crewService.deleteCrew(new CrewVO(acc_id, deleteCheck[i]));
		}
		return crewManagerGet(model, session);
	}
	
	
}
