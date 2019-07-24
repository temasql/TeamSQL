package kr.or.ddit.invite.service;

import java.util.List;

import kr.or.ddit.invite.model.InviteVO;

public interface IInviteService {
	/**
	* Method : getInviteList
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id_fk
	* @return
	* Method 설명 : 사용자 아이디에 해당하는 초대장 리스트
	*/
	List<InviteVO> getInviteList(String user_id_fk);
	
	/**
	* Method : deleteInvite
	* 작성자 : 이중석
	* 변경이력 :
	* @param invite_id
	* @return
	* Method 설명 : 초대장 번호에 해당하는 초대장을 삭제
	*/
	int deleteInvite(int invite_id);
}
