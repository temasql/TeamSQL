package kr.or.ddit.crew.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.crew.dao.ICrewDao;
import kr.or.ddit.crew.model.CrewVO;
import kr.or.ddit.user.model.UserVO;

@Service
public class CrewService implements ICrewService{
	
	@Resource(name = "crewDao")
	private ICrewDao crewDao;
	
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

	private static final Logger logger = LoggerFactory.getLogger(CrewService.class);
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
		String a = (String) pageMap.get("search");
		String a2 = (String) pageMap.get("user_id");
		String a3 = (String) pageMap.get("account_id_fk");
		int a4 = (int) pageMap.get("page");
		int a5 = (int) pageMap.get("pageSize");
		logger.debug("crewList ===>>>[{}]", crewList);
		logger.debug("crewList search ===>>>[{}]", a);
		logger.debug("crewList user_id ===>>>[{}]", a2);
		logger.debug("crewList account_id_fk ===>>>[{}]", a3);
		logger.debug("crewList page ===>>>[{}]", a4);
		logger.debug("crewList pageSize ===>>>[{}]", a5);
		// 검색한 내용에 해당하는 회원의 수
//		String search = (String) pageMap.get("search");
		
		
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


}
