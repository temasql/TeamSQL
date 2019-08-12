package kr.or.ddit.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.crew.service.ICrewService;
import kr.or.ddit.history.model.ChangedVO;
import kr.or.ddit.history.model.HistoryTempVO;
import kr.or.ddit.history.service.IHistoryService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.Crawling;
import kr.or.ddit.util.CrawlingVO;

@Controller
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);

	@Resource(name = "historyService")
	private IHistoryService historyService;
	@Resource(name = "crewService")
	private ICrewService crewService;
	/**
	 * 
	* Method : main
	* 작성자 : 강호길
	* 변경이력 :
	* @return
	* Method 설명 : 메인 페이지
	 */
//	@RequestMapping(path = "/main", method = RequestMethod.GET)
//	public String main(HttpSession session, Model model) {
//		// 유저아이디
//		String user_id_fk = ((UserVO) session.getAttribute("USER_INFO")).getUser_id();
//		
//		// 메인페이지내 DB변경이력 리스트를 출력
//		List<ChangedVO> changedMainList = historyService.changedMainList(user_id_fk);
//		
//		// DB변경이력
//		model.addAttribute("changedMainList", changedMainList);
//		
//		model.addAttribute("crewMap", crewService.getAccountCrew(user_id_fk));
//		// IT뉴스
//		List<List<CrawlingVO>> itNewsList = new Crawling().getITNews();
//		model.addAttribute("itNewsList", itNewsList);
//		
//		return "main.tiles";
//	}
	
	@RequestMapping(path = "/main", method = RequestMethod.GET)
	public String main(HttpSession session, Model model) {
		// 유저아이디
		String user_id = ((UserVO) session.getAttribute("USER_INFO")).getUser_id();
		
		List<String> accountList = historyService.getAccountIdList(user_id);
		
		List<HistoryTempVO> historyTempList = new ArrayList<HistoryTempVO>();
		
		for (int i = 0; i < accountList.size(); i++) {
			HistoryTempVO historyTempVO = historyService.getLastDateAndName(accountList.get(i));
			historyTempList.add(historyTempVO);
		}
		
		List<String> accoList = new ArrayList<String>();
		
		for (int i = 0; i < accountList.size(); i++) {
			String account_id_underBar = accountList.get(i);
			int underBarIdx = account_id_underBar.lastIndexOf("_");
			String account_id = account_id_underBar.substring(0, underBarIdx);
			accoList.add(account_id);
		}
		
		for (int i = 0; i < historyTempList.size(); i++) {
			historyTempList.get(i).setAccount_id(accountList.get(i));
			historyTempList.get(i).setSlice_account_id(accoList.get(i));
		}
		
		if(historyTempList.size() > 4) {
			for (int i = 5; i < historyTempList.size(); i++) {
				historyTempList.remove(i);
			}
		}
		
		model.addAttribute("historyTempList", historyTempList);
		
		model.addAttribute("crewMap", crewService.getAccountCrew(user_id));
		
		// IT뉴스
		List<List<CrawlingVO>> itNewsList = new Crawling().getITNews();
		model.addAttribute("itNewsList", itNewsList);
		
		
		return "main.tiles";
	}
	
}
