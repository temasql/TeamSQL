package kr.or.ddit.crew.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.crew.service.ICrewService;
import kr.or.ddit.invite.model.InviteVO;
import kr.or.ddit.invite.service.IInviteService;

@RequestMapping("/crew")
@Controller
public class CrewController {

	@Resource(name = "inviteService")
	private IInviteService inviteService;

	@Resource(name = "crewService")
	private ICrewService crewService;
	
	@RequestMapping(path =  "/crewMain")
	public String viewGet(String inviteCheck, InviteVO invite) {
		
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
	
	@RequestMapping(path =  "/view", method = RequestMethod.POST)
	public String viewPost() {
		return "";
	}
}
