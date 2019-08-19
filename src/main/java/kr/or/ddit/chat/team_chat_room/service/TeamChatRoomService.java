package kr.or.ddit.chat.team_chat_room.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.chat.team_chat_room.dao.ITeamChatRoomDao;
import kr.or.ddit.chat.team_chat_room.model.TeamChatRoomVO;

@Service
public class TeamChatRoomService implements ITeamChatRoomService{

	@Resource(name = "teamChatRoomDao")
	private ITeamChatRoomDao teamChatRoomDao;

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
		return teamChatRoomDao.insertTeamChatRoom(teamChatRoomVo);
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
		return teamChatRoomDao.getChatRoomId(accountId);
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
		return teamChatRoomDao.chatRoomList(userId);
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
		return teamChatRoomDao.getAccount_id_fk(chat_room_id);
	}
}
