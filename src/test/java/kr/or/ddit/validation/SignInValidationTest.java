/**
 * 
 */
package kr.or.ddit.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import kr.or.ddit.user.model.UserVO;

/**
* SignInValidationTest.java
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
public class SignInValidationTest{

private Validator validator; 	// Validation체크
	
	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	/**
	* Method : signInValidationSuccessTest
	* 작성자 : 이중석
	* 변경이력 :
	* @throws Exception
	* Method 설명 : 사용자 회원가입 유효성 검사 성공 테스트
	*/
	@Test@Ignore
	public void signInValidationSuccessTest(){
		/***Given***/
		// 유효성 검사에서 성공할 객체
		UserVO successUserVo = new UserVO();
		/***When***/
		
		// 유효성 체크에 성공할 아이디값  : 영어로 시작하고 영어 포함 숫자 4~12글자 가능
		successUserVo.setUser_id("a123456789");
		
		// 유효성 체크에 성공할 이메일값  : 이메일 형식에 맞게 입력
		successUserVo.setUser_email("diat1450@gmail.com");
		
		// 유효성 체크에 성공할 비밀번호 값 : 숫자, 영어, 특수문자 1개이상의 8~16글자
		successUserVo.setUser_pw("a1!asdfw123");
		
		// 유효성 체크에 성공할 이름 값 : 한글 2~4글자
		successUserVo.setUser_name("하하하하");
		
		Set<ConstraintViolation<UserVO>> successViolations = validator.validate(successUserVo);
		/***Then***/
		assertTrue(successViolations.isEmpty());
	}

	/**
	 * Method : signInValidationFailTest
	 * 작성자 : 이중석
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 회원가입 유효성 검사 실패 테스트
	 */
	@Test@Ignore
	public void signInValidationFailTest(){
		/***Given***/
		// 유효성 검사에서 실패할 객체
		UserVO failUserVo = new UserVO();
		/***When***/
		
		// 유효성 체크에 실패할 아이디값  : 영어로 시작하고 영어 포함 숫자 4~12글자 가능
		failUserVo.setUser_id("123456789a");
		
		// 유효성 체크에 실패할 이메일값  : 이메일 형식에 맞게 입력
		failUserVo.setUser_email("diat1450@123.231");
		
		// 유효성 체크에 실패할 비밀번호 값 : 숫자, 영어, 특수문자 1개이상의 8~16글자
		failUserVo.setUser_pw("a1asdfw123");
		
		// 유효성 체크에 실패할 이름 값 : 한글 2~4글자
		failUserVo.setUser_name("하");
		
		Set<ConstraintViolation<UserVO>> failViolations = validator.validate(failUserVo);
		/***Then***/
		assertFalse(failViolations.isEmpty());
	}
	

}
