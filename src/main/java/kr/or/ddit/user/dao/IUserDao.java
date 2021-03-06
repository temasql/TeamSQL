package kr.or.ddit.user.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.user.model.UserVO;

public interface IUserDao {
	
	/**
	* Method : insertUser
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 유저 등록
	*/
	int insertUser(UserVO userVo);
	
	int insertAdmin(UserVO userVo);
	
	/**
	* Method : get
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
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
	List<UserVO> userList(Map<String, Object> pageMap);

	/**
	 * Method : list
	 * 작성자 : 이중석
	 * 변경이력 :
	 * @return
	 * Method 설명 : 유저 리스트
	 */
	List<UserVO> adminList(Map<String, Object> pageMap);
	
	
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
	* Method : temporaryUpdateUserPw
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : PW찾기 후 발송한 임시비밀번호를 암호화
	*/
	int temporaryUpdateUserPw(UserVO userVo);
	
	/**
	* Method : delete
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 파라미터 아이디에 해당하는 회원의 탈퇴 구분을 N에서 Y로 바꿈
	*/
	int deleteUser(UserVO userVo);
	
	/**
	* Method : findUserId
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자의 이름과 이메일을 입력하여 아이디 조회
	*/
	List<String> findUserId(UserVO userVo);
	
	/**
	 * Method : findUserPw
	 * 작성자 : 이중석
	 * 변경이력 :
	 * @param userVo
	 * @return
	 * Method 설명 : 사용자의 아이디와 이메일을 입력하여 아이디 조회
	 */
	String findUserPw(UserVO userVo);

	/**
	* Method : userSearchCount
	* 작성자 : 이중석
	* 변경이력 :
	* @param search
	* @return
	* Method 설명 : 검색어에 해당하는 이용가능한 일반회원 수
	*/
	int userSearchCount(String search);
	
	/**
	 * Method : userSearchCount
	 * 작성자 : 이중석
	 * 변경이력 :
	 * @param search
	 * @return
	 * Method 설명 : 검색어에 해당하는 이용가능한 일반회원 수
	 */
	int adminSearchCount(String search);
	
	/**
	* Method : deleteUserMG
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 관리자가 체크박스로 사용자 탈퇴
	*/
	int deleteUserMG(String user_id);
}
