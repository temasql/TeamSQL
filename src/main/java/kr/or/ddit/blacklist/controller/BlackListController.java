package kr.or.ddit.blacklist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.blacklist.model.BlackListVO;
import kr.or.ddit.blacklist.service.IBlackListService;
import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

@RequestMapping("/blackList")
@Controller
public class BlackListController {

	
	@Resource(name = "blackListService")
	private IBlackListService blackListService;
	
	@Resource(name = "userService")
	private IUserService userService;
	
	@RequestMapping(path =  "/blackListManager", method = RequestMethod.POST)
	public String blackListManagerPost(@RequestParam(name = "searchfor", defaultValue = "") String search,
			PageVo pageVo, Model model) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("search", search);
//		
//		// 페이지 번호
		pageMap.put("page", pageVo.getPage());
//		
//		// 한 페이지에 출력할 게시글 수
		pageMap.put("pageSize", pageVo.getPageSize());
//		
		Map<String, Object> resultMap = blackListService.blackList(pageMap);
		List<BlackListVO>blackList = (List<BlackListVO>) resultMap.get("blackList");
		int paginationSize = (int) resultMap.get("paginationSize");
//		
		model.addAttribute("blackList", blackList);
		model.addAttribute("pageMap", pageMap);
		model.addAttribute("paginationSize", paginationSize);
		return "admin/blackListMG/blackListMGAjaxHtml";
	}
	
	@RequestMapping(path =  "/blackListManager", method = RequestMethod.GET)
	public String blackListManagerGet() {
		return "/admin/blackListMG/blackListMGMain.tiles";
	}
	
	@RequestMapping("/deleteBlackList")
	public String deleteUserMG(String[] deleteCheck, Model model, HttpSession session) {
		
		String unlockUser = ((UserVO)session.getAttribute("USER_INFO")).getUser_id();
		Map<String, Object> userMap = new HashMap<String, Object>();
		
		for (int i = 0; i < deleteCheck.length; i++) {
			userMap.put("unlockUser", unlockUser);
			userMap.put("black_id", deleteCheck[i]);
			blackListService.deleteBlackListMG(userMap);
		}
		return blackListManagerGet();
	}
	
	@RequestMapping("/insertBlackList")
	public String insertBlackList(BlackListVO blackListVo, Model model, HttpSession session) {	
		if (userService.getUser(blackListVo.getUser_id_fk()) == null) {
			model.addAttribute("user_id", null);
			return "jsonView";
		}else {
			model.addAttribute("user_id", " ");
		}
		String reg_user_id_fk = ((UserVO)session.getAttribute("USER_INFO")).getUser_id();

		blackListVo.setReg_user_id_fk(reg_user_id_fk);
		blackListService.insertBlackList(blackListVo);
		return "jsonView";
	}
	
}
