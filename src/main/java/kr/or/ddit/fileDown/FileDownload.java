package kr.or.ddit.fileDown;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.AbstractView;

@RequestMapping("/fileDownload")
@Controller
public class FileDownload extends AbstractView {

	@RequestMapping(path = "download", method = RequestMethod.POST)
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			String fileId = (String) model.get("file_Id");

			// 아이디에 맞는 정보를 담은 첨부파일 객체
			//UploadFileVO fileVo = fileService.getFileVo(fileId);
			
			// 첨부파일 이름
			//String fileName = fileVo.getFileName();
			
			
			// 다운로드할 첨부파일 이름 설정
			//response.setHeader("Content-Disposition", "attachment;filename="+fileName);
			response.setContentType("application/octet-stream");
			
			// 파일 객체 생성
			File file = new File(fileId);
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
