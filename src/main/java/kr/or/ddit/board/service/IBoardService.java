package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardService {
	
	int insert(BoardVO boardVo);
	
	BoardVO get(String id);
	
	List<BoardVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}
