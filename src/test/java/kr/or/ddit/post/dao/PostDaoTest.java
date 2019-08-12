package kr.or.ddit.post.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.post.model.PostVO;
import kr.or.ddit.testenv.LogicTestEnv;

/**
* CommonsDaoTest.java
*
* @author OWNER
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* OWNER 최초 생성
* Dao 테스트의 틀을 위한 클래스입니다.
* </pre>
*/
public class PostDaoTest extends LogicTestEnv{

	@Resource(name = "postDao")
	private IPostDao dao;
	
	@Test
	public void daoTest() {
		/***Given***/
		Map<String, Object> map = new HashMap<String, Object>();
		List<PostVO> boardList = dao.allPostList();
		map.put("board_id", boardList.get(0).getBoard_id_fk());
		map.put("page", 1);
		map.put("pageSize", 10);
		/***When***/
		List<PostVO> postList = dao.postPagingList(map);
		/***Then***/
		assertNotNull(postList);
//		assertEquals(5, postList.size());
	}

}
