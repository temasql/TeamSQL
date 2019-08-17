package kr.or.ddit.quiz.quiz.controller;

import java.sql.SQLSyntaxErrorException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.quiz.quiz.model.QuizAndAnswerVO;
import kr.or.ddit.quiz.quiz.model.QuizVO;
import kr.or.ddit.quiz.quiz.service.IQuizService;
import kr.or.ddit.quiz.quiz_example.model.QuizExampleVO;
import kr.or.ddit.user.model.UserVO;

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
		
		quizAndAnswerVO = service.userReadQuiz(quiz_right);
		
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
			
			model.addAttribute("quizAndAnswerVO", quizAndAnswerVO);
			
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
			
			logger.debug("마지막 문제");
			
			return "jsonView";
		}
		
		
		String returnResult = "";
		
		//객관식
		if(quizVo.getQuiz_right().equals("01")) {
			List<QuizExampleVO> quizExampleList = service.userMultipleList(quizAndAnswerVO);
			
			logger.debug("객관식 리스트 : {}", quizExampleList);
			
			model.addAttribute("quizExampleList", quizExampleList);
			
			returnResult = "/quiz/ajaxHtml/userMultipleAjaxHtml";
		}else if(quizVo.getQuiz_right().equals("02")) {
			
			returnResult = "/quiz/ajaxHtml/userOXAjaxHtml";
		}else if(quizVo.getQuiz_right().equals("03")) {
			logger.debug("quiz_right 입니다 : {}", quizVo.getQuiz_right());
			
			returnResult = "/quiz/ajaxHtml/userShortAjaxHtml";
		}else{
			
			returnResult = "/quiz/ajaxHtml/userEssayAjaxHtml";
		}
		
		model.addAttribute("quizAndAnswerVO", quizAndAnswerVO);
		
		return returnResult;
	}
	
	/**
	* Method : userEssayAnswer
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param map_1
	* @param quizVO
	* @param userAnswer
	* @return
	* Method 설명 : 사용자가 주관식 정답 버튼 클릭 시 DB에서
	* 			데이터조회 후 
	* 			사용자가 입력 한 값과 DB의 데이터 값과 일치하는 지 비교
	*/
//	@ResponseBody
	@RequestMapping(path = "/userEssayAnswer", method = RequestMethod.POST)
	public String userEssayAnswer(Model model, @RequestBody Map<String, Object> map_1, QuizAndAnswerVO quizAndAnswerVO) {
		
		logger.debug("값 비교 map_1 : {}", map_1);
		
		String quiz_id_1 = (String) map_1.get("quiz_id");
		int quiz_id = Integer.parseInt(quiz_id_1);
		
		String quiz_answer = (String) map_1.get("quiz_answer");
		logger.debug("quiz_answer : {}",quiz_answer);
		String msg = "";
		try {
			quiz_answer = quiz_answer.substring(0, quiz_answer.lastIndexOf(";"));
			logger.debug("; 지우기 : {}", quiz_answer);
		} catch (Exception e) {
			logger.debug("; 지웠나? : {}", quiz_answer);
			msg = "세미콜론이 없습니다.";
			model.addAttribute("msg", msg);
			return "jsonView";
		}
		logger.debug("quiz_answer : {}",quiz_answer);
		
		String quiz_right = (String) map_1.get("quiz_right");
		String adminAnswer = (String) map_1.get("adminAnswer");
		adminAnswer = adminAnswer.substring(0, adminAnswer.lastIndexOf(";"));
		
		quizAndAnswerVO.setQuiz_id(quiz_id);
		quizAndAnswerVO.setQuiz_answer(quiz_answer);
		quizAndAnswerVO.setQuiz_right(quiz_right);
		logger.debug("quizAndAnswerVO : {}", quizAndAnswerVO);
		
		
		List<Map> adminAnswerList = service.answerCompare(adminAnswer);;
		List<Map> userAnswerList = null;
		try {
			userAnswerList = service.answerCompare(quizAndAnswerVO.getQuiz_answer());
		} catch (Exception e) {
			msg = "잘못된 쿼리문 입니다.";
			model.addAttribute("msg", msg);
			return "jsonView";
		}
		
		
		Collections.sort(userAnswerList, new Comparator<Map>() {
			@Override
			public int compare(Map o1, Map o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
		
		Collections.sort(adminAnswerList, new Comparator<Map>() {
			@Override
			public int compare(Map o1, Map o2) {
				return o1.toString().compareTo(o2.toString());
			}
		});
		
		boolean result = userAnswerList.equals(adminAnswerList);
		
		if(result) {
			msg = "정답";
			model.addAttribute("msg", msg);
		}else {
			msg = "오답";
			model.addAttribute("msg", msg);
		}
		
		logger.debug("userList : {}", userAnswerList);
		logger.debug("adminAnswerList : {}", adminAnswerList);
		
		return "jsonView";
	}
}
