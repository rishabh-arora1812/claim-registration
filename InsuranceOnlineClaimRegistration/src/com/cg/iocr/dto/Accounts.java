package com.cg.iocr.dto;

public class Accounts {

	private long accountNumber;
	private String insuredName;
	private String insuredStreet;
	private String insuredCity;
	private String insuredState;
	private String insuredZipCode;
	private String bussinessSegment;
	private String userName;
	private String agentUserName;

	public Accounts() {
		// TODO Auto-generated constructor stub
	}

	public Accounts(long accountNumber, String insuredName, String insuredStreet, String insuredCity,
			String insuredState, String insuredZipCode, String bussinessSegment, String userName,
			String agentUserName) {
		super();
		this.accountNumber = accountNumber;
		this.insuredName = insuredName;
		this.insuredStreet = insuredStreet;
		this.insuredCity = insuredCity;
		this.insuredState = insuredState;
		this.insuredZipCode = insuredZipCode;
		this.bussinessSegment = bussinessSegment;
		this.userName = userName;
		this.agentUserName = agentUserName;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public String getInsuredStreet() {
		return insuredStreet;
	}

	public void setInsuredStreet(String insuredStreet) {
		this.insuredStreet = insuredStreet;
	}

	public String getInsuredCity() {
		return insuredCity;
	}

	public void setInsuredCity(String insuredCity) {
		this.insuredCity = insuredCity;
	}

	public String getInsuredState() {
		return insuredState;
	}

	public void setInsuredState(String insuredState) {
		this.insuredState = insuredState;
	}

	public String getInsuredZipCode() {
		return insuredZipCode;
	}

	public void setInsuredZipCode(String insuredZipCode) {
		this.insuredZipCode = insuredZipCode;
	}

	public String getBussinessSegment() {
		return bussinessSegment;
	}

	public void setBussinessSegment(String bussinessSegment) {
		this.bussinessSegment = bussinessSegment;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAgentUserName() {
		return agentUserName;
	}

	public void setAgentUserName(String agentUserName) {
		this.agentUserName = agentUserName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Accounts [accountNumber=").append(accountNumber).append(", insuredName=").append(insuredName)
				.append(", insuredStreet=").append(insuredStreet).append(", insuredCity=").append(insuredCity)
				.append(", insuredState=").append(insuredState).append(", insuredZipCode=").append(insuredZipCode)
				.append(", bussinessSegment=").append(bussinessSegment).append(", userName=").append(userName)
				.append(", agentUserName=").append(agentUserName).append("]");
		return builder.toString();
	}

}
