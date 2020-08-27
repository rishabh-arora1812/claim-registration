package com.cg.iocr.dto;

public class ClaimDetails {
    private long claimNumber;
    private int questionId;
    private String answer;
    private String question;
    
	public ClaimDetails() {
		super();
	}

	public ClaimDetails(int questionId, String answer) {
		super();
		this.questionId = questionId;
		this.answer = answer;
	}

	public ClaimDetails(String answer, String question) {
		super();
		this.answer = answer;
		this.question = question;
	}

	public ClaimDetails(long claimNumber, int questionId, String answer) {
		super();
		this.claimNumber = claimNumber;
		this.questionId = questionId;
		this.answer = answer;
	}

	public ClaimDetails(long claimNumber, int questionId, String answer, String question) {
		super();
		this.claimNumber = claimNumber;
		this.questionId = questionId;
		this.answer = answer;
		this.question = question;
	}

	public long getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(long claimNumber) {
		this.claimNumber = claimNumber;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ClaimDetails [claimNumber=").append(claimNumber).append(", questionId=").append(questionId)
				.append(", answer=").append(answer).append(", question=").append(question).append("]");
		return builder.toString();
	}
   
    
    
    
}
