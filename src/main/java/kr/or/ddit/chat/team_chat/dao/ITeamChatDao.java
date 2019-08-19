package kr.or.ddit.chat.team_chat.dao;

import java.util.List;

import kr.or.ddit.chat.team_chat.model.TeamChatVO;

public interface ITeamChatDao {
	/**
	* Method : insertChat
	* 작성자 : 손주형
	* 변경이력 :
	* @param teamChatVo
	* @return
	* Method 설명 : 사용자가 입력하는 모든 채팅을 DB에 저장하는 메서드
	*/
	int insertChat(TeamChatVO teamChatVo);
	
	/**
	* Method : userChatList
	* 작성자 : 손주형
	* 변경이력 :
	* @param teamChatVO
	* @return
	* Method 설명 : 구성원들이 입력한 모든 채팅내역 반환
	*/
	List<TeamChatVO> userChatList(TeamChatVO teamChatVO);
	
}
