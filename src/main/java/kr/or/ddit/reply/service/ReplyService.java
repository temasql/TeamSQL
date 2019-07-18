package kr.or.ddit.reply.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.reply.dao.IReplyDao;
import kr.or.ddit.reply.model.ReplyVO;

//@Service
public class ReplyService implements IReplyService{

	@Resource(name = "replyDao")
	private IReplyDao replydao;

	@Override
	public int insert(ReplyVO replyVo) {
		return replydao.insert(replyVo);
	}
	
	@Override
	public ReplyVO get(String id) {
		return replydao.get(id);
	}
	
	@Override
	public List<ReplyVO> list() {
		return replydao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<ReplyVO> mapList =  replydao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return replydao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return replydao.delete(id);
	}

}
