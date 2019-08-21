package kr.or.ddit.post.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.post.service.IPostService;
import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.ts_file.model.TSFileVO;
import kr.or.ddit.ts_file.service.ITSFileService;
import kr.or.ddit.util.PartUtil;

@RequestMapping("/post")
@Controller
public class PostController {

	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

	@Resource(name="postService")
	private IPostService postService;
	
	@Resource(name="boardService")
	private IBoardService boardService;
	
	@Resource(name="TSFileService")
	private ITSFileService fileService;

	@Resource(name = "replyService")
	private IReplyService replyService;
	
	
	/**
	* Method : boardList
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 게시글 리스트 화면 요청
	*/
	@RequestMapping(path =  "/boardList", method = RequestMethod.GET)
	public String boardList(int board_id, Model model) {
//		logger.debug("boardList get() : [{}]", board_id);
//		model.addAttribute("boardList", boardService.boardList());
		model.addAttribute("board_id", board_id);
		BoardVO boardVo = boardService.getBoard(board_id);
		
		model.addAttribute("boardVo", boardVo);
		
		if(board_id ==1) {
			return "/notice/noticeList.tiles";
		} else {
			return "/board/boardList.tiles";
		}
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
	public String boardList(PageVo pageVo, int board_id, Model model,
			@RequestParam(name = "selectBox", defaultValue = "post_title")String  selectBox
			, @RequestParam(name = "search", defaultValue = "") String search) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("page", pageVo.getPage());
		pageMap.put("pageSize", pageVo.getPageSize());
		pageMap.put("board_id", board_id);
		pageMap.put("select", selectBox);
		pageMap.put("search", search);
		
		logger.debug("boardList post page() : [{}]", pageVo.getPage());
		logger.debug("boardList post pageSize() : [{}]", pageVo.getPageSize());
		logger.debug("boardList post board_id () : [{}]", board_id);
	 	
		
		Map<String, Object> resultMap = postService.postPagingList(pageMap);
		
		for (String key : resultMap.keySet()) {
			logger.debug("resultMap : [{}]", resultMap.get(key));
		}
		
		
		List<PostVO> boardList = (List<PostVO>) resultMap.get("postList");
		logger.debug("boardList Post boardList : {}", boardList);
		
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
		model.addAttribute("board_id", board_id);
		model.addAttribute("postList", boardList);
		model.addAttribute("pageMap", pageMap);
		model.addAttribute("paginationSize", paginationSize);
		

		return "/board/boardListAjaxHtml";

	}
	
	
	/**
	* Method : noticeList
	* 작성자 : 이영은
	* 변경이력 :
	* @param pageVo
	* @param board_id
	* @param model
	* @param selectBox
	* @param search
	* @return
	* Method 설명 : 공지사항 페이징 처리 응답화면
	*/
	@RequestMapping(name = "/noticeList", method = RequestMethod.POST)
	public String noticeList(PageVo pageVo, int board_id, Model model,
			@RequestParam(name = "selectBox", defaultValue = "post_title")String  selectBox
			, @RequestParam(name = "search", defaultValue = "") String search) {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("page", pageVo.getPage());
		pageMap.put("pageSize", pageVo.getPageSize());
		pageMap.put("board_id", board_id);
		pageMap.put("select", selectBox);
		pageMap.put("search", search);
		
		logger.debug("boardList post page() : [{}]", pageVo.getPage());
		logger.debug("boardList post pageSize() : [{}]", pageVo.getPageSize());
		logger.debug("boardList post board_id () : [{}]", board_id);
	 	
		
		Map<String, Object> resultMap = postService.postPagingList(pageMap);
		
		for (String key : resultMap.keySet()) {
			logger.debug("resultMap : [{}]", resultMap.get(key));
		}
		
		
		List<PostVO> boardList = (List<PostVO>) resultMap.get("postList");
		logger.debug("boardList Post boardList : {}", boardList);
		
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
		model.addAttribute("board_id", board_id);
		model.addAttribute("postList", boardList);
		model.addAttribute("pageMap", pageMap);
		model.addAttribute("paginationSize", paginationSize);
		
		return "/notice/noticeListAjaxHtml";
	}
	
	
	/**
	* Method : postForm
	* 작성자 : 이영은
	* 변경이력 :
	* @param board_id
	* @param user_id
	* @param model
	* @return
	* Method 설명 : 게시글 작성 화면 요청
	*/
	@RequestMapping(path = "/postForm", method = RequestMethod.GET)
	public String postForm(int board_id, String user_id, Model model) {
		BoardVO boardVo = boardService.getBoard(board_id);

		model.addAttribute("board_id", board_id);
		model.addAttribute("user_id", user_id);
		model.addAttribute("boardVo", boardVo);
		
		return "/board/boardPostInsert.tiles";
	}
	
	
	/**
	* Method : postForm
	* 작성자 : 이영은
	* 변경이력 :
	* @param board_id
	* @param user_id
	* @param post_title
	* @param smarteditor
	* @param files
	* @param redirectAttributes
	* @return
	* Method 설명 : 게시글 등록
	*/
	@RequestMapping(path = "/postForm", method = RequestMethod.POST)
	public String postForm(int board_id, String user_id, String post_title, String smarteditor,
												MultipartFile[] files, RedirectAttributes redirectAttributes) {
		
		int post_id = postService.postMaxCnt();
		logger.debug("filesSize: {}", files.length);
		PostVO postVo = new PostVO(post_id, user_id, board_id, post_title, smarteditor, post_id);
		int insertPost = postService.insertPost(postVo);
		
		if(insertPost == 1) {
			if(files != null) {
				List<TSFileVO> uploadFileList = new ArrayList<TSFileVO>();
				
				for(MultipartFile file : files) {
					if (file.getSize() > 0) {
						String path = PartUtil.getUploadPath();
						String ext = PartUtil.getExt(file.getOriginalFilename());
						String fileName = UUID.randomUUID().toString();
						String fileId = path + File.separator + fileName + ext;
						
						try {
							file.transferTo(new File(fileId));
						} catch (IllegalStateException | IOException e ) {
							e.printStackTrace();					
						}
						logger.debug("post_id : {}", post_id);
						logger.debug("fileId : {}", fileId);
						logger.debug("fileOFN : {}", file.getOriginalFilename());
						TSFileVO fileVo = new TSFileVO(post_id, fileId, file.getOriginalFilename());
						uploadFileList.add(fileVo);
					}
				}
				fileService.insertFile(uploadFileList);
			}
			redirectAttributes.addAttribute("post_id", post_id);
			return "redirect:/post/readPost";
		}
		return "redirect:/post/postForm";
	}
	
	
	/**
	* Method : readPost
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @param model
	* @return
	* Method 설명 : 게시글 상세 조회
	*/
	@RequestMapping("/readPost")
	public String readPost(int post_id, Model model) {
		
		logger.debug("======= postController readPost =======");
		PostVO postVo = postService.getPost(post_id);
		
		List<TSFileVO> fileList = fileService.getFileList(post_id);
		List<BoardVO> boardList = boardService.boardList();
		
		model.addAttribute("postVo", postVo);
		model.addAttribute("fileList", fileList);
		model.addAttribute("board", boardList);
		
		int board_id = postVo.getBoard_id_fk();
		BoardVO boardVo = boardService.getBoard(board_id);
		
		model.addAttribute("boardVo", boardVo);
		
		List<ReplyVO> replyList = replyService.replyList(post_id);
		model.addAttribute("replyList", replyList);
		
		boardVo = boardService.getBoard(board_id);
		model.addAttribute("boardVo", boardVo);
		

		if(board_id ==1) {
			return "/notice/noticePostDetail.tiles";
		} else {
			return "/board/boardPostDetail.tiles";
		}
		
	}
	
	
	/**
	* Method : fileDownLoad
	* 작성자 : 이영은
	* 변경이력 :
	* @param file_id
	* @param model
	* @return
	* Method 설명 : 첨부 파일 다운로드 뷰
	*/
	@RequestMapping("/fileDownLoad")
	public String fileDownLoad(int file_id, Model model) {
		model.addAttribute("file_id", file_id);
		return "fileDownloadView";
	}
	
	
	/**
	* Method : modifyPost
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @param model
	* @return
	* Method 설명 : 게시글 수정화면 요청
	*/
	@RequestMapping(path = "/modifyPost", method = RequestMethod.GET)
	public String modifyPost(int post_id, Model model) {
		PostVO postVo = new PostVO();
		postVo = postService.getPost(post_id);
		List<TSFileVO> fileList = fileService.getFileList(post_id);
		
		int board_id = postVo.getBoard_id_fk();
		BoardVO boardVo = boardService.getBoard(board_id);
		
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("post_id", post_id);
		model.addAttribute("postVo", postVo);
		model.addAttribute("fileList", fileList);
		
		return "/board/boardPostUpdate.tiles";
	}
	
	
	/**
	* Method : modifyPost
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @param user_id
	* @param post_title
	* @param smarteditor
	* @param delFile
	* @param files
	* @param redirectAttributes
	* @return
	* Method 설명 : 게시글 수정
	*/
	@RequestMapping(path = "/modifyPost", method = RequestMethod.POST)
	public String modifyPost(int post_id, String user_id, String post_title, String smarteditor,
			String[] delFile, MultipartFile[] files, RedirectAttributes redirectAttributes) {
		
		if(delFile != null && delFile.length > 0) {
			fileService.delUpdateFiles(delFile);
		}
		
		PostVO postVo = new PostVO();
		postVo.setUser_id_fk(user_id);
		postVo.setPost_id(post_id);
		postVo.setPost_title(post_title);
		postVo.setPost_content(smarteditor);
		
		int updateCnt = postService.updatePost(postVo);
		
		if(updateCnt == 1) {
			if(files != null) {
				List<TSFileVO> uploadFileList = new ArrayList<TSFileVO>();
				
				for(MultipartFile file : files) {
					String path = PartUtil.getUploadPath();
					String ext = PartUtil.getExt(file.getOriginalFilename());
					String fileName = UUID.randomUUID().toString();
					String fileId = path  + File.separator + fileName + ext;
					
					try {
						file.transferTo(new File(fileId));
					} catch (IllegalStateException | IOException e ) {
						e.printStackTrace();
					}
					
					TSFileVO fileVo = new TSFileVO(post_id, fileId, file.getOriginalFilename());
					uploadFileList.add(fileVo);
				}
				fileService.insertFile(uploadFileList);
			}
			redirectAttributes.addAttribute("post_id", postVo.getPost_id());
			return "redirect:/post/readPost";
		} else {
			redirectAttributes.addAttribute("post_id", postVo.getPost_id());
			return "redirect:/post/modifyPost";
		}
	}

	
	/**
	* Method : answerPostView
	* 작성자 : 이영은
	* 변경이력 :
	* @param user_id
	* @param post_id
	* @param model
	* @return
	* Method 설명 : 답글 작성화면 요청
	*/
	@RequestMapping(path = "/answerPost", method = RequestMethod.GET)
	public String answerPostView(String user_id, int post_id, Model model) {
		PostVO postVo = postService.getPost(post_id);
		
		int board_id = postVo.getBoard_id_fk();
		BoardVO boardVo = boardService.getBoard(board_id);
		
		model.addAttribute("post_id", post_id);
		model.addAttribute("boardVo", boardVo);
		
		return "/board/boardPostAnswer.tiles";
	}
	
	
	/**
	* Method : answerPost
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @param user_id
	* @param post_title
	* @param smarteditor
	* @param files
	* @param redirectAttributes
	* @return
	* Method 설명 : 답글 작성
	*/
	@RequestMapping(path = "/answerPost", method = RequestMethod.POST)
	public String answerPost(int post_id, String user_id, String post_title, String smarteditor,
			MultipartFile[] files, RedirectAttributes redirectAttributes) {
		int parent_id_fk = post_id;
		
		PostVO parentPostVo = postService.getPost(parent_id_fk);
		
		int post_group_seq = parentPostVo.getPost_group_seq();
		int board_id = parentPostVo.getBoard_id_fk();
		post_id = postService.postMaxCnt();
		
		PostVO postVo = new PostVO(post_id, parent_id_fk, user_id, board_id, post_title, smarteditor, "Y", post_group_seq);
		
		int answerPost = postService.answerPost(postVo);
		
		if(answerPost == 1) {
			if(files != null) {
				List<TSFileVO> uploadFileList = new ArrayList<TSFileVO>();
				
				for(MultipartFile file : files) {
					String path = PartUtil.getUploadPath();
					String ext = PartUtil.getExt(file.getOriginalFilename());
					String fileName = UUID.randomUUID().toString();
					String fileId = path + File.separator + fileName + ext;
					
					try {
						file.transferTo(new File(fileId));
					} catch (IllegalStateException | IOException e ) {
						e.printStackTrace();					
					}
					logger.debug("post_id : {}", post_id);
					logger.debug("fileId : {}", fileId);
					
					logger.debug("fileOFN : {}", file.getOriginalFilename());
					TSFileVO fileVo = new TSFileVO(post_id, fileId, file.getOriginalFilename());
					uploadFileList.add(fileVo);
					fileService.insertFile(uploadFileList);
				}
			}
			redirectAttributes.addAttribute("post_id", post_id);
			return "redirect:/post/readPost";
		}
		return "redirect:/post/answerPost";
	}
	
	
	/**
	* Method : deletePost
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @param redirectAttributes
	* @return
	* Method 설명 : 게시글 삭제(사용여부 변경)
	*/
	@RequestMapping(path = "/deletePost", method = RequestMethod.GET)
	public String deletePost(int post_id, RedirectAttributes redirectAttributes) {
		PostVO postVo = postService.getPost(post_id);
		int board_id = postVo.getBoard_id_fk();
		
		int deleteCnt = postService.deletePost(post_id);
		
		if(deleteCnt == 1) {
			int delReply = replyService.deleteReply(post_id);
		}
		redirectAttributes.addAttribute("board_id", board_id);
		return "redirect:/post/boardList";
	}
	
	
	
}
