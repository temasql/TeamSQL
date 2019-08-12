package kr.or.ddit.reply.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.reply.model.ReplyVO;
import kr.or.ddit.reply.service.IReplyService;

@RequestMapping("/reply")
@Controller
public class ReplyController {

	@Resource(name="replyService")
	private IReplyService replyService;
	
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
