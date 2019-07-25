package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.domain.user_domain.dao.IUserDomainDao;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.template.user_template.dao.IUserTemplateDao;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.ProfileUtil;

@Service
public class UserService implements IUserService{

	@Resource(name =  "userDao")
	private IUserDao userDao;
	
	@Resource(name = "userDomainDao")
	private IUserDomainDao userDomainDao;
	
	@Resource(name = "userTemplateDao")
	private IUserTemplateDao userTemplateDao;
	
	/**
	* Method : insertUser
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 유저 등록
	*/
	@Override
	public int insertUser(UserVO userVo, MultipartFile profile) {
		
		// 사용자 비밉런호 암호화 
	    userVo.setUser_pw(KISA_SHA256.encrypt(userVo.getUser_pw()));
	    
	    // Oauth계정의 프로필 사진이 있으면 서버 profile폴더에 따로 이미지를 생성하지 않는다.
	    if(userVo.getUser_path() != null)
    		userVo.setUser_path(ProfileUtil.insertProfile(profile, userVo));
	    
	    // 사용자 등록
	    int userInsertCount = userDao.insertUser(userVo);
	    
	    // 등록 확인
	    if (userInsertCount == 1) {
	    	
	    	// 공통 도메인, 템플릿 등록
			userDomainDao.insertCommonDomain(userVo.getUser_id());
			userTemplateDao.insertCommonTemplate(userVo.getUser_id());
		}
	    
		return userInsertCount;
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
	public Map<String, Object> userList(Map<String, Object> pageMap) {
		// 검색한 내용에 해당하는 회원이 페이징 처리 되어 리스트에 담긴다
		List<UserVO> userList = userDao.userList(pageMap);
		
		// 검색한 내용에 해당하는 회원의 수
		String search = (String) pageMap.get("search");
		int userSearchCount = userDao.userSearchCount(search);
		
		// 컨트롤러에서 담아온 pageSize
		int pageSize = (int) pageMap.get("pageSize");
		int paginationSize = (int) Math.ceil((double)userSearchCount/pageSize);
		
		// 게시글이 없어서 paginationSize가 0 일경우 임의로 1설정
		paginationSize = paginationSize == 0 ? 1 : paginationSize;
		
		// Map객체에 페이징 처리된 게시글 리스트와 페이지의 갯수를 담아서 리턴한다
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("userList", userList);
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
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
	public int updateUser(UserVO userVo, MultipartFile profile) {
		// 사용자 비밉런호 암호화 
	    userVo.setUser_pw(KISA_SHA256.encrypt(userVo.getUser_pw()));
	    
	    // Oauth계정의 프로필 사진이 있으면 서버 profile폴더에 따로 이미지를 생성하지 않는다.
		userVo.setUser_path(ProfileUtil.updateProfile(profile, userVo));
	    
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
	public int deleteUser(UserVO userVo) {
		return userDao.deleteUser(userVo);
	}

	/**
	* Method : findUserId
	* 작성자 : 이중석
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자의 이름과 이메일을 입력하여 아이디 조회
	*/
	@Override
	public String findUserId(UserVO userVo) {
		return userDao.findUserId(userVo);
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
		return userDao.findUserPw(userVo);
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
		return userDao.temporaryUpdateUserPw(userVo);
	}

	/**
	* Method : userSearchCount
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 :
	*/
	@Override
	public int userSearchCount(String search) {
		return userDao.userSearchCount(search);
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
		return userDao.deleteUserMG(user_id);
	}
	
	

}
