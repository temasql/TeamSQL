package kr.or.ddit.main.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.history.model.ChangedVO;
import kr.or.ddit.history.service.IHistoryService;
import kr.or.ddit.user.model.UserVO;

@Controller
public class MainController {

	@Resource(name = "historyService")
	private IHistoryService historyService;
	
	/**
	 * 
	* Method : main
	* 작성자 : 강호길
	* 변경이력 :
	* @return
	* Method 설명 : 메인 페이지
	 */
	@RequestMapping(path = "/main", method = RequestMethod.GET)
	public String main(HttpSession session, Model model) {
		// 유저아이디
		String user_id_fk = ((UserVO) session.getAttribute("USER_INFO")).getUser_id();
		
		// 메인페이지내 DB변경이력 리스트를 출력
		List<ChangedVO> changedMainList = historyService.changedMainList(user_id_fk);
		
		// DB변경이력
		model.addAttribute("changedMainList", changedMainList);
		return "main.tiles";
	}
	
}
