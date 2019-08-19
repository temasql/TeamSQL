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

import kr.or.ddit.page.model.PageVo;
import kr.or.ddit.quiz.quiz.model.QuizAndAnswerVO;
import kr.or.ddit.quiz.quiz.model.QuizVO;
import kr.or.ddit.quiz.quiz.service.IQuizService;
import kr.or.ddit.quiz.quiz_answer.model.QuizAnswerVO;
import kr.or.ddit.quiz.quiz_example.model.QuizExampleVO;
import kr.or.ddit.user.model.UserVO;

@Controller
public class QuizController {
	private static final Logger logger = LoggerFactory.getLogger(QuizController.class);
	
	@Resource(name="quizService")
	private IQuizService service;
	
	@RequestMapping(path =  "/quizRealMain", method = RequestMethod.GET)
	public String quizRealMain(HttpSession session) {
		
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		
		if(userVO.getUser_right().equals("C")) {
			return "/quiz/quizRealMain.tiles";
		}
		
		return "/quiz/quizMain.tiles";
	}
	
	/**
	* Method : quizeMain
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : 퀴즈 메인페이지로 이동하는 메서드
	*/
	@RequestMapping(path =  "/quizMain", method = RequestMethod.GET)
	public String quizeMain(HttpSession session) {
		logger.debug("퀴즈");
		
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
//		if(userVO.getUser_right().equals("C")) {
//			return "/quiz/quizUserMain.tiles";
//		}
		
		return "/quiz/quizMain.tiles";
	}

	//OX퀴즈화면 이동 
	/**
	* Method : quizOX
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : 사용자면 OX퀴즈 문제화면으로 이동, 
	* 			  관리자면 OX퀴즈 관리게시판화면으로 이동
	* 			 OX퀴즈에 해당하는 리스트 출력
	*/
	@RequestMapping("/quizOX")
	public String quizOX(HttpSession session, Model model, String quiz_right) {
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		logger.debug("quizOX quiz_right : {}", quiz_right);
		
		//로그인한 사람이 관리자일때 OX퀴즈 리스트로 이동하는 조건문
		if(userVO.getUser_right().equals("A")) {
			
			
			model.addAttribute("quiz_right", quiz_right);
			model.addAttribute("quizName", "OX 퀴즈");
			return "/admin/quizMG/quizList.tiles";
		}
		
		return "/quiz/quizOX.tiles";
	}
	
	/**
	* Method : quizOX
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quiz_right
	* @return
	* Method 설명 : ajax에서 호출 시 해당 게시판 리스트 반환
	*/
	@RequestMapping(path = "/quizList", method = {RequestMethod.POST, RequestMethod.GET})
	public String quizOX(Model model, String quiz_right, PageVo pageVO) {
		logger.debug("quizOX post quiz_right : {}", quiz_right);
		
//		PageVo pageVO = new PageVo();
		
		List<QuizVO> quizList = service.quizList(quiz_right, pageVO);
		int quizCnt = service.quizListCnt(quiz_right);
		
		int paginationSize = (int) Math.ceil((double)quizCnt/10);
		/*
		 	quizList.size()의 값이 변동이 있어서
		 	10으로 고정 시켜놨음
		 	화면에 출력되는 리스트의 양은 최대 10개로 제한 할것이 때문이다.
		 */
		
		logger.debug("quizCnt : {}", quizCnt);
		logger.debug("quizList : {}", quizList.size());
		logger.debug("paginationSize : {}", paginationSize);
		
		model.addAttribute("quiz_right", quiz_right);
		model.addAttribute("quizList", quizList);
		model.addAttribute("pagination", paginationSize);
		model.addAttribute("pageVO", pageVO);
		
		return "/admin/quizMG/quizListAjaxHtml";
	}
	
	/**
	* Method : insertOX
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quiz_right
	* @return
	* Method 설명 : OX퀴즈 추가 화면으로 이동
	*/
	@RequestMapping(path = "/insertOX", method=RequestMethod.GET)
	public String insertOX(Model model, String quiz_right) {
		logger.debug("insertOX get quiz_right : {}", quiz_right);
		
		model.addAttribute("quiz_right", quiz_right);
		
		return "/admin/quizMG/quizOX/quizOXInsert.tiles";
	}
	
