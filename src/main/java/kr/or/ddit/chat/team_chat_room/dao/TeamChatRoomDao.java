package kr.or.ddit.chat.team_chat_room.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.chat.team_chat_room.model.TeamChatRoomVO;

//@Repository
public class TeamChatRoomDao implements ITeamChatRoomDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(TeamChatRoomVO teamChatRoomVo) {
		return sqlSession.insert("teamChatRoom.insert", teamChatRoomVo);
	}
	
	@Override
	public TeamChatRoomVO get(String id) {
		return sqlSession.selectOne("teamChatRoom.get", id);
	}
	
	@Override
	public List<TeamChatRoomVO> list() {
		return sqlSession.selectList("teamChatRoom.list");
	}

	@Override
	public List<TeamChatRoomVO> map(Map<String, Object> map) {
		return sqlSession.selectList("teamChatRoom.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("teamChatRoom.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("teamChatRoom.delete", id);
	}

	

}
