package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.user.model.UserVO;

public interface IUserService {
	/**
	* Method : insertUser
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	 * @param profile 
	* @return
	* Method 설명 : 유저 등록
	*/
	int insertUser(UserVO userVo, MultipartFile profile);
	
	/**
	* Method : get
	* 작성자 : 이중석
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 아이디에 해당하는 유저의 정보
	*/
	UserVO getUser(String user_id);
	
	
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
	* Method : updateUser
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 정보 수정
	*/
	int updateUser(UserVO userVo);

	/**
	* Method : delete
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 파라미터 아이디에 해당하는 회원의 탈퇴 구분을 N에서 Y로 바꿈
	*/
	int deleteUser(String user_id);
}