	/**
	* Method : insertOX
	* 작성자 : 손주형
	* 변경이력 :
	* @param quizVO
	* @param quizAnswerVO
	* @return
	* Method 설명 : 퀴즈 추가 버튼 클릭시 DB에 추가되는 메서드
	*/
	@RequestMapping(path = "/insertQuiz", method=RequestMethod.POST)
	public String insertOX(Model model, HttpSession session, QuizVO quizVO, QuizAnswerVO quizAnswerVO) {
		logger.debug("quizVO : {}", quizVO);
		logger.debug("quizAnswerVO : {}", quizAnswerVO);
		
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		
		logger.debug("user_id : {}", userVO.getUser_id());
		quizVO.setUser_id_fk(userVO.getUser_id());
		
		logger.debug("insertQuiz : {}", quizVO);
		logger.debug("insertQuiz : {}", quizAnswerVO);
		
		int result = service.insert_question(quizVO, quizAnswerVO);
		
		logger.debug("등록 성공 : {}", result);
		
		model.addAttribute("quiz_right", quizVO.getQuiz_right());
		
		if(quizVO.getQuiz_right().equals("01")) {
			model.addAttribute("quizName", "객관식 퀴즈");
		}else if(quizVO.getQuiz_right().equals("02")){
			model.addAttribute("quizName", "OX 퀴즈");
		}else if(quizVO.getQuiz_right().equals("03")) {
			model.addAttribute("quizName", "단답식 퀴즈");
		}else {
			model.addAttribute("quizName", "주관식 퀴즈");
		}
		
		return "/admin/quizMG/quizList.tiles";
	}
	
	/**
	* Method : readOX
	* 작성자 : 손주형
	* 변경이력 :
	* @param quiz_id
	* @param quiz_right
	* @return
	* Method 설명 : 관리자 퀴즈 조회 화면으로 이동
	*/
	@RequestMapping(path="/readOX", method = RequestMethod.GET)
	public String readOX(Model model, QuizVO quizVO, HttpSession session) {
		logger.debug("QuizVO : {}", quizVO);
		
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		logger.debug("userVO : {}", userVO);
		
		quizVO.setUser_id_fk(userVO.getUser_id());
		
		
		if(quizVO.getQuiz_right().equals("01")) {
			model.addAttribute("quizName", "객관식 퀴즈");
			logger.debug("객관식 등록 후 조회 했을 때 일루 오나?");
			
			Map<String, Object> map = service.multipleQuizList(quizVO);
			
			QuizAndAnswerVO quizAndAnswerVO1 = (QuizAndAnswerVO) map.get("quizAndAnswerVO");
			List<QuizExampleVO> exampleList = (List<QuizExampleVO>) map.get("exampleList");
			
			model.addAttribute("quiz_right", quizVO.getQuiz_right());
			model.addAttribute("quizAndAnswerVO", quizAndAnswerVO1);
			model.addAttribute("exampleList", exampleList);
			
			return "/admin/quizMG/quizMultiple/quizMultipleDetail.tiles";
		}else if(quizVO.getQuiz_right().equals("02")){
			model.addAttribute("quizName", "OX 퀴즈");
		}else if(quizVO.getQuiz_right().equals("03")) {
			model.addAttribute("quizName", "단답식 퀴즈");
		}else {
			QuizAndAnswerVO quizAndAnswerVO = service.readQuiz(quizVO);
			
			logger.debug("주관식 문제 조회");
			logger.debug("quizAndAnswerVO : {}", quizAndAnswerVO);
			
			model.addAttribute("quizAndAnswerVO", quizAndAnswerVO);
			model.addAttribute("quiz_right", quizVO.getQuiz_right());
			model.addAttribute("quizName", "주관식 퀴즈");
			
			return "/admin/quizMG/quizEssay/quizEssayDetail.tiles";
		}
		QuizAndAnswerVO quizAndAnswerVO = service.readQuiz(quizVO);
		
		model.addAttribute("quizAndAnswerVO", quizAndAnswerVO);
		model.addAttribute("quiz_right", quizVO.getQuiz_right());
		
		return "/admin/quizMG/quizOX/quizOXDetail.tiles";
	}
	
