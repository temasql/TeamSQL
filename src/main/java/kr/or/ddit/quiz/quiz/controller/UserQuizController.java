package kr.or.ddit.quiz.quiz.controller;

import java.util.ArrayList;
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
			logger.debug("유저 주관식 리스트 : {}", quizAndAnswerList);
			
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
	public String userEssayAnswer(Model model, @RequestBody Map<String, Object> map_1, QuizVO quizVO, String userAnswer) {
		String userAnswerArr[] = null;
		String result[] = null;
				
		
		logger.debug("ajax 유저 주관식 map_1 : {}", map_1);
		String quiz_id = (String) map_1.get("quiz_id");
		int quiz_id_1 = Integer.parseInt(quiz_id);
		
		quizVO.setQuiz_id(quiz_id_1);
		String quiz_right = (String) map_1.get("quiz_right");
		quizVO.setQuiz_right(quiz_right);
		userAnswer = (String) map_1.get("userAnswer");
		
		logger.debug("ajax 유저 주관식 userVO : {}", quizVO);
		Map<String, Object> map = service.userAnswerList(quizVO);
		List<QuizAndAnswerVO> quizAndAnswerList = (List<QuizAndAnswerVO>) map.get("quizAndAnswerList");
//		List<QuizAnswerVO> quizAnswerList = (List<QuizAnswerVO>) map.get("quizAnswerList");
		
//		logger.debug("유저 주관식 리스트 : {}", quizAnswerList);
		logger.debug("유저 주관식 리스트 : {}", quizAndAnswerList);
		
		logger.debug("주관식 : {}", userAnswer);
		
		userAnswer = userAnswer.trim();
		userAnswer = userAnswer.replaceAll("\r\n", " ");
		userAnswer = userAnswer.replaceAll("\r", " ");
		userAnswer = userAnswer.replaceAll("\n", " ");
		
		logger.debug("비교 전 : {}", userAnswer);
		
		userAnswerArr = userAnswer.split(" ");
		
		model.addAttribute("quizAndAnswerList", quizAndAnswerList);
		
		List<QuizAndAnswerVO> list = new ArrayList<QuizAndAnswerVO>();
		
		for(String userArr : userAnswerArr)
			logger.debug("배열 값 : {}", userArr);
		
		QuizAndAnswerVO vo = new QuizAndAnswerVO();
		
		for(int i=0; i<userAnswerArr.length; i++) {
			if(!userAnswerArr[i].equals("")) {
				list = new ArrayList<QuizAndAnswerVO>();
				logger.debug("포문안의 : {}", userAnswerArr[i]);
				vo.setQuiz_answer(userAnswerArr[i]);
				list.add(vo);
				
				logger.debug("list 값 : {}", list);
//				userAnswerArr_1 = userAnswerArr;
//				logger.debug("userAnswerArr_1 이동 중 : {}", userAnswerArr_1[i]);
			}
		}
		
		
		String msg = null;
		
		for(int i=0; i<list.size(); i++) {
			if(!list.get(i).getQuiz_answer().equals("")) {
//				logger.debug("quizAndAnswerList.get(i).getQuiz_answer() : {}", quizAndAnswerList.get(i).getQuiz_answer());
				logger.debug("포문안의 DB : {}", quizAndAnswerList.get(i).getQuiz_answer());
				logger.debug("포문안의 list : {}", list.get(i).getQuiz_answer());
					
				
				if(list.get(i).getQuiz_answer().equals(quizAndAnswerList.get(i).getQuiz_answer())) {
					msg = "정답";
//				model.addAttribute("msg", "정답");
				}else {
					msg = "오답";
//				model.addAttribute("msg", "오답");
				}
			}
		}
		
		logger.debug("msg : {}", msg);
		
		model.addAttribute("msg", msg);
		
		return "jsonView";
	}
}
