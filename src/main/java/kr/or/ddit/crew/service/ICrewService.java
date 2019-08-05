package kr.or.ddit.crew.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.crew.model.UserAndCrewVO;

public interface ICrewService {
	
	/**
	 * 
	* Method : insertCrew
	* 작성자 : 김범휘
	* 변경이력 :
	* @param crewVO
	* @return
	* Method 설명 : 구성원 추가
	 */
	int insertCrew(CrewVO crewVO);
	
	/**
	* Method : crewList
	* 작성자 : 이중석
	* 변경이력 :
	* @param pageMap
	* @return
	* Method 설명 : 구성원 리스트 페이징 처리
	*/
	Map<String, Object> crewList(Map<String, Object> pageMap);

	/**
	* Method : crewSelectList
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 해당 계정의 팀 리스트
	*/
	List<CrewVO> crewSelectList(String user_id);

	/**
	* Method : deleteCrew
	* 작성자 : 이중석
	* 변경이력 :
	* @param crewVO
	* Method 설명 :
	*/
	int deleteCrew(CrewVO crewVO);

	/**
	* Method : getCrew
	* 작성자 : 이중석
	* 변경이력 :
	* @param crewVo
	* @return
	* Method 설명 :초대전에 이미 구성원이 있는지 null체크
	*/
	CrewVO getCrew(CrewVO crewVo);
	
	/**
	 * 
	* Method : getMyAccountList
	* 작성자 : 김범휘
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 자신이 속한 DB계정 아이디 리스트 가져오기
	 */
	List<String> getMyAccountList(String user_id);
	
	/**
	* Method : getCrewList
	* 작성자 : 손주형
	* 변경이력 :
	* @param crewVO
	* @return
	* Method 설명 : 계정명을 입력 받아 userid와 username을 리턴
	*/
	List<UserAndCrewVO> getCrewList(CrewVO crewVO);

	/**
	* Method : getAccountCrew
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 :
	*/
	Map<String, Object> getAccountCrew(String user_id);
}