	/**
	* Method : updateOX
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quizVO
	* @param quiz_right
	* @return
	* Method 설명 : OX퀴즈 수정 화면으로 이동
	*/
	@RequestMapping(path = "/updateOX", method = RequestMethod.GET)
	public String updateOX(Model model, QuizVO quizVO, String quiz_right) {
		
		QuizAndAnswerVO quizAndAnswerVO = service.readQuiz(quizVO);
		
		model.addAttribute("quiz_right", quiz_right);
		model.addAttribute("quizAndAnswerVO", quizAndAnswerVO);
		return "/admin/quizMG/quizOX/quizOXUpdate.tiles";
	}
	
	/**
	* Method : updateOX
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quizAndAnswerVO
	* @param quiz_right
	* @return
	* Method 설명 : 퀴즈 수정 후 리스트 화면으로 전환
	*/
	@RequestMapping(path="/updateQuiz", method=RequestMethod.POST)
	public String updateOX(Model model, QuizAndAnswerVO quizAndAnswerVO, String quiz_right) {
		logger.debug("updateOX Post quizAndAnswerVO : {}", quizAndAnswerVO);
		
		int result = service.updateQuiz(quizAndAnswerVO);
		
		if(quiz_right.equals("01")) {
			model.addAttribute("quizName", "객관식 퀴즈");
		}else if(quiz_right.equals("02")){
			model.addAttribute("quizName", "OX 퀴즈");
		}else if(quiz_right.equals("03")) {
			model.addAttribute("quizName", "단답식 퀴즈");
		}else {
			model.addAttribute("quizName", "주관식 퀴즈");
		}
		
		if(result>0) {
			logger.debug("수정 성공");
		}
		
		model.addAttribute("quiz_right", quiz_right);
		
		return "/admin/quizMG/quizList.tiles";
	}
	
	/**
	* Method : updateQuiz
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quizVO
	* @param session
	* @param quiz_right
	* @return
	* Method 설명 : OX퀴즈를 제외한 나머지 퀴즈화면(동일한 화면)으로 이동
	*/
	@RequestMapping(path = "/updateQuiz", method = RequestMethod.GET)
	public String updateQuiz(Model model, QuizVO quizVO, HttpSession session, String quiz_right) {
		QuizAndAnswerVO quizAndAnswerVO = service.readQuiz(quizVO);
		
		if(quizVO.getQuiz_right().equals("01")) {
			model.addAttribute("quizName", "객관식 퀴즈");
		}else if(quizVO.getQuiz_right().equals("02")){
			model.addAttribute("quizName", "OX 퀴즈");
		}else if(quizVO.getQuiz_right().equals("03")) {
			model.addAttribute("quizName", "단답식 퀴즈");
		}else {
			model.addAttribute("quizName", "주관식 퀴즈");
		}
		
		model.addAttribute("quiz_right", quiz_right);
		model.addAttribute("quizAndAnswerVO", quizAndAnswerVO);
		return "/admin/quizMG/quizShort/quizShortUpdate.tiles";
	}
	
	
	/**
	* Method : updateMultipleQuiz
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quizVO
	* @return
	* Method 설명 : 객관식 수정 화면으로 이동
	*/
	@RequestMapping(path = "/updateMultipleQuiz", method=RequestMethod.GET)
	public String updateMultipleQuiz(Model model, QuizVO quizVO) {
		Map<String, Object> map = service.multipleQuizList(quizVO);
		
		QuizAndAnswerVO quizAndAnswerVO = (QuizAndAnswerVO) map.get("quizAndAnswerVO");
		List<QuizExampleVO> exampleList = (List<QuizExampleVO>) map.get("exampleList");
		
		model.addAttribute("quiz_right", quizVO.getQuiz_right());
		model.addAttribute("quizAndAnswerVO", quizAndAnswerVO);
		model.addAttribute("exampleList", exampleList);
		
		return "/admin/quizMG/quizMultiple/quizMultipleUpdate.tiles";
	}
	
