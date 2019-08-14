package kr.or.ddit.ts_file.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.ts_file.model.TSFileVO;

public interface ITSFileService {
	
	/**
	* Method : insertFile
	* 작성자 : 이영은
	* 변경이력 :
	* @param uploadFileList
	* @return
	* Method 설명 : 파일 첨부
	*/
	int insertFile(List<TSFileVO> uploadFileList);
	
	
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
	* Method : delUpdateFiles
	* 작성자 : 이영은
	* 변경이력 :
	* @param delFileIds
	* Method 설명 : 게시글 수정 시 파일이 수정되었으면 기존의 파일 데이터 삭제
	*/
	void delUpdateFiles(String[] delFileIds);
}
