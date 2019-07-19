package kr.or.ddit.board.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.testenv.LogicTestEnv;

/**
* CommonsDaoTest.java
*
* @author OWNER
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* OWNER 최초 생성
* Dao 테스트의 틀을 위한 클래스입니다.
* </pre>
*/
public class BoardDaoTest extends LogicTestEnv{

	@Resource(name = "boardDao")
	private IBoardDao dao;
	
	
	/**
	* Method : boardListTest
	* 작성자 : 이영은
	* 변경이력 :
	* Method 설명 : 게시판 리스트 조회 테스트
	*/
	@Test
	public void boardListTest() {
		/***Given***/
		
		/***When***/
		List<BoardVO> boardList = dao.boardList();
		
		/***Then***/
		assertEquals(3, boardList.size());
	}
	
	
	/**
	* Method : addBoardTest
	* 작성자 : 이영은
	* 변경이력 :
	* Method 설명 : 신규 게시판 생성 테스트
	*/
	@Test
	public void addBoardTest() {
		/***Given***/
		int testId = dao.boardMaxCnt();
		BoardVO boardVo = new BoardVO(testId, "admin", "Q&A", "y");

		/***When***/
		int addCnt = dao.addBoard(boardVo);
		
		/***Then***/
		assertEquals(1, addCnt);
	}
	
	
	/**
	* Method : modifyBoardTest
	* 작성자 : 이영은
	* 변경이력 :
	* Method 설명 : 게시판 사용여부 변경 테스트
	*/
	@Test
	public void modifyBoardTest() {
		/***Given***/
		int testId = dao.boardMaxCnt();
		BoardVO boardVo = new BoardVO(testId-1, "admin", "테스트게시판3", "N");

		/***When***/
		int updateCnt = dao.modifyBoard(boardVo);
		
		/***Then***/
		assertEquals(1, updateCnt);
	}
	
	
	/**
	* Method : getBoardTest
	* 작성자 : 이영은
	* 변경이력 :
	* Method 설명 : 입력받은 게시판 아이디와 일치하는 게시판의 정보 테스트
	*/
	@Test
	public void getBoardTest() {
		/***Given***/
		int testId = dao.boardMaxCnt();
		int board_id = testId-1;

		/***When***/
		BoardVO boardVo = dao.getBoard(board_id);
		
		/***Then***/
		assertEquals("테스트게시판3", boardVo.getBoard_name());
	}
}
