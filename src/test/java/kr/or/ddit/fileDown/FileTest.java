package kr.or.ddit.fileDown;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;

/**
* FileTest.java
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
* 파일 테스트를 틀을 위해 임시로 만든 클래스입니다.
*
* </pre>
*/
public class FileTest extends ControllerTestEnv{
	
	/**
	* Method : uploadTest
	* 작성자 : OWNER
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 파일테스트 틀입니다.
	*/
	@Test
	public void uploadTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile files = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		
		/***When***/
		MvcResult mvcResult =  mockMvc.perform(fileUpload("/file/upload")
											.file(files))
											.andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("", "");
	}
}
