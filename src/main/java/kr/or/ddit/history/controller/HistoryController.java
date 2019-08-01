package kr.or.ddit.history.controller;

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

import kr.or.ddit.history.model.HistoryVO;
import kr.or.ddit.history.service.IHistoryService;
import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.user.model.UserVO;

@RequestMapping("/history")
@Controller
public class HistoryController {
	private static final Logger logger = LoggerFactory.getLogger(HistoryController.class);
	
	@Resource(name = "historyService")
	private IHistoryService historyService;
	
	
	/**
	 * 
	* Method : dbChanged
	* 작성자 : 강호길
	* 변경이력 :
	* @param hVo
	* @param session
	* @param pageVo
	* @param model
	* @return
	* Method 설명 : DB변경 메인 페이지
	 */
	@RequestMapping(path = "/historyList", method = RequestMethod.POST)
	public String dbChanged(HttpSession session,PageVo pageVo, Model model) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		
		// 회원 아이디
		String user_id= ((UserVO) session.getAttribute("USER_INFO")).getUser_id();
		pageMap.put("user_id", user_id);
		
		// 페이지 번호
		pageMap.put("page", pageVo.getPage());
		
		// 한 페이지에 출력할 게시글 수
		pageMap.put("pageSize", pageVo.getPageSize());
		
		// 페이징리스트 담기
		Map<String, Object> resultMap = historyService.changedPagingList(pageMap);
		
		// 페이지네이션
		List<HistoryVO> changedPagingList = (List<HistoryVO>) resultMap.get("changedPagingList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		// 파라미터 보내기
		model.addAttribute("user_id",user_id);
		model.addAttribute("changedPagingList", changedPagingList);
		model.addAttribute("pageMap",pageMap);
		model.addAttribute("paginationSize",paginationSize);
		
		return "history/historyListAjaxHtml";
	}
	
	/**
	 * 
	* Method : dbChangedGet
	* 작성자 : 강호길
	* 변경이력 :
	* @return
	* Method 설명 : DB변경 메인 페이지
	 */
	@RequestMapping(path =  "/historyList", method= RequestMethod.GET)
	public String dbChangedView() {
		
		return "/history/historyList.tiles";
	}
	
	
	
	/**
	 * 
	* Method : dbChangedDetail
	* 작성자 : 강호길
	* 변경이력 :
	* @return
	* Method 설명 : DB변경 상세 이력 페이지
	 */
	@RequestMapping(path = "/historyDetail",method = RequestMethod.POST)
	public String dbChangedDetail(String object_owner,PageVo pageVo, HistoryVO hVo, Model model) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		
		// 해당 DB계정
		pageMap.put("object_owner", object_owner);
		logger.debug("오브젝트오너 : {}",object_owner);
		// 페이지 번호 
		pageMap.put("page", pageVo.getPage());
		logger.debug("페이지 : {}", pageVo.getPage());
		// 한 페이지에 출력할 게시글 수
		pageMap.put("pageSize", pageVo.getPageSize());
		logger.debug("페이지수 : {}",pageVo.getPageSize());
		// 페이징리스트에 담기
		Map<String, Object> resultMap = historyService.changedDetailPagingList(pageMap);
		
		// 페이지네이션
		List<HistoryVO> changedDetailPagingList = (List<HistoryVO>) resultMap.get("changedDetailPagingList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		// 파라미터 보내기
		model.addAttribute("object_owner",object_owner);
		model.addAttribute("changedDetailPagingList",changedDetailPagingList);
		logger.debug("변경일시 : {}", changedDetailPagingList.get(0).getExec_dtm());
		model.addAttribute("pageMap",pageMap);
		model.addAttribute("paginationSize",paginationSize);
	
		return "history/historyDetailAjaxHtml";
	}
	
	@RequestMapping(path = "/historyDetailView", method = RequestMethod.POST)
	public String dbChangedDetailView(String object_owner, Model model) {
		
		// DB계정명
		model.addAttribute("object_owner", object_owner);
		logger.debug("겟오브젝트오너 : {}",object_owner);
		return "/history/historyDetail.tiles";
		
	}
	
}
