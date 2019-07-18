package kr.or.ddit.post.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.post.model.PostVO;

public interface IPostService {
	
	int insert(PostVO postVo);
	
	PostVO get(String id);
	
	List<PostVO> list();
	
	Map<String, Object> map(Map<String, Object> map);
	
	int update(String id);
	
	int delete(String id);
}