	/**
	* Method : updateMultipleQuiz
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quizVO
	* @param quiz_right
	* @return
	* Method 설명 : 객관식 문제를 수정하는 메서드
	*/
	@RequestMapping(path = "updateMultipleQuiz", method = RequestMethod.POST)
	public String updateMultipleQuiz(HttpSession session, Model model, String[] example_content, QuizAndAnswerVO quizAndAnswerVO, QuizExampleVO quizExampleVO, QuizAnswerVO quizAnswerVO) {
		logger.debug("객관식 수정중");
		logger.debug("quizAndAnswerVO : {}", quizAndAnswerVO);
		logger.debug("quizExampleVO : {}", quizExampleVO);
		logger.debug("quizAnswerVO : {}", quizAnswerVO);
		logger.debug("");
		
		int result = service.updateMultipleQuiz(quizAndAnswerVO, quizExampleVO, example_content);
		logger.debug("객관식 수정 갯수 : {}", result);
		
		
		model.addAttribute("quiz_right", quizAndAnswerVO.getQuiz_right());
		return "/admin/quizMG/quizList.tiles";
	}
	
	
	
	/**
	* Method : deleteQuiz
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quiz_id
	* @param quiz_right
	* @return
	* Method 설명 : 선택한 퀴즈를 삭제하는 메서드
	*/
	@RequestMapping(path = "/deleteQuiz", method = RequestMethod.POST)
	public String deleteQuiz(Model model, int quiz_id, String quiz_right) {
		
		int result = service.deleteQuiz(quiz_id);
		
		if(result > 0) {
			logger.debug("삭제 갯수 : {}", result);
		}
		
		if(quiz_right.equals("01")) {
			model.addAttribute("quizName", "객관식 퀴즈");
		}else if(quiz_right.equals("02")){
			model.addAttribute("quizName", "OX 퀴즈");
		}else if(quiz_right.equals("03")) {
			model.addAttribute("quizName", "단답식 퀴즈");
		}else {
			model.addAttribute("quizName", "주관식 퀴즈");
		}
		
		model.addAttribute("quiz_right", quiz_right);
		
		return "/admin/quizMG/quizList.tiles";
	}
	
	/**
	* Method : quizShort
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : 사용자면 각 퀴즈 문제화면으로 이동, 관리자면 각 퀴즈 관리게시판화면으로 이동
	*/
	@RequestMapping(path =  "/quizListView", method = RequestMethod.GET)
	public String quizShort(HttpSession session, Model model, String quiz_right) {
		
		logger.debug("단답식 : quiz_right : {}", quiz_right);
		
		if(quiz_right.equals("01")) {
			model.addAttribute("quizName", "객관식 퀴즈");
		}else if(quiz_right.equals("02")){
			model.addAttribute("quizName", "OX 퀴즈");
		}else if(quiz_right.equals("03")) {
			model.addAttribute("quizName", "단답식 퀴즈");
		}else {
			model.addAttribute("quizName", "주관식 퀴즈");
		}
		
		model.addAttribute("quiz_right", quiz_right);
		
		return "/admin/quizMG/quizList.tiles";
	}
	
	/**
	* Method : insertShort
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quiz_right
	* @return
	* Method 설명 : 단답식 추가 화면 이동
	*/
	@RequestMapping(path="/insertShort", method = RequestMethod.GET)
	public String insertShort(Model model, String quiz_right) {
		logger.debug("객관식 GET");
		
		model.addAttribute("quiz_right", quiz_right);
		
		return "/admin/quizMG/quizShort/quizShortInsert.tiles";
	}
	
