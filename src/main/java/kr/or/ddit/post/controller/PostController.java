package kr.or.ddit.post.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;

@RequestMapping("/post")
@Controller
public class PostController {

	@Resource(name="postService")
	private IPostService postService;
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	
	/**
	* Method : boardList
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 게시글 리스트 요청 화면
	*/
	@RequestMapping(path =  "/boardList", method = RequestMethod.GET)
	public String boardList(int board_id, Model model) {
		model.addAttribute("board_id", board_id);
		return "/board/boardList.tiles";
	}
	
	
	/**
	* Method : boardList
	* 작성자 : 이영은
	* 변경이력 :
	* @param pageVo
	* @param model
	* @return
	* Method 설명 : 게시글 페이징 처리 응답 화면
	*/
	@RequestMapping(path = "/boardList", method = RequestMethod.POST)
	public String boardList(PageVo pageVo, int board_id,Model model) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("page", pageVo.getPage());
		pageMap.put("pageSize", pageVo.getPageSize());
		pageMap.put("board_id", board_id);
		
		Map<String, Object> resultMap = postService.postPagingList(pageMap);
		
		List<PostVO> boardList = (List<PostVO>) resultMap.get("postList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageMap", pageMap);
		model.addAttribute("paginationSize", paginationSize);
		
		return "board/boardListAjaxHtml";
	}

}
