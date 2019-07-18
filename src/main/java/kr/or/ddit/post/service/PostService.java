package kr.or.ddit.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.post.dao.IPostDao;
import kr.or.ddit.post.model.PostVO;

//@Service
public class PostService implements IPostService{

	@Resource(name = "postDao")
	private IPostDao postDao;

	@Override
	public int insert(PostVO postVo) {
		return postDao.insert(postVo);
	}
	
	@Override
	public PostVO get(String id) {
		return postDao.get(id);
	}
	
	@Override
	public List<PostVO> list() {
		return postDao.list();
	}

	@Override
	public Map<String, Object> map(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<PostVO> mapList =  postDao.map(map);
		return resultMap;
	}

	@Override
	public int update(String id) {
		return postDao.update(id);
	}
	
	@Override
	public int delete(String id) {
		return postDao.delete(id);
	}

}
