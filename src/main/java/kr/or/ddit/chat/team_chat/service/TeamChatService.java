package kr.or.ddit.chat.team_chat.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.chat.team_chat.dao.ITeamChatDao;
import kr.or.ddit.chat.team_chat.model.TeamChatVO;

@Service
public class TeamChatService implements ITeamChatService{

	@Resource(name = "teamChatDao")
	private ITeamChatDao teamChatDao;

	/**
	* Method : insertChat
	* 작성자 : 손주형
	* 변경이력 :
	* @param teamChatVo
	* @return
	* Method 설명 : 사용자가 입력하는 모든 채팅을 DB에 저장하는 메서드
	*/
	@Override
	public int insertChat(TeamChatVO teamChatVo) {
		return teamChatDao.insertChat(teamChatVo);
	}
	
	/**
	* Method : userChatList
	* 작성자 : 손주형
	* 변경이력 :
	* @param teamChatVO
	* @return
	* Method 설명 : 구성원들이 입력한 모든 채팅내역 반환
	*/
	@Override
	public List<TeamChatVO> userChatList(TeamChatVO teamChatVO) {
		return teamChatDao.userChatList(teamChatVO);
	}


}
