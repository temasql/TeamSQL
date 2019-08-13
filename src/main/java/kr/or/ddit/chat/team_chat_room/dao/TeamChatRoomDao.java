package kr.or.ddit.chat.team_chat_room.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.chat.team_chat_room.model.TeamChatRoomVO;

@Repository
public class TeamChatRoomDao implements ITeamChatRoomDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	 * 
	* Method : insertTeamChatRoom
	* 작성자 : 김범휘
	* 변경이력 :
	* @param teamChatRoomVo
	* @return
	* Method 설명 : 채팅방 추가
	 */
	@Override
	public int insertTeamChatRoom(TeamChatRoomVO teamChatRoomVo) {
		return sqlSession.insert("teamChatRoom.insertTeamChatRoom", teamChatRoomVo);
	}
	
	@Override
	public TeamChatRoomVO get(String id) {
		return sqlSession.selectOne("teamChatRoom.get", id);
	}
	
	/**
	* Method : chatRoomList
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : 채팅방 리스트 반환
	*/
	@Override
	public List<TeamChatRoomVO> chatRoomList(String userId) {
		return sqlSession.selectList("teamChatRoom.chatRoomList", userId);
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
