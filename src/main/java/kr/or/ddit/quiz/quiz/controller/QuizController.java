package kr.or.ddit.quiz.quiz.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.quiz.quiz.model.QuizVO;
import kr.or.ddit.quiz.quiz.service.IQuizService;
import kr.or.ddit.user.model.UserVO;

//@RequestMapping("/quiz")
@Controller
public class QuizController {
	private static final Logger logger = LoggerFactory.getLogger(QuizController.class);
	
	@Resource(name="quizService")
	private IQuizService service;

	/**
	* Method : quizeMain
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 퀴즈 메인페이지로 이동하는 메서드
	*/
	@RequestMapping(path =  "/quizMain", method = RequestMethod.GET)
	public String quizeMain() {
		logger.debug("퀴즈");
		return "/quiz/quizMain.tiles";
	}

	//OX퀴즈화면 이동 
	/**
	* Method : quizOX
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 사용자면 OX퀴즈 문제화면으로 이동, 
	* 			  관리자면 OX퀴즈 관리게시판화면으로 이동
	* 			 OX퀴즈에 해당하는 리스트 출력
	*/
	@RequestMapping("/quizOX")
	public String quizOX(HttpSession session, Model model, String quiz_right) {
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		logger.debug("quiz_right : {}", quiz_right);
		
		//로그인한 사람이 관리자일때 OX퀴즈 리스트로 이동하는 조건문
		if(userVO.getUser_right().equals("A")) {
			
			
			model.addAttribute("quiz_right", "02");
			model.addAttribute("quizName", "OX 퀴즈");
			return "/admin/quizMG/quizList.tiles";
		}
		
		return "/quiz/quizOX.tiles";
	}
	
	/**
	* Method : quizOX
	* 작성자 : PC19
	* 변경이력 :
	* @param model
	* @param quiz_right
	* @return
	* Method 설명 : ajax에서 호출 시 해당 게시판 리스트 반환
	*/
	@RequestMapping(path = "/quizOX", method = RequestMethod.POST)
	public String quizOX(Model model, String quiz_right) {
		PageVo pageVO = new PageVo();
		
		List<QuizVO> quizList = service.quizList(quiz_right, pageVO);
		int quizCnt = service.quizListCnt(quiz_right);
		
		int paginationSize = (int) Math.ceil((double)quizCnt/quizList.size());
		
		model.addAttribute("quizList", quizList);
		model.addAttribute("pagination", paginationSize);
		model.addAttribute("pageVO", pageVO);
		
		return "admin/quizMG/quizListAjaxHtml";
	}
	
	@RequestMapping(path = "/insertOX", method=RequestMethod.GET)
	public String insertOX() {
		
		return "/admin/quizMG/quizOX/quizOXInsert.tiles";
	}
	/**
	* Method : quizShort
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 사용자면 단답식퀴즈 문제화면으로 이동, 관리자면 단답식퀴즈 관리게시판화면으로 이동
	*/
	@RequestMapping(path =  "/quizShort", method = RequestMethod.GET)
	public String quizShort(HttpSession session, Model model, String quiz_right) {
		
		return "/quiz/quizShort.tiles";
	}
	
	/**
	* Method : quizMultiple
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 : 사용자면 객관식퀴즈 문제화면으로 이동, 관리자면 객관식퀴즈 관리게시판화면으로 이동
	*/
	@RequestMapping("/quizMultiple")
	public String quizMultiple() {
		
		return "/quiz/quizMultiple.tiles";
	}
	
	//주관식 퀴즈
	/**
	* Method : quizEssay
	* 작성자 : PC19
	* 변경이력 :
	* @return
	* Method 설명 :사용자면 주관식퀴즈 문제화면으로 이동, 관리자면 주관식퀴즈 관리게시판화면으로 이동
	*/
	@RequestMapping("/quizEssay")
	public String quizEssay() {
		
		return "/quiz/quizEssay.tiles";
	}
}
