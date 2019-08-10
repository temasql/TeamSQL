package kr.or.ddit.post.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.post.model.PostVO;

public interface IPostService {
	
	/**
	* Method : allPostList
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 전체 게시판 게시글 리스트
	*/
	List<PostVO> allPostList();
	
	
	/**
	* Method : boardPostList
	* 작성자 : 이영은
	* 변경이력 :
	* @param board_id
	* @return
	* Method 설명 : 특정 게시판 게시글 조회
	*/
	List<PostVO> boardPostList(int board_id);
	
	
	/**
	* Method : postPagingList
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 게시글 페이징 리스트
	*/
	Map<String, Object> postPagingList(Map<String, Object> map);
	
	
	/**
	* Method : insertPost
	* 작성자 : 이영은
	* 변경이력 :
	* @param postVo
	* @return
	* Method 설명 : 게시글 등록
	*/
	int insertPost(PostVO postVo);
}
