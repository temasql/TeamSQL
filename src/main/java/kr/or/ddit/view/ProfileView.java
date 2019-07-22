/**
 * 
 */
package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.View;

import kr.or.ddit.user.model.UserVO;

/**
* ProfileView.java
*
* @author 이중석
* @version 1.0
* @see
*
* <pre>
* << 개정이력(Modification Information) >>
*
* 수정자 수정내용
* ------ ------------------------
* 이중석 최초 생성
*
* </pre>
*/
public class ProfileView implements View{

	private static final Logger logger = LoggerFactory.getLogger(ProfileView.class);
	
	/**
	* Method : getContentType
	* 작성자 : 이중석
	* 변경이력 :
	* @return
	* Method 설명 :
	*/
	@Override
	public String getContentType() {
		return "img";
	}

	/**
	* Method : render
	* 작성자 : 이중석
	* 변경이력 :
	* @param model
	* @param request
	* @param response
	* @throws Exception
	* Method 설명 :
	*/
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserVO userVo = (UserVO) model.get("userVo");
		// path정보로 file을 읽어 들여서 
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FileInputStream fis = null; 
		
		// 사용자가 업로드한 파일이 존재할 경우 : path 
		File file = new File(userVo.getUser_path()); 
		logger.debug("userVo.getUser_path() : [{}]", userVo.getUser_path());
		try {
			fis = new FileInputStream(file);
			byte[] buffer = new byte[512];
			
			//response객체에 스트림으로 써준다. 
			while( fis.read(buffer, 0, 512) != -1){
				sos.write(buffer); 
			} 
			fis.close(); 
			sos.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		
	}

	
	
}
