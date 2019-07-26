package kr.or.ddit.invite.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.invite.model.InviteVO;

@Repository
public class InviteDao implements IInviteDao {

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;
	
	/**
	* Method : getInviteList
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id_fk
	* @return
	* Method 설명 : 사용자 아이디에 해당하는 초대장 리스트
	*/
	@Override
	public List<InviteVO> getInviteList(String user_id_fk) {
		return sqlSession.selectList("invite.getInviteList", user_id_fk);
	}

	/**
	* Method : deleteInvite
	* 작성자 : 이중석
	* 변경이력 :
	* @param invite_id
	* @return
	* Method 설명 : 초대장 번호에 해당하는 초대장을 삭제
	*/
	@Override
	public int deleteInvite(int invite_id) {
		return sqlSession.delete("invite.deleteInvite", invite_id);
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
		return sqlSession.insert("invite.insertCrew", crewVo);
	}

}
