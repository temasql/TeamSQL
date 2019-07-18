package kr.or.ddit.reply.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.reply.model.ReplyVO;

public interface IReplyService {
	
	int insert(ReplyVO replyVo);
	
	ReplyVO get(String id);
	
	List<ReplyVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}
