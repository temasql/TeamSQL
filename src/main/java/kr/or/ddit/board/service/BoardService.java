package kr.or.ddit.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVO;

//@Service
public class BoardService implements IBoardService{

	@Resource(name = "boardDao")
	private IBoardDao boardDao;

	@Override
	public int insert(BoardVO boardVo) {
		return boardDao.insert(boardVo);
	}
	
	@Override
	public BoardVO get(String id) {
		return boardDao.get(id);
	}
	
	@Override
	public List<BoardVO> list() {
		return boardDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<BoardVO> mapList =  boardDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return boardDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return boardDao.delete(id);
	}

}
