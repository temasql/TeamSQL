package kr.or.ddit.quiz.quiz.controller;

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
		quiz_answer = quiz_answer.substring(0, quiz_answer.lastIndexOf(";"));
		logger.debug("quiz_answer : {}",quiz_answer);
		
		String quiz_right = (String) map_1.get("quiz_right");
		String adminAnswer = (String) map_1.get("adminAnswer");
		adminAnswer = adminAnswer.substring(0, adminAnswer.lastIndexOf(";"));
		
		quizAndAnswerVO.setQuiz_id(quiz_id);
		quizAndAnswerVO.setQuiz_answer(quiz_answer);
		quizAndAnswerVO.setQuiz_right(quiz_right);
		logger.debug("quizAndAnswerVO : {}", quizAndAnswerVO);
		
		
		List<UserVO> userAnswerList = service.answerCompare(quizAndAnswerVO.getQuiz_answer());
		List<UserVO> adminAnswerList = service.answerCompare(adminAnswer);
		
		//null값을 "null"로 바꾸는 작업
		for(int i=0; i < userAnswerList.size(); i++) {
			if(userAnswerList.get(i).getUser_path() == null) {
				UserVO userVO = userAnswerList.get(i);
				userAnswerList.remove(i);
				userVO.setUser_path("null");
				userAnswerList.add(i, userVO);
			}
		}
		for(int i=0; i < adminAnswerList.size(); i++) {
			if(adminAnswerList.get(i).getUser_path() == null) {
				UserVO userVO = adminAnswerList.get(i);
				adminAnswerList.remove(i);
				userVO.setUser_path("null");
				adminAnswerList.add(i, userVO);
			}
		}
		
		//리스트 비교
		String msg = "";
		for(int i=0; i< userAnswerList.size(); i++) {
			if(userAnswerList.get(i).equals(adminAnswerList.get(i))) {
				msg = "정답";
				model.addAttribute("msg", msg);
			}else {
				msg = "오답";
				model.addAttribute("msg", msg);
				break;
			}
		}
		
		logger.debug("userList : {}", userAnswerList);
		logger.debug("adminAnswerList : {}", adminAnswerList);
		
		return "jsonView";
	}
}
