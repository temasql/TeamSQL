package kr.or.ddit.ts_file.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.ts_file.model.TSFileVO;

public interface ITSFileDao {

	/**
	* Method : insertFile
	* 작성자 : 이영은
	* 변경이력 :
	* @param fileVo
	* @return
	* Method 설명 : 파일첨부
	*/
	int insertFile(TSFileVO fileVo);
	
	
	/**
	* Method : getFileList
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 해당 게시글에 첨부된 파일리스트 조회
	*/
	List<TSFileVO> getFileList(int post_id);
	
	
	/**
	* Method : getFile
	* 작성자 : 이영은
	* 변경이력 :
	* @param tsfile_id
	* @return
	* Method 설명 : 해당 게시글에 첨부된 파일 조회
	*/
	TSFileVO getFile(int tsfile_id);
	
	
	/**
	* Method : deleteFile
	* 작성자 : 이영은
	* 변경이력 :
	* @param delFileId
	* @return
	* Method 설명 : 게시글 수정 시 해당 게시글에 첨부된 파일 삭제
	*/
	int deleteFile(String delFileId);
}
