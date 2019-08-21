package kr.or.ddit.reply.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;
import kr.or.ddit.user.model.UserVO;

@RequestMapping("/reply")
@Controller
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Resource(name="replyService")
	private IReplyService replyService;
	
	
	/**
	* Method : addReply
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @param reply_content
	* @param session
	* @param model
	* @param redirectAttributes
	* @return
	* Method 설명 : 댓글 추가
	*/
	@RequestMapping(path = "/addReply", method = RequestMethod.POST)
	public String addReply(int post_id, String reply_content, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
		UserVO userVo = (UserVO) session.getAttribute("USER_INFO");
		String user_id = userVo.getUser_id();
		
		ReplyVO replyVo = new ReplyVO(replyService.replyMaxCnt(), user_id, post_id, reply_content, "Y");
		
		int insertReply = replyService.insertReply(replyVo);
		
		if(insertReply == 1) {
			List<ReplyVO> replyList = replyService.replyList(post_id);
			model.addAttribute("replyList", replyList);
		}
		redirectAttributes.addAttribute("post_id", post_id);
		return "redirect:/post/readPost";
	}
	
	
	/**
	* Method : deleteReply
	* 작성자 : 이영은
	* 변경이력 :
	* @param reply_id
	* @param redirectAttributes
	* @return
	* Method 설명 : 댓글 삭제
	*/
	@RequestMapping(path =  "/deleteReply", method = RequestMethod.GET)
	public String deleteReply(int reply_id, RedirectAttributes redirectAttributes) {
		
		logger.debug("deleteReply reply_id : {}", reply_id);
		
		ReplyVO replyVo = replyService.getReply(reply_id);
		int post_id = replyVo.getPost_id_fk();
		
		int deleteReply = replyService.deleteReply(reply_id);
		
		redirectAttributes.addAttribute("post_id", post_id);
		return "redirect:/post/readPost";
	}
	
	@RequestMapping(path =  "/view", method = RequestMethod.POST)
	public String viewPost() {
		return "";
	}
}
