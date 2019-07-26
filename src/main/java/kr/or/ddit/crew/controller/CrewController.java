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

import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.crew.service.ICrewService;
import kr.or.ddit.invite.model.InviteVO;
import kr.or.ddit.invite.service.IInviteService;
import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.user.model.UserVO;

@RequestMapping("/crew")
@Controller
public class CrewController {

	@Resource(name = "inviteService")
	private IInviteService inviteService;

	@Resource(name = "crewService")
	private ICrewService crewService;
	
	@RequestMapping(path =  "/crewMain")
	public String inviteCrew(String inviteCheck, InviteVO invite) {
		
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
	
	@RequestMapping(path = "/crewManager", method = RequestMethod.POST)
	public String crewManager(@RequestParam(name = "searchfor", defaultValue = "") String search,
			PageVo pageVo, Model model, String account_id_fk, HttpSession session) {
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", search);
		
		String user_id = ((UserVO)session.getAttribute("USER_INFO")).getUser_id();
		
		pageMap.put("user_id", user_id);
		pageMap.put("account_id_fk", account_id_fk);
		
		// 페이지 번호
		pageMap.put("page", pageVo.getPage());
		
		// 한 페이지에 출력할 게시글 수
		pageMap.put("pageSize", pageVo.getPageSize());

		Map<String, Object> resultMap = crewService.crewList(pageMap);
		List<UserVO>crewList = (List<UserVO>) resultMap.get("crewList");
		int paginationSize = (int) resultMap.get("paginationSize");

		model.addAttribute("crewList", crewList);
		model.addAttribute("pageMap", pageMap);
		model.addAttribute("paginationSize", paginationSize);
		return "crew/crewMainAjaxHtml";
	}
	private static final Logger logger = LoggerFactory.getLogger(CrewController.class);
	@RequestMapping(path = "/crewManager", method = RequestMethod.GET)
	public String crewManagerGet(Model model, HttpSession session) {

		String user_id = ((UserVO)session.getAttribute("USER_INFO")).getUser_id();
		logger.debug("crewManagerGet user_id ===>>>[{}]", user_id);
		model.addAttribute("crewSelectList", crewService.crewSelectList(user_id));
		String select = "";
		if (crewService.crewSelectList(user_id) != null && crewService.crewSelectList(user_id).size() > 0) {
			select = crewService.crewSelectList(user_id).get(0).getAccount_id_fk();
		}
		logger.debug("crewManagerGet select ===>>>[{}]", select);
		model.addAttribute("selected", select);
		return "/crew/crewMain.tiles";
	}
	
	@RequestMapping(path = "/inviteCrew", method = RequestMethod.POST)
	public String inviteCrew(String user_id, String ac_id, Model model) {
		CrewVO crewVo = new CrewVO(ac_id, user_id);
		if(inviteService.insertCrew(crewVo) == 0){
			model.addAttribute("user_id", null);
			return "jsonView";
		}
		model.addAttribute("user_id", " ");
		
		return "jsonView";
	}
	
	
	@RequestMapping("/deleteCrew")
	public String deleteUserMG(String[] deleteCheck,String acc_id ,  Model model, HttpSession session) {

		for (int i = 0; i < deleteCheck.length; i++) {
			logger.debug("===================================[{}]", acc_id);
			logger.debug("===================================[{}]", deleteCheck[i]);
			crewService.deleteCrew(new CrewVO(acc_id, deleteCheck[i]));
		}
		return crewManagerGet(model, session);
	}
	
	
}
