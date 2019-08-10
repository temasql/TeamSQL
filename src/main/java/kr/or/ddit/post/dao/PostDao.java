package kr.or.ddit.post.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.post.model.PostVO;

@Repository
public class PostDao implements IPostDao{

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	
	/**
	* Method : allPostList
	* 작성자 : 이영은
	* 변경이력 :
	* @return
	* Method 설명 : 전체 게시글 리스트 조회
	*/
	@Override
	public List<PostVO> allPostList() {
		return sqlSession.selectList("post.allPostList");
	}


	/**
	* Method : boardPostList
	* 작성자 : 이영은
	* 변경이력 :
	* @param board_id
	* @return
	* Method 설명 : 특정 게시판 게시글 리스트 조회
	*/
	@Override
	public List<PostVO> boardPostList(int board_id) {
		return sqlSession.selectList("post.boardPostList", board_id);
	}


	/**
	* Method : postCnt
	* 작성자 : 이영은
	* 변경이력 :
	* @param board_id
	* @return
	* Method 설명 : 특정 게시판 게시글 갯수
	*/
	@Override
	public int postCnt(int board_id) {
		return (Integer)sqlSession.selectOne("post.postCnt", board_id);
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
	public List<PostVO> postPagingList(Map<String, Object> map) {
		return sqlSession.selectList("post.postPagingList", map);
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
		return sqlSession.insert("post.insertPost", postVo);
	}

	

}