	/**
	* Method : insertMultiple
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quiz_right
	* @return
	* Method 설명 : 객관식 추가 화면으로 이동
	*/
	@RequestMapping(path = "/insertMultiple", method = RequestMethod.GET)
	public String insertMultiple(Model model, String quiz_right) {
		
		model.addAttribute("quiz_right", quiz_right);
		
		return "/admin/quizMG/quizMultiple/quizMultipleInsert.tiles";
	}
	
	/**
	* Method : insertMultiple
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quiz_right
	* @return
	* Method 설명 : 객관식 퀴즈 문제를 DB에 등록
	*/
	@RequestMapping(path = "/insertMultiple", method = RequestMethod.POST)
	public String insertMultiple(HttpSession session, Model model, String[] example_content, QuizVO quizVO, QuizExampleVO quizExampleVO, QuizAnswerVO quizAnswerVO) {
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		
		quizVO.setUser_id_fk(userVO.getUser_id());
		
		logger.debug("insertMultiple POST");
		logger.debug("quizVO : {}", quizVO);
		logger.debug("quizExampleVO : {}", quizExampleVO);
		logger.debug("quizAnswerVO : {}", quizAnswerVO);
		
		for(String exampleContent : example_content) {
			logger.debug("quizExampleVO : {}", exampleContent);
		}
		
		int result = service.insertMultipleQuiz(quizVO, quizAnswerVO, quizExampleVO, example_content);
		
		logger.debug("객관식 성공 갯수 : {}", result);
		
		model.addAttribute("quiz_right", quizVO.getQuiz_right());
		return "/admin/quizMG/quizList.tiles";
	}
	
	
	/**
	* Method : quizMultiple
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 : 사용자면 객관식퀴즈 문제화면으로 이동, 관리자면 객관식퀴즈 관리게시판화면으로 이동
	*/
	@RequestMapping("/quizMultiple")
	public String quizMultiple() {
		
		return "/quiz/quizMultiple.tiles";
	}
	
	/**
	* Method : quizEssay
	* 작성자 : 손주형
	* 변경이력 :
	* @return
	* Method 설명 :사용자면 주관식퀴즈 문제화면으로 이동, 관리자면 주관식퀴즈 관리게시판화면으로 이동
	*/
	@RequestMapping("/quizEssay")
	public String quizEssay() {
		
		return "/quiz/quizEssay.tiles";
	}
	
	/**
	* Method : insertEssay
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quiz_right
	* @return
	* Method 설명 : 관리자가 주관식 화면 등록으로 이동하는 메서드
	*/
	@RequestMapping(path = "/insertEssay", method = RequestMethod.GET)
	public String insertEssay(Model model, String quiz_right) {
		
		model.addAttribute("quiz_right", quiz_right);
		
		return "/admin/quizMG/quizEssay/quizEssayInsert.tiles";
	}
	
	/**
	* Method : insertEssay
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quizVO
	* @param quizAnswerVO
	* @param session
	* @return
	* Method 설명 : 주관식 문제 추가후 리스트화면으로 전환되는 메서드
	*/
	@RequestMapping(path = "/insertEssay", method = RequestMethod.POST)
	public String insertEssay(Model model, QuizVO quizVO, QuizAnswerVO quizAnswerVO, HttpSession session) {
		UserVO userVO = (UserVO) session.getAttribute("USER_INFO");
		
		quizVO.setUser_id_fk(userVO.getUser_id());
		
		String answer = quizAnswerVO.getQuiz_answer();
		
		quizAnswerVO.setQuiz_answer(answer);
		
		logger.debug("주관식 등록 quizVO : {}", quizVO);
		logger.debug("주관식 등록 quizAnswerVO : {}", quizAnswerVO);
		
		int result = service.insertEssay(quizVO, quizAnswerVO);
		logger.debug("주관식 등록 갯수 : {}", result);
		
		model.addAttribute("quiz_right", quizVO.getQuiz_right());
		return "/admin/quizMG/quizList.tiles";
	}
	
