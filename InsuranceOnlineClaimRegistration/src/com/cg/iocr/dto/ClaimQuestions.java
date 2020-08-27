package com.cg.iocr.dto;

public class ClaimQuestions {
	private String claimType;
	private int questionId;
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	
	public ClaimQuestions() {
		super();
	}
	
	public ClaimQuestions(String claimType, int questionId, String question, String answer1, String answer2,
			String answer3) {
		super();
		this.claimType = claimType;
		this.questionId = questionId;
		this.question = question;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
	}

	

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}


	@Override
	public String toString() {
		return "ClaimQuestions [claimType=" + claimType + ", questionId=" + questionId + ", question=" + question
				+ ", answer1=" + answer1 + ", answer2=" + answer2 + ", answer3=" + answer3 + "]";
	}

}
