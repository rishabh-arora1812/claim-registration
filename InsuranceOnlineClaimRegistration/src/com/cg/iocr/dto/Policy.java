package com.cg.iocr.dto;

public class Policy {
	private long policyNumber;
	private double policyPremium;
	private long accountNumber;
	private int noOfClaims;

	public Policy() {
		// TODO Auto-generated constructor stub
	}

	public Policy(long policyNumber, double policyPremium, long accountNumber) {
		super();
		this.policyNumber = policyNumber;
		this.policyPremium = policyPremium;
		this.accountNumber = accountNumber;
	}

	public Policy(long policyNumber, double policyPremium, long accountNumber, int noOfClaims) {
		super();
		this.policyNumber = policyNumber;
		this.policyPremium = policyPremium;
		this.accountNumber = accountNumber;
		this.noOfClaims = noOfClaims;
	}

	public int getNoOfClaims() {
		return noOfClaims;
	}

	public void setNoOfClaims(int noOfClaims) {
		this.noOfClaims = noOfClaims;
	}

	public long getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(long policyNumber) {
		this.policyNumber = policyNumber;
	}

	public double getPolicyPremium() {
		return policyPremium;
	}

	public void setPolicyPremium(double policyPremium) {
		this.policyPremium = policyPremium;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Policy [policyNumber=").append(policyNumber).append(", policyPremium=").append(policyPremium)
				.append(", accountNumber=").append(accountNumber).append("]");
		return builder.toString();
	}

}
