package kr.or.ddit.chat.team_chat.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.chat.team_chat.model.TeamChatVO;

@Repository
public class TeamChatDao implements ITeamChatDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

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
		return sqlSession.insert("teamChat.insertChat", teamChatVo);
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
		return sqlSession.selectList("teamChat.userChatList", teamChatVO);
	}
}
