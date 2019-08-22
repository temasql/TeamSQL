package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.post.model.PostVO;

public interface IPostDao {

	/**
	* Method : allPostList
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 전체 게시글 리스트 조회
	*/
	List<PostVO> allPostList();
	
	
	/**
	* Method : boardPostList
	* 작성자 : 이영은
	* 변경이력 :
	* @param board_id
	* @return
	* Method 설명 : 특정 게시판 게시글 리스트 조회
	*/
	List<PostVO> boardPostList(int board_id);
	
	
	/**
	* Method : postCnt
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 게시글 갯수
	*/
	int postCnt(Map<String, Object> map);
	
	
	/**
	* Method : postPagingList
	* 작성자 : 이영은
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 게시글 페이징 리스트
	*/
	List<PostVO> postPagingList(Map<String, Object> map);
	
	
	/**
	* Method : insertPost
	* 작성자 : 이영은
	* 변경이력 :
	* @param postVo
	* @return
	* Method 설명 : 게시글 등록
	*/
	int insertPost(PostVO postVo);


	/**
	* Method : postMaxCnt
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 마지막 게시글 아이디 조회
	*/
	int postMaxCnt();
	
			
	/**
	* Method : getPost
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 게시글 상세조회
	*/
	PostVO getPost(int post_id);
	
	
	/**
	* Method : updatePost
	* 작성자 : 이영은
	* 변경이력 :
	* @param postVo
	* @return
	* Method 설명 : 게시글 수정
	*/
	int updatePost(PostVO postVo);
	
	
	/**
	* Method : answerPost
	* 작성자 : 이영은
	* 변경이력 :
	* @param postVo
	* @return
	* Method 설명 : 답글 등록
	*/
	int answerPost(PostVO postVo);
	
	
	/**
	* Method : deletePost
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 게시글 삭제(사용여부 미사용으로 변경)
	*/
	int deletePost(int post_id);
	
	
	/**
	* Method : updateViewCnt
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 게시글 조회수 증가
	*/
	int updateViewCnt(int post_id);
	
}
