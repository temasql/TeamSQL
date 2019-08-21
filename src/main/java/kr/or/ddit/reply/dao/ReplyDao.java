package kr.or.ddit.reply.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.reply.model.ReplyVO;

@Repository
public class ReplyDao implements IReplyDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	
	/**
	* Method : replyMaxCnt
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 댓글 전체 수
	*/
	@Override
	public int replyMaxCnt() {
		return sqlSession.selectOne("reply.replyMaxCnt");
	}

	
	/**
	* Method : insertReply
	* 작성자 : 이영은
	* 변경이력 :
	* @param replyVo
	* @return
	* Method 설명 : 댓글 등록
	*/
	@Override
	public int insertReply(ReplyVO replyVo) {
		return sqlSession.insert("reply.insertReply", replyVo);
	}

	
	/**
	* Method : replyList
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 해당 게시글 댓글 리스트 조회
	*/
	@Override
	public List<ReplyVO> replyList(int post_id) {
		return sqlSession.selectList("reply.replyList", post_id);
	}

	
	/**
	* Method : delReplyCnt
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 댓글이 있는 게시글 삭제 시 댓글도 삭제처리(사용여부 변경)
	*/
	@Override
	public int delReplyCnt(int post_id) {
		return sqlSession.update("reply.delReplyCnt", post_id);
	}

	
	/**
	* Method : deleteReply
	* 작성자 : 이영은
	* 변경이력 :
	* @param reply_id
	* @return
	* Method 설명 : 댓글 삭제(사용여부 변경)
	*/
	@Override
	public int deleteReply(int reply_id) {
		return sqlSession.update("reply.deleteReply", reply_id);
	}

	
	/**
	* Method : getReply
	* 작성자 : 이영은
	* 변경이력 :
	* @param reply_id
	* @return
	* Method 설명 : 댓글 하나의 정보
	*/
	@Override
	public ReplyVO getReply(int reply_id) {
		return sqlSession.selectOne("reply.getReply", reply_id);
	}


}
