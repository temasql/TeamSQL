package kr.or.ddit.reply.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.reply.model.ReplyVO;

public interface IReplyDao {

	int insert(ReplyVO replyVo);
	
	ReplyVO get(String id);
	
	List<ReplyVO> list();
	
	List<ReplyVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
