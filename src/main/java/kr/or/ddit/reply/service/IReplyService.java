package kr.or.ddit.reply.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.reply.model.ReplyVO;

public interface IReplyService {
	
	/**
	* Method : replyMaxCnt
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 댓글 전체 수 
	*/
	int replyMaxCnt();
	
	
	/**
	* Method : insertReply
	* 작성자 : 이영은
	* 변경이력 :
	* @param replyVo
	* @return
	* Method 설명 : 댓글 등록
	*/
	int insertReply(ReplyVO replyVo);
	
	
	/**
	* Method : replyList
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 해당 게시글 댓글 리스트 조회
	*/
	List<ReplyVO> replyList(int post_id);
	
	
	/**
	* Method : delReplyCnt
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 댓글이 있는 게시글 삭제 시 댓글도 삭제 처리(사용여부 변경)
	*/
	int delReplyCnt(int post_id);
	
	
	/**
	* Method : deleteReply
	* 작성자 : 이영은
	* 변경이력 :
	* @param reply_id
	* @return
	* Method 설명 : 댓글 삭제(사용여부 변경)
	*/
	int deleteReply(int reply_id);
	
	
	/**
	* Method : getReply
	* 작성자 : 이영은
	* 변경이력 :
	* @param reply_id
	* @return
	* Method 설명 : 댓글 하나의 정보
	*/
	ReplyVO getReply(int reply_id);

}
