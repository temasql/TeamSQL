package kr.or.ddit.reply.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.reply.model.ReplyVO;

//@Repository
public class ReplyDao implements IReplyDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(ReplyVO replyVo) {
		return sqlSession.insert("reply.insert", replyVo);
	}
	
	@Override
	public ReplyVO get(String id) {
		return sqlSession.selectOne("reply.get", id);
	}
	
	@Override
	public List<ReplyVO> list() {
		return sqlSession.selectList("reply.list");
	}

	@Override
	public List<ReplyVO> map(Map<String, Object> map) {
		return sqlSession.selectList("reply.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("reply.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("reply.delete", id);
	}

	

}
