package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

import kr.or.ddit.post.model.PostVO;

//@Repository
public class PostDao implements IPostDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	@Override
	public int insert(PostVO postVo) {
		return sqlSession.insert("post.insert", postVo);
	}
	
	@Override
	public PostVO get(String id) {
		return sqlSession.selectOne("post.get", id);
	}
	
	@Override
	public List<PostVO> list() {
		return sqlSession.selectList("post.list");
	}

	@Override
	public List<PostVO> map(Map<String, Object> map) {
		return sqlSession.selectList("post.map", map);
	}
	
	@Override
	public int update(String id) {
		return sqlSession.update("post.update", id);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete("post.delete", id);
	}

	

}
