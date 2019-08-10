package kr.or.ddit.board.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVO;

@Service
public class BoardService implements IBoardService{

	@Resource(name = "boardDao")
	private IBoardDao boardDao;

	
	/**
	* Method : boardList
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 리스트 조회
	*/
	@Override
	public List<BoardVO> boardList() {
		return boardDao.boardList();
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
		return boardDao.boardMaxCnt();
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
		return boardDao.addBoard(boardVo);
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
		return boardDao.modifyBoard(boardVo);
	}

	
	/**
	* Method : getBoard
	* 작성자 : 이영은
	* 변경이력 :
	* @param board_id
	* @return
	* Method 설명 : 입력받은 게시판 아이디와 일치하는 게시판의 정보
	*/
	@Override
	public BoardVO getBoard(int board_id) {
		return boardDao.getBoard(board_id);
	}

}