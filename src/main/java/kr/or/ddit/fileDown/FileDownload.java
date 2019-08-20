package kr.or.ddit.fileDown;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.ts_file.model.TSFileVO;
import kr.or.ddit.ts_file.service.ITSFileService;

public class FileDownload extends AbstractView {
	
	private static final Logger logger = LoggerFactory.getLogger(FileDownload.class);
	
	@Resource(name = "TSFileService")
	private ITSFileService fileService;
	
//	@RequestMapping(path = "/download", method = RequestMethod.POST)
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		response.setContentType("application/download; utf-8");
			int fileId = (int) model.get("file_id");

			logger.debug("filedownload fileId : {}", fileId);
			
			// 아이디에 맞는 정보를 담은 첨부파일 객체
			TSFileVO fileVo = fileService.getFile(fileId);
			
			// 첨부파일 이름
			String fileName = fileVo.getTsfile_filename();
			logger.debug("fileName : {}", fileName);
			StringBuffer sb = new StringBuffer();

            for (int i = 0; i < fileName.length(); i++) {

                   char c = fileName.charAt(i);

                   if (c > '~') {

                         sb.append(URLEncoder.encode("" + c, "UTF-8"));

                   } else {

                         sb.append(c);

                   }

            }

            fileName = sb.toString();
			
			// 다운로드할 첨부파일 이름 설정
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
			response.setContentType("application/octet-stream");
			
			// 파일 객체 생성
			File file = new File(fileVo.getTsfile_path());
			FileInputStream fis = new FileInputStream(file);
			ServletOutputStream sos = response.getOutputStream();
			
			byte[] buff = new byte[1024];
			int len = -1;
			
			while( (len = fis.read(buff)) != -1 ){
				sos.write(buff);
			}
			
			sos.close();
			fis.close();
	}
}
