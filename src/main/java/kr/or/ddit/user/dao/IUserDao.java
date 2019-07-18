package kr.or.ddit.user.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.user.model.UserVO;

public interface IUserDao {
	
	/**
	* Method : insertUser
	* 작성자 : 이중석
	* 변경이력 :
	* @param commonsVo
	* @return
	* Method 설명 : 유저 등록
	*/
	int insertUser(UserVO commonsVo);
	
	/**
	* Method : get
	* 작성자 : 이중석
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 아이디에 해당하는 유저의 정보
	*/
	UserVO getUser(String id);
	
	
	/**
	* Method : list
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 : 유저 리스트
	*/
	List<UserVO> userList();
	
	/**
	* Method : map
	* 작성자 : 이중석
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 :
	*/
	List<UserVO> map(Map<String, Object> map);
	
	/**
	* Method : update
	* 작성자 : 이중석
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 사용자 정보 수정
	*/
	int updateUser(String id);

	/**
	* Method : delete
	* 작성자 : 이중석
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 사용자 삭제 
	*/
	int delete(String id);
}
