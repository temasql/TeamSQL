package kr.or.ddit.crew.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.account.dao.IAccountDao;
import kr.or.ddit.account.model.AccountVO;
import kr.or.ddit.crew.dao.ICrewDao;
import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.crew.model.UserAndCrewVO;

@Service
public class CrewService implements ICrewService{
	
	@Resource(name = "crewDao")
	private ICrewDao crewDao;
	
	@Resource(name = "accountDao")
	private IAccountDao accountDao;
	
	/**
	 * 
	* Method : insertCrew
	* 작성자 : 김범휘
	* 변경이력 :
	* @param crewVO
	* @return
	* Method 설명 : 구성원 추가
	 */
	@Override
	public int insertCrew(CrewVO crewVO) {
		return crewDao.insertCrew(crewVO);
	}

	/**
	* Method : crewList
	* 작성자 : 이중석
	* 변경이력 :
	* @param pageMap
	* @return
	* Method 설명 :
	*/
	@Override
	public Map<String, Object> crewList(Map<String, Object> pageMap) {
		// 검색한 내용에 해당하는 회원이 페이징 처리 되어 리스트에 담긴다
		List<CrewVO> crewList = crewDao.crewList(pageMap);
		Map<String, Object> accountMap = new HashMap<String, Object>();
		accountMap.put("account_id", (String)pageMap.get("account_id_fk"));
		accountMap.put("user_id_fk", (String)pageMap.get("user_id"));
		AccountVO accountVo = accountDao.getAccountVo(accountMap);
		
		int crewSearchCount = crewDao.crewSearchCount(pageMap);
		
		// 컨트롤러에서 담아온 pageSize
		int pageSize = (int) pageMap.get("pageSize");
		int paginationSize = (int) Math.ceil((double)crewSearchCount/pageSize);
		
		// 게시글이 없어서 paginationSize가 0 일경우 임의로 1설정
		paginationSize = paginationSize == 0 ? 1 : paginationSize;
		
		// Map객체에 페이징 처리된 게시글 리스트와 페이지의 갯수를 담아서 리턴한다
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("crewList", crewList);
		resultMap.put("paginationSize", paginationSize);
		resultMap.put("accountVo", accountVo);
		return resultMap;
	}

	/**
	* Method : crewSelectList
	* 작성자 : 이중석
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 :
	*/
	@Override
	public List<CrewVO> crewSelectList(String user_id) {
		return crewDao.crewSelectList(user_id);
	}

	/**
	* Method : deleteCrew
	* 작성자 : 이중석
	* 변경이력 :
	* @param crewVO
	* @return
	* Method 설명 :
	*/
	@Override
	public int deleteCrew(CrewVO crewVO) {
		return crewDao.deleteCrew(crewVO);
	}

	/**
	* Method : getCrew
	* 작성자 : 이중석
	* 변경이력 :
	* @param crewVo
	* @return
	* Method 설명 :초대전에 이미 구성원이 있는지 null체크
	*/
	@Override
	public CrewVO getCrew(CrewVO crewVo) {
		return crewDao.getCrew(crewVo);
	}

	/**
	 * 
	* Method : getMyAccountList
	* 작성자 : 김범휘
	* 변경이력 :
	* @param user_id
	* @return
	* Method 설명 : 자신이 속한 DB계정 아이디 리스트 가져오기
	 */
	@Override
	public List<String> getMyAccountList(String user_id) {
		return crewDao.getMyAccountList(user_id);
	}

	/**
	* Method : getCrewList
	* 작성자 : 손주형
	* 변경이력 :
	* @param crewVO
	* @return
	* Method 설명 : 계정명을 입력 받아 userid와 username을 리턴
	*/
	@Override
	public List<UserAndCrewVO> getCrewList(CrewVO crewVO) {
		return crewDao.getCrewList(crewVO);
	}
}
