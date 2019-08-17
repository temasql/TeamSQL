package kr.or.ddit.user.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVO;

@Repository
public class UserDao implements IUserDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	* Method : insertUser
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	*/
	@Override
	public int insertUser(UserVO userVo) {
		return sqlSession.insert("user.insertUser", userVo);
	}

	/**
	* Method : getUser
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 아이디에 해당하는 사용자 정보 조회
	*/
	@Override
	public UserVO getUser(String user_id) {
		return sqlSession.selectOne("user.getUser", user_id);
	}

	/**
	* Method : userList
	* 작성자 : 이중석
	* 변경이력 :
	* @param pageMap
	* @return
	* Method 설명 : 일반 회원 페이징 처리
	*/
	@Override
	public List<UserVO> userList(Map<String, Object> pageMap) {
		return sqlSession.selectList("user.userList", pageMap);
	}
	
	/**
	 * Method : adminList
	 * 작성자 : 이중석
	 * 변경이력 :
	 * @param pageMap
	 * @return
	 * Method 설명 : 관리자 페이징 처리
	 */
	@Override
	public List<UserVO> adminList(Map<String, Object> pageMap) {
		return sqlSession.selectList("user.adminList", pageMap);
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
		return sqlSession.update("user.updateUser", userVo);
	}

	/**
	* Method : deleteUser
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 파라미터 아이디에 해당하는 회원의 탈퇴 구분을 N에서 Y로 바꿈 
	*/
	@Override
	public int deleteUser(UserVO userVo) {
		return sqlSession.update("user.deleteUser", userVo);
	}

	/**
	* Method : findUserId
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 아이디 찾기
	*/
	@Override
	public List<String> findUserId(UserVO userVo) {
		return sqlSession.selectList("user.findUserId", userVo);
	}

	/**
	* Method : findUserPw
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자의 아이디와 이메일을 입력하여 아이디 조회
	*/
	@Override
	public String findUserPw(UserVO userVo) {
		return sqlSession.selectOne("user.findUserPw", userVo);
	}

	/**
	* Method : temporaryUpdateUserPw
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : PW찾기 후 발송한 임시비밀번호를 암호화
	*/
	@Override
	public int temporaryUpdateUserPw(UserVO userVo) {
		return sqlSession.update("user.temporaryUpdateUserPw", userVo);
	}

	/**
	* Method : userSearchCount
	* 작성자 : 이중석
	* 변경이력 :
	* @param search
	* @return
	* Method 설명 : 검색어에 해당하는 이용가능한 일반회원 수
	*/
	@Override
	public int userSearchCount(String search) {
		return sqlSession.selectOne("user.userSearchCount", search);
	}

	/**
	* Method : deleteUserMG
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 관리자가 체크박스로 사용자 탈퇴
	*/
	@Override
	public int deleteUserMG(String user_id) {
		return sqlSession.update("user.deleteUserMG", user_id);
	}

	/**
	* Method : adminSearchCount
	* 작성자 : 이중석
	* 변경이력 :
	* @param search
	* @return
	* Method 설명 : 검색어에 해당하는 관리자 수 테스트
	*/
	@Override
	public int adminSearchCount(String search) {
		return sqlSession.selectOne("user.adminSearchCount", search);
	}

	/**
	* Method : insertAdmin
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 관리자 등록 테스트
	*/
	@Override
	public int insertAdmin(UserVO userVo) {
		return sqlSession.insert("user.insertAdmin", userVo);
	}

}
