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

/**
* BlackListController.java
*
* @author 이중석
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* 이중석 최초 생성
*
* </pre>
*/
@RequestMapping("/blackList")
@Controller
public class BlackListController {
	
	@Resource(name = "blackListService")
	private IBlackListService blackListService;
	
	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	* Method : insertBlackList
	* 작성자 : 이중석
	* 변경이력 :
	* @param blackListVo
	* @param model
	* @param session
	* @return
	* Method 설명 : 블랙리스트 등록
	*/
	@RequestMapping("/insertBlackList")
	public String insertBlackList(BlackListVO blackListVo, Model model, HttpSession session) {	
		
		if (userService.getUser(blackListVo.getUser_id_fk()) == null) {
			model.addAttribute("user_id", null);
			return "jsonView";
		}else {
			model.addAttribute("user_id", blackListVo.getUser_id_fk());
		}
		String reg_user_id_fk = ((UserVO)session.getAttribute("USER_INFO")).getUser_id();

		blackListVo.setReg_user_id_fk(reg_user_id_fk);
		blackListService.insertBlackList(blackListVo);
		return "jsonView";
	}
	
	/**
	* Method : deleteBlackListMG
	* 작성자 : 이중석
	* 변경이력 :
	* @param deleteCheck
	* @param model
	* @param session
	* @return
	* Method 설명 : 블랙리스트 해제
	*/
	@RequestMapping("/deleteBlackList")
	public String deleteBlackListMG(String[] deleteCheck, Model model, HttpSession session) {
		
		String unlockUser = ((UserVO)session.getAttribute("USER_INFO")).getUser_id();
		Map<String, Object> userMap = new HashMap<String, Object>();
		
		for (int i = 0; i < deleteCheck.length; i++) {
			userMap.put("unlockUser", unlockUser);
			userMap.put("black_id", deleteCheck[i]);
			blackListService.deleteBlackListMG(userMap);
		}
		return blackListManagerGet();
	}
	
	
	/**
	 * Method : blackListManagerGet
	 * 작성자 : 이중석
	 * 변경이력 :
	 * @return
	 * Method 설명 : 블랙리스트 메인 요청 화면
	 */
	@RequestMapping(path =  "/blackListManager", method = RequestMethod.GET)
	public String blackListManagerGet() {
		return "/admin/blackListMG/blackListMGMain.tiles";
	}
	
	/**
	* Method : blackListManagerPost
	* 작성자 : 이중석
	* 변경이력 :
	* @param search
	* @param pageVo
	* @param model
	* @return
	* Method 설명 : 블랙리스트 메인 페이징 처리 응답 화면
	*/
	@RequestMapping(path =  "/blackListManager", method = RequestMethod.POST)
	public String blackListManagerPost(@RequestParam(name = "searchfor", defaultValue = "") String search,
			PageVo pageVo, Model model) {
		
		Map<String, Object> pageMap = new HashMap<String, Object>();
		
		pageMap.put("search", search);
		pageMap.put("page", pageVo.getPage());
		pageMap.put("pageSize", pageVo.getPageSize());
		
		Map<String, Object> resultMap = blackListService.blackList(pageMap);
		List<BlackListVO>blackList = (List<BlackListVO>) resultMap.get("blackList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		 int startPage = ((int)Math.floor((pageVo.getPage()-1)/10)) + 1;
	      if(pageVo.getPage()==1) {
	         startPage =1;
	      }
	      
	      if(startPage>=2) {
	            startPage =((int)Math.floor((pageVo.getPage()-1)/10)*10) + 1;
	        }
	      
	       paginationSize = ((int)Math.floor((pageVo.getPage()-1)/10 + 1))*10;
	         
	         int lastpaginationSize= (int) resultMap.get("paginationSize");
	         
	         if(((int)Math.floor((pageVo.getPage()-1)/10 + 1))*10>lastpaginationSize) {
	            paginationSize= lastpaginationSize;
	         }
		model.addAttribute("lastpaginationSize", lastpaginationSize);
		model.addAttribute("startPage", startPage);
		model.addAttribute("blackList", blackList);
		model.addAttribute("pageMap", pageMap);
		model.addAttribute("paginationSize", paginationSize);
		return "admin/blackListMG/blackListMGAjaxHtml";
	}
	
}