	/**
	* Method : updateEssay
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @param quizVO
	* @return
	* Method 설명 : 관리자 주관식 수정 화면으로 이동
	*/
	@RequestMapping(path = "/updateEssay", method =RequestMethod.GET)
	public String updateEssay(Model model, QuizVO quizVO) {
		
		Map<String, Object> map = service.quizAnswer(quizVO);
		
		QuizVO quizVO_1 = (QuizVO) map.get("quizVO");
		List<QuizAnswerVO> quizAnswerList = (List<QuizAnswerVO>) map.get("quizAnswerList");
		
		logger.debug("quizVO_1 : {}", quizVO_1);
		logger.debug("quizAnswerList : {}", quizAnswerList);
		
		String answer = "";
		for(int i=0; i<quizAnswerList.size(); i++) {
			if(		quizAnswerList.get(i).getQuiz_answer().equals("where")	||
					quizAnswerList.get(i).getQuiz_answer().equals("WHERE"))
				quizAnswerList.get(i).setQuiz_answer(quizAnswerList.get(i).getQuiz_answer()+"\t");
			
			if(		quizAnswerList.get(i).getQuiz_answer().equals("from") 	||
					quizAnswerList.get(i).getQuiz_answer().equals("FROM"))
				quizAnswerList.get(i).setQuiz_answer(quizAnswerList.get(i).getQuiz_answer()+"\t");
			
			if(quizAnswerList.get(i).getQuiz_answer().equals("=")) {
				quizAnswerList.get(i).setQuiz_answer(" "+quizAnswerList.get(i).getQuiz_answer());
			}
			
			if(		!quizAnswerList.get(i).getQuiz_answer().equals("select\t") ||
					!quizAnswerList.get(i).getQuiz_answer().equals("SELECT\t") ||
					!quizAnswerList.get(i).getQuiz_answer().equals("\nfrom\t") ||
					!quizAnswerList.get(i).getQuiz_answer().equals("\nFROM\t") ||
					!quizAnswerList.get(i).getQuiz_answer().equals("where\t")  ||
					!quizAnswerList.get(i).getQuiz_answer().equals("WHERE\t")  ||
					!quizAnswerList.get(i).getQuiz_answer().equals("  =")  ) 
				quizAnswerList.get(i).setQuiz_answer(quizAnswerList.get(i).getQuiz_answer()+"\t");
				
			
			
			answer += quizAnswerList.get(i).getQuiz_answer();
			logger.debug("answer 값 : {}", answer);
		}
		
		QuizAndAnswerVO quizAndAnswerVO = new QuizAndAnswerVO();
		
		logger.debug("quizVO_1 : {}", quizVO_1);
		logger.debug("answer 합치기 : {}", answer);
		
		quizAndAnswerVO.setQuiz_answer(answer);
		quizAndAnswerVO.setQuiz_explain(quizAnswerList.get(0).getQuiz_explain());
		quizAndAnswerVO.setQuiz_id(quizVO_1.getQuiz_id());
		quizAndAnswerVO.setQuiz_question(quizVO_1.getQuiz_question());
		quizAndAnswerVO.setQuiz_right(quizVO_1.getQuiz_right());
		
		model.addAttribute("quizName", "주관식 퀴즈");
		model.addAttribute("quizAndAnswerVO", quizAndAnswerVO);
		model.addAttribute("quiz_right", quizVO.getQuiz_right());
		
		
		return "/admin/quizMG/quizEssay/quizEssayUpdate.tiles";
	}
	
	/**
	* Method : updateEssay
	* 작성자 : 손주형
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 관리자가 수정버튼 클릭 시 DB에 update되는 메서드
	*/
	@RequestMapping(path = "/updateEssay", method = RequestMethod.POST)
	public String updateEssay(Model model, QuizAndAnswerVO quizAndAnswerVO, HttpSession session) {
		
		logger.debug("주관식 수정 quizAndAnswerVO : {}", quizAndAnswerVO);
		
		int result = service.updateQuiz(quizAndAnswerVO);
		
		if(result>0)
			logger.debug("주관식 수정 완료");
		
		model.addAttribute("quiz_right", quizAndAnswerVO.getQuiz_right());
		return "/admin/quizMG/quizList.tiles";
	}
}
