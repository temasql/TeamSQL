package kr.or.ddit.chat.team_chat_room.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Override
	public TeamChatRoomVO get(String id) {
		return teamChatRoomDao.get(id);
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

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<TeamChatRoomVO> mapList =  teamChatRoomDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return teamChatRoomDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return teamChatRoomDao.delete(id);
	}

}
