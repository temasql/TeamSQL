package kr.or.ddit.quiz.quiz_example.model;

public class QuizExampleVO {
	private int example_id;         // 선택지아이디
	private int quiz_id_fk;         // 퀴즈아이디
	private int example_num;        // 선택지번호
	private String example_content;	// 선택지내용

	
	public int getExample_id() {
		return example_id;
	}
	public void setExample_id(int example_id) {
		this.example_id = example_id;
	}
	public int getQuiz_id_fk() {
		return quiz_id_fk;
	}
	public void setQuiz_id_fk(int quiz_id_fk) {
		this.quiz_id_fk = quiz_id_fk;
	}
	public int getExample_num() {
		return example_num;
	}
	public void setExample_num(int example_num) {
		this.example_num = example_num;
	}
	public String getExample_content() {
		return example_content;
	}
	public void setExample_content(String example_content) {
		this.example_content = example_content;
	}
	@Override
	public String toString() {
		return "QuizExampleVO [example_id=" + example_id + ", quiz_id_fk=" + quiz_id_fk + ", example_num=" + example_num
				+ ", example_content=" + example_content + "]";
	}
}
