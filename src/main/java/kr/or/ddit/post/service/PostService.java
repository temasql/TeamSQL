package kr.or.ddit.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.post.dao.IPostDao;
import kr.or.ddit.post.model.PostVO;

@Service
public class PostService implements IPostService{

	@Resource(name = "postDao")
	private IPostDao postDao;


	/**
	* Method : allPostList
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 전체 게시판 게시글 리스트
	*/
	@Override
	public List<PostVO> allPostList() {
		return postDao.allPostList();
	}


	/**
	* Method : boardPostList
	* 작성자 : 이영은
	* 변경이력 :
	* @param board_id
	* @return
	* Method 설명 : 특정 게시판 게시글 조회
	*/
	@Override
	public List<PostVO> boardPostList(int board_id) {
		return postDao.boardPostList(board_id);
	}	
	
	
	/**
	* Method : postPagingList
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 게시글 페이징 리스트
	*/
	@Override
	public Map<String, Object> postPagingList(Map<String, Object> map) {
		List<PostVO> postPagingList = postDao.postPagingList(map);
		
		int postCnt = postDao.postCnt((int) map.get("board_id"));
		int pageSize = (int) map.get("pageSize");
		int pagination = (int) Math.ceil((double)postCnt/pageSize);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("postList", postPagingList);
		resultMap.put("paginationSize", pagination);
		
		return resultMap;
	}


	/**
	* Method : insertPost
	* 작성자 : 이영은
	* 변경이력 :
	* @param postVo
	* @return
	* Method 설명 : 게시글 등록
	*/
	@Override
	public int insertPost(PostVO postVo) {
		return postDao.insertPost(postVo);
	}






}
