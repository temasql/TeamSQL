package kr.or.ddit.ts_file.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.ts_file.dao.ITSFileDao;
import kr.or.ddit.ts_file.model.TSFileVO;

@Service
public class TSFileService implements ITSFileService{

	@Resource(name = "TSFileDao")
	private ITSFileDao tsFiledao;

	
	/**
	* Method : insertFile
	* 작성자 : 이영은
	* 변경이력 :
	* @param uploadFileList
	* @return
	* Method 설명 : 파일 첨부
	*/
	@Override
	public int insertFile(List<TSFileVO> uploadFileList) {
		int insertCntSum = 0;
		
		for(TSFileVO fileVo : uploadFileList) {
			int insertCnt = tsFiledao.insertFile(fileVo);
			insertCntSum += insertCnt;
		}
		return insertCntSum;
	}

	
	/**
	* Method : getFileList
	* 작성자 : 이영은
	* 변경이력 :
	* @param post_id
	* @return
	* Method 설명 : 해당 게시글에 첨부된 파일 리스트
	*/
	@Override
	public List<TSFileVO> getFileList(int post_id) {
		return tsFiledao.getFileList(post_id);
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
		return tsFiledao.getFile(tsfile_id);
	}


	/**
	* Method : delUpdateFiles
	* 작성자 : 이영은
	* 변경이력 :
	* @param delFileIds
	* Method 설명 : 게시글 수정 시 파일이 수정되었으면 기존의 파일 데이터 삭제
	*/
	@Override
	public void delUpdateFiles(String[] delFileIds) {
		int delCntSum = 0;
		
		for(String delFileId : delFileIds) {
			int delCnt = tsFiledao.deleteFile(delFileId);
			
			if(delCnt != 1 ) {
				break;
			}
			delCntSum += delCnt;
		}
	}

}
