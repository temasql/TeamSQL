package kr.or.ddit.quiz.quiz.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class QuizVO {
	private int quiz_id;            // 퀴즈아이디
	private String user_id_fk;      // 퀴즈작성자
	private String quiz_right;      // 퀴즈구분
	private String quiz_question;   // 퀴즈문제
	@DateTimeFormat(pattern = "yyyy-MM-dd a hh:mm:ss")
	private Date quiz_dt;			// 퀴즈생성일시
	
	public int getQuiz_id() {
		return quiz_id;
	}
	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
	}
	public String getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public String getQuiz_right() {
		return quiz_right;
	}
	public void setQuiz_right(String quiz_right) {
		this.quiz_right = quiz_right;
	}
	public String getQuiz_question() {
		return quiz_question;
	}
	public void setQuiz_question(String quiz_question) {
		this.quiz_question = quiz_question;
	}
	public Date getQuiz_dt() {
		return quiz_dt;
	}
	public void setQuiz_dt(Date quiz_dt) {
		this.quiz_dt = quiz_dt;
	}
	@Override
	public String toString() {
		return "QuizVO [quiz_id=" + quiz_id + ", user_id_fk=" + user_id_fk + ", quiz_right=" + quiz_right
				+ ", quiz_question=" + quiz_question + ", quiz_dt=" + quiz_dt + "]";
	}
	
	
}
