package kr.or.ddit.board.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.board.model.BoardVO;

//@Repository
public class BoardDao implements IBoardDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(BoardVO boardVo) {
		return sqlSession.insert("board.insert", boardVo);
	}
	
	@Override
	public BoardVO get(String id) {
		return sqlSession.selectOne("board.get", id);
	}
	
	@Override
	public List<BoardVO> list() {
		return sqlSession.selectList("board.list");
	}

	@Override
	public List<BoardVO> map(Map<String, Object> map) {
		return sqlSession.selectList("board.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("board.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("board.delete", id);
	}

	

}
