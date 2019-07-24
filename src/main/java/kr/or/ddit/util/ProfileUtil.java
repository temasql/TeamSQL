package kr.or.ddit.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.user.model.UserVO;

public class ProfileUtil {
	
		private static final String UPLOAD_PATH = "d:"+ File.separator +"teamSQL"+ File.separator;
	
	/**
	 * Method : getExt
	 * 작성자 : PC25
	 * 변경이력 :
	 * @param fileName
	 * @return
	 * Method 설명 : 파일명으로부터 파일 확장자를 반환한다.
	 */
	public static String getExt(String fileName) {
		if (fileName.contains(".")) {
			int start = fileName.lastIndexOf('.');
			return fileName.substring(start);
		}
		return "";
	}
	
	/**
	 * Method : setMkdir
	 * 작성자 : PC25
	 * 변경이력 :
	 * @return
	 * Method 설명 : 년,월 업로드 폴더가 존재하는지 체크 없을경우 폴더 생성
	 */
	public static Map<String, Object> setMkdir(){
		//년도에 해당하는 폴더가 있는지, 년도안에 월에 해당하는 폴더가 있는지
		Date dt = new Date();
		SimpleDateFormat yyyyMMSdf = new SimpleDateFormat("yyyyMM");
		
		String yyyyMM = yyyyMMSdf.format(dt);
		
		String yyyy = yyyyMM.substring(0, 4);
		String mm = yyyyMM.substring(4, 6);
		
		String sp = File.separator;
		
		File teamSQLFolder = new File("d:"+ File.separator +"teamSQL");
		// teamSQL 폴더로 넘어갔을때 해당  폴더를 생성한다.
		if(!teamSQLFolder.exists()){
			teamSQLFolder.mkdir();
		}
		
		File yyyyFolder = new File(UPLOAD_PATH + yyyy);
		// 신규년도로 넘어갔을때 해당 년도의 폴더를 생성한다.
		if(!yyyyFolder.exists()){
			yyyyFolder.mkdir();
		}
		
		//월에 해당하는 폴더가 있는지 확인
		File mmFolder = new File(UPLOAD_PATH + yyyy + sp + mm);
		if(!mmFolder.exists()){
			mmFolder.mkdir();
		}
		
		//profile에 해당하는 폴더가 있는지 확인
		File profileFolder = new File(UPLOAD_PATH + yyyy + sp + mm + sp + "profile");
		if(!profileFolder.exists()){
			profileFolder.mkdir();
		}
		
		String uploadPath = UPLOAD_PATH + yyyy + sp + mm + sp + "profile";
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("uploadPath", uploadPath);
		return resultMap;
	}
	
	public static String insertProfile(MultipartFile profile, UserVO userVo) {
		if (profile.getSize() > 0 && profile != null) {
			String fileName = profile.getOriginalFilename();
			String ext = getExt(fileName);
			String sp = File.separator;
			
			Map<String, Object> resultMap = setMkdir();
			String uploadPath =  (String) resultMap.get("uploadPath");
			
			// 파일 디스크에 쓰기
			String filePath = uploadPath + sp + UUID.randomUUID().toString() + ext;
			userVo.setUser_path(filePath);
			try {
				profile.transferTo(new File(filePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			return filePath;
		}else {
			HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			
			return req.getServletContext().getRealPath(File.separator + "resources" + File.separator + "img"+ File.separator +"user.png");
		}
	}
	
	public static String updateProfile(MultipartFile profile, UserVO userVo) {
		if (profile.getSize() > 0 && profile != null) {
			String fileName = profile.getOriginalFilename();
			String ext = getExt(fileName);
			String sp = File.separator;
			
			Map<String, Object> resultMap = setMkdir();
			String uploadPath =  (String) resultMap.get("uploadPath");
			
			// 파일 디스크에 쓰기
			String filePath = uploadPath + sp + UUID.randomUUID().toString() + ext;
			userVo.setUser_path(filePath);
			try {
				profile.transferTo(new File(filePath));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			return filePath;
		}else if(userVo.getUser_path() != null && !(userVo.getUser_path().equals(""))) {
			return userVo.getUser_path();
		}else {
			HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			
			return req.getServletContext().getRealPath(File.separator + "resources" + File.separator + "img"+ File.separator +"user.png");
		}
	}

}
