package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.board.model.BoardVO;

@Repository
public class BoardDao implements IBoardDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	
	/**
	* Method : boardList
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 리스트 조회
	*/
	@Override
	public List<BoardVO> boardList() {
		return sqlSession.selectList("board.boardList");
	}

	
	/**
	* Method : boardMaxCnt
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 테스트용 게시판 아이디 생성
	*/
	@Override
	public int boardMaxCnt() {
		return sqlSession.selectOne("board.boardMaxCnt");
	}
	
	
	/**
	* Method : addBoard
	* 작성자 : 이영은
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 신규 게시판 생성
	*/
	@Override
	public int addBoard(BoardVO boardVo) {
		return sqlSession.insert("board.addBoard", boardVo);
	}

	
	/**
	* Method : modifyBoard
	* 작성자 : 이영은
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 게시판 사용여부 변경
	*/
	@Override
	public int modifyBoard(BoardVO boardVo) {
		return sqlSession.update("board.modifyBoard", boardVo);
	}

	
	/**
	* Method : getBoard
	* 작성자 : 이영은
	* 변경이력 :
	* @param boardId
	* @return
	* Method 설명 : 입력받은 게시판 아이디와 일치하는 게시판의 정보
	*/
	@Override
	public BoardVO getBoard(int board_id) {
		return sqlSession.selectOne("board.getBoard", board_id);
	}



}
