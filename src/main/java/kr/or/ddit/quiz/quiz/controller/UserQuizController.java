package kr.or.ddit.quiz.quiz.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.quiz.quiz.model.QuizAndAnswerVO;
import kr.or.ddit.quiz.quiz.model.QuizVO;
import kr.or.ddit.quiz.quiz.service.IQuizService;
import kr.or.ddit.quiz.quiz_answer.model.QuizAnswerVO;
import kr.or.ddit.quiz.quiz_example.model.QuizExampleVO;

@Controller
public class UserQuizController {
	private static final Logger logger = LoggerFactory.getLogger(UserQuizController.class);
	
	@Resource(name="quizService")
	private IQuizService service;
	
	/**
	* Method : userReadQuiz
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quiz_right
	* @return
	* Method 설명 : 사용자 각 퀴즈 푸는 화면으로 이동
	*/
	@RequestMapping(path = "/userQuiz", method = RequestMethod.GET)
	public String userReadQuiz(Model model,QuizVO quizVO, String quiz_right, HttpSession session) {
		
		//유저가 각 퀴즈 화면으로 이동할때 첫 문제를 조회하는 메서드
		QuizAndAnswerVO quizAndAnswerVO = null;
		List<QuizAndAnswerVO> quizAndAnswerList = null;
		List<QuizAnswerVO> quizAnswerList = null;
		
		if(quiz_right.equals("01")||quiz_right.equals("02")||quiz_right.equals("03")) {
			quizAndAnswerVO = service.userReadQuiz(quiz_right);
		}else {
			logger.debug("유저 주관식 userVO : {}", quizVO);
			Map<String, Object> map = service.userAnswerList(quizVO);
			quizAndAnswerList = (List<QuizAndAnswerVO>) map.get("quizAndAnswerList");
			quizAnswerList = (List<QuizAnswerVO>) map.get("quizAnswerList");
		}
		
		session.setAttribute("quizIdSum", "");
		String returnResult = "";
		
		if(quiz_right.equals("01")) {
			
			List<QuizExampleVO> exampleList = service.userMultipleList(quizAndAnswerVO);
			
			model.addAttribute("quizAndAnswerVO", quizAndAnswerVO);
			model.addAttribute("exampleList", exampleList);
			
			returnResult = "/quiz/quizMultiple.tiles";
		}else if(quiz_right.equals("02")) {
			
			logger.debug("유저 퀴즈 조회 quizAndAnswerVO : {}", quizAndAnswerVO);
			
			model.addAttribute("quizAndAnswerVO", quizAndAnswerVO);
			
			returnResult = "/quiz/quizOX.tiles";
		}else if(quiz_right.equals("03")) {
			
			model.addAttribute("quizAndAnswerVO", quizAndAnswerVO);
			
			logger.debug("단답식 퀴즈 : {}", quizAndAnswerVO);
			
			returnResult = "/quiz/quizShort.tiles";
		}else if(quiz_right.equals("04")) {
			
			logger.debug("유저 주관식 리스트 : {}", quizAnswerList);
			
			model.addAttribute("quizAnswerList", quizAnswerList);
			model.addAttribute("quizAndAnswerList", quizAndAnswerList);
			
			returnResult = "/quiz/quizEssay.tiles";
		}
		
		return returnResult;
	}
	
	/**
	* Method : quizNextRead
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quizAndAnswerVO
	* @return
	* Method 설명 : 유저가 해당 문제를 풀었을 때 다음 문제를 조회하는 메서드
	*/
//	@ResponseBody
	@RequestMapping(path = "/userQuizRead", method = RequestMethod.POST)
	public String quizNextRead(Model model, QuizVO quizVo, HttpSession session) {
		
		logger.debug("퀴즈 quizVO : {}", quizVo);
		
		String quizIdSum = (String) session.getAttribute("quizIdSum");
		quizIdSum += quizVo.getQuiz_id() + ", ";
		session.setAttribute("quizIdSum", quizIdSum);
		
		String quiz_id = quizIdSum.substring(0, quizIdSum.lastIndexOf(", "));
		
		quizVo.setQuizIdSum(quiz_id);
		
		QuizAndAnswerVO quizAndAnswerVO = service.userNextQuiz(quizVo);
		
		
		logger.debug("quizAndAnswerVO : {}", quizAndAnswerVO);
		
		//가져온 데이터가 없을 때
		if(quizAndAnswerVO == null) {
			model.addAttribute("msg", "마지막 문제입니다.");
			return "/quiz/ajaxHtml/userMultipleAjaxHtml";
		}
		
		
		String returnResult = "";
		
		//객관식
		if(quizVo.getQuiz_right().equals("01")) {
			List<QuizExampleVO> quizExampleList = service.userMultipleList(quizAndAnswerVO);
			
			model.addAttribute("quizExampleList", quizExampleList);
			
			returnResult = "/quiz/ajaxHtml/userMultipleAjaxHtml";
		}else if(quizVo.getQuiz_right().equals("02")) {
			
			returnResult = "/quiz/ajaxHtml/userOXAjaxHtml";
		}else if(quizVo.getQuiz_right().equals("03")) {
			
			returnResult = "/quiz/ajaxHtml/userShortAjaxHtml";
		}else{
			
			returnResult = "";
		}
		
		model.addAttribute("quizAndAnswerVO", quizAndAnswerVO);
		
		return returnResult;
	}
}
