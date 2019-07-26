package kr.or.ddit.invite.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.invite.dao.IInviteDao;
import kr.or.ddit.invite.model.InviteVO;

@Service
public class InviteService implements IInviteService {

	@Resource(name = "inviteDao")
	private IInviteDao inviteDao;
	/**
	* Method : getInviteList
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id_fk
	* @return
	* Method 설명 :
	*/
	@Override
	public List<InviteVO> getInviteList(String user_id_fk) {
		return inviteDao.getInviteList(user_id_fk);
	}
	
	/**
	* Method : deleteInvite
	* 작성자 : 이중석
	* 변경이력 :
	* @param invite_id
	* @return
	* Method 설명 :초대장 번호에 해당하는 초대장을 삭제
	*/
	@Override
	public int deleteInvite(int invite_id) {
		return inviteDao.deleteInvite(invite_id);
	}

	/**
	* Method : insertCrew
	* 작성자 : 이중석
	* 변경이력 :
	* @param crewVo
	* @return
	* Method 설명 :
	*/
	@Override
	public int insertCrew(CrewVO crewVo) {
		return inviteDao.insertCrew(crewVo);
	}

}
