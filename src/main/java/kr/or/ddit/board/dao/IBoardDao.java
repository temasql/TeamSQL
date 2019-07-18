package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardDao {

	int insert(BoardVO boardVo);
	
	BoardVO get(String id);
	
	List<BoardVO> list();
	
	List<BoardVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
