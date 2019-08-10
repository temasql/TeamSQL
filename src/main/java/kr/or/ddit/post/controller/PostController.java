package kr.or.ddit.post.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	* Method : postList
	* 작성자 : 이영은
	* 변경이력 :
	* @param board_id
	* @param pageVo
	* @param model
	* @return
	* Method 설명 : 게시글 리스트 화면 요청
	*/
	@RequestMapping(path =  "/postList", method = RequestMethod.GET)
	public String postList(int board_id, PageVo pageVo, Model model) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("board_id", board_id);
		map.put("page", pageVo.getPage());
		map.put("pageSize", pageVo.getPageSize());
		
		Map<String, Object> resultMap = postService.postPagingList(map);
		int paginationSize = (int) resultMap.get("paginationSize");
		List<PostVO> postList = (List<PostVO>) resultMap.get("postList");
		
		model.addAttribute("map", map);
		model.addAttribute("postList", postList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("boardVo", boardService.getBoard(board_id));
		
		return "/post/postList.tiles";
	}
	
	
	/**
	* Method : postFormView
	* 작성자 : 이영은
	* 변경이력 :
	* @param user_id_fk
	* @param board_id_fk
	* @param model
	* @return
	* Method 설명 : 게시글 작성 화면 요청
	*/
	@RequestMapping(path = "/postForm", method = RequestMethod.GET)
	public String postFormView(String user_id, int board_id, Model model) {
		model.addAttribute("board_id", board_id);
		model.addAttribute("user_id", user_id);
		
		return "/post/postInsert.tiles";
	}
	
	
	@RequestMapping(path =  "/view", method = RequestMethod.POST)
	public String viewPost() {
		return "";
	}
}
