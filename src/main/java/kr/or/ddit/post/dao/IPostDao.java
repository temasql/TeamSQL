package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.post.model.PostVO;

public interface IPostDao {

	int insert(PostVO postVo);
	
	PostVO get(String id);
	
	List<PostVO> list();
	
	List<PostVO> map(Map<String, Object> map);
	
	int update(String id);

	int delete(String id);
}
