package kr.or.ddit.chat.team_chat.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.chat.team_chat.model.TeamChatVO;

//@Repository
public class TeamChatDao implements ITeamChatDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(TeamChatVO teamChatVo) {
		return sqlSession.insert("teamChat.insert", teamChatVo);
	}
	
	@Override
	public TeamChatVO get(String id) {
		return sqlSession.selectOne("teamChat.get", id);
	}
	
	@Override
	public List<TeamChatVO> list() {
		return sqlSession.selectList("teamChat.list");
	}

	@Override
	public List<TeamChatVO> map(Map<String, Object> map) {
		return sqlSession.selectList("teamChat.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("teamChat.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("teamChat.delete", id);
	}

	

}
