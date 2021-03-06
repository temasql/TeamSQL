package kr.or.ddit.chat.team_chat_room.dao;

import java.util.List;

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
	
	/**
	* Method : getChatRoomId
	* 작성자 : 손주형
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 유저 아이디로 채팅방 아이디, 채팅방명 얻기
	*/
	@Override
	public TeamChatRoomVO getChatRoomId(String accountId) {
		return sqlSession.selectOne("teamChatRoom.getChatRoomId", accountId);
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

	/**
	* Method : getChatRoomNM
	* 작성자 : 손주형
	* 변경이력 :
	* @param account_id
	* @return
	* Method 설명 : 채팅방 번호로 채팅방 ID을 조회하는 메서드
	*/
	@Override
	public String getAccount_id_fk(int chat_room_id) {
		return sqlSession.selectOne("teamChatRoom.getAccount_id_fk", chat_room_id);
	}
}
