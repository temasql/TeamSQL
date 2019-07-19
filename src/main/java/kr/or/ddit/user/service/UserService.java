package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVO;

@Service
public class UserService implements IUserService{

	@Resource(name =  "userDao")
	private IUserDao userDao;
	
	/**
	* Method : insertUser
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 유저 등록
	*/
	@Override
	public int insertUser(UserVO userVo) {
		return userDao.insertUser(userVo);
	}

	/**
	* Method : getUser
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 아이디에 해당하는 유저의 정보
	*/
	@Override
	public UserVO getUser(String user_id) {
		return userDao.getUser(user_id);
	}

	/**
	* Method : userList
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 : 유저 리스트
	*/
	@Override
	public List<UserVO> userList() {
		return userDao.userList();
	}

	/**
	* Method : map
	* 작성자 : 이중석
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 :
	*/
	@Override
	public List<UserVO> map(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* Method : updateUser
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 정보 수정
	*/
	@Override
	public int updateUser(UserVO userVo) {
		return userDao.updateUser(userVo);
	}

	/**
	* Method : deleteUser
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 파라미터 아이디에 해당하는 회원의 탈퇴 구분을 N에서 Y로 바꿈
	*/
	@Override
	public int deleteUser(String user_id) {
		return userDao.deleteUser(user_id);
	}

}
