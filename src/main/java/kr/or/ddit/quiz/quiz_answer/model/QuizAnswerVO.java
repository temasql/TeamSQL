package kr.or.ddit.quiz.quiz_answer.model;

public class QuizAnswerVO {
	private int answer_id;          // 정답아이디
	private int quiz_id_fk;         // 퀴즈아이디
	private String quiz_answer;     // 퀴즈정답
	private String quiz_explain;	// 퀴즈해설
	
	public int getAnswer_id() {
		return answer_id;
	}
	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}
	public int getQuiz_id_fk() {
		return quiz_id_fk;
	}
	public void setQuiz_id_fk(int quiz_id_fk) {
		this.quiz_id_fk = quiz_id_fk;
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
		return "QuizAnswerVO [answer_id=" + answer_id + ", quiz_id_fk=" + quiz_id_fk + ", quiz_answer=" + quiz_answer
				+ ", quiz_explain=" + quiz_explain + "]";
	}
	
	
}
