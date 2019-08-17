package kr.or.ddit.ts_file.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.ts_file.model.TSFileVO;

@Repository
public class TSFileDao implements ITSFileDao{

	@Resource(name="sqlSession")
	SqlSessionTemplate sqlSession;
	
	
	/**
	* Method : insertFile
	* 작성자 : 이영은
	* 변경이력 :
	* @param fileVo
	* @return
	* Method 설명 : 파일 첨부
	*/
	@Override
	public int insertFile(TSFileVO fileVo) {
		return sqlSession.insert("tsFile.insertFile", fileVo);
	}

	
	/**
	* Method : getFileList
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 해당 게시글에 첨부된 파일리스트 조회
	*/
	@Override
	public List<TSFileVO> getFileList(int post_id) {
		return sqlSession.selectList("tsFile.getFileList", post_id);
	}

	
	/**
	* Method : getFile
	* 작성자 : 이영은
	* 변경이력 :
	* @param tsfile_id
	* @return
	* Method 설명 : 해당 게시글에 첨부된 파일 조회
	*/
	@Override
	public TSFileVO getFile(int tsfile_id) {
		return sqlSession.selectOne("tsFile.getFile", tsfile_id);
	}

	
	/**
	* Method : deleteFile
	* 작성자 : 이영은
	* 변경이력 :
	* @param delFileId
	* @return
	* Method 설명 : 게시글 수정 시 해당 게시글에 첨부된 파일 삭제
	*/
	@Override
	public int deleteFile(String delFileId) {
		return sqlSession.delete("tsFile.deleteFile", delFileId);
	}


}
