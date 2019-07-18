package kr.or.ddit.user.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.user.model.UserVO;

/**
* UserController.java
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
* 일반회원에 관한 컨트롤러
*
* </pre>
*/
@RequestMapping("/user")
@Controller
public class UserController {

   private static final Logger logger = LoggerFactory.getLogger(UserController.class);

   /**
   * Method : signInGet
   * 작성자 : 이중석
   * 변경이력 :
   * @return
   * Method 설명 : 회원가입 페이지 요청
   */
   @RequestMapping(path = "/signIn", method = RequestMethod.GET)
   public String signInGet() {
      
      return "/user/signIn.tiles";
   }
   
   /**
   * Method : signInPost
   * 작성자 : 이중석
   * 변경이력 :
   * @param userVo
   * @param result
   * @return
   * Method 설명 : 회원가입 페이지 응답
   */
   @RequestMapping(path = "/signIn", method = RequestMethod.POST)
   public String signInPost(@Valid UserVO userVo, BindingResult result) {
      
      logger.debug("UserController userVo : {}", userVo);
      
      if(result.hasErrors()) {
         return "/user/signIn.tiles";
      }
      
      return "/user/login.tiles";
   }
   
}