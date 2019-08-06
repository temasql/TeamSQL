package kr.or.ddit.quiz.quiz.model;

/*
 *  update 2개테이블을 동시에 한쿼리에서 실행되게끔 할려고 했는데 실패...
 */
public class QuizAndAnswerVO {
	private int quiz_id;
	private String quiz_right;
	private String quiz_question;
	private String quiz_answer;
	private String quiz_explain;
	public int getQuiz_id() {
		return quiz_id;
	}
	public void setQuiz_id(int quiz_id) {
		this.quiz_id = quiz_id;
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
	public String getQuiz_answer() {
		return quiz_answer;
	}
	public void setQuiz_answer(String quiz_answer) {
		this.quiz_answer = quiz_answer;
	}
	public String getQuiz_explain() {
		return quiz_explain;
	}
	public void setQuiz_explain(String quiz_explain) {
		this.quiz_explain = quiz_explain;
	}
	
	@Override
	public String toString() {
		return "QuizAndAnswerVO [quiz_id=" + quiz_id + ", quiz_right=" + quiz_right + ", quiz_question=" + quiz_question
				+ ", quiz_answer=" + quiz_answer + ", quiz_explain=" + quiz_explain + "]";
	}
	
	public QuizAndAnswerVO(int quiz_id, String quiz_right, String quiz_question, String quiz_answer,
			String quiz_explain) {
		super();
		this.quiz_id = quiz_id;
		this.quiz_right = quiz_right;
		this.quiz_question = quiz_question;
		this.quiz_answer = quiz_answer;
		this.quiz_explain = quiz_explain;
	}
	
	public QuizAndAnswerVO() {
	}
}
