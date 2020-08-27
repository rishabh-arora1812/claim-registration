package com.cg.iocr.dto;

public class Claim {
	private long claimNumber;
	private String claimReason;
	private String accidentLocation;
	private String accidentCity;
	private String accidentState;
	private int accidentZipCode;
	private String claimType;
	private long policyNumber;
	
	public Claim() {
		super();
	}
	

	public Claim(long claimNumber, String claimType, long policyNumber) {
		super();
		this.claimNumber = claimNumber;
		this.claimType = claimType;
		this.policyNumber = policyNumber;
	}




	public Claim(long claimNumber, String claimReason, String accidentLocation, String accidentCity,
			String accidentState, int accidentZipCode, String claimType, long policyNumber) {
		super();
		this.claimNumber = claimNumber;
		this.claimReason = claimReason;
		this.accidentLocation = accidentLocation;
		this.accidentCity = accidentCity;
		this.accidentState = accidentState;
		this.accidentZipCode = accidentZipCode;
		this.claimType = claimType;
		this.policyNumber = policyNumber;
	}

	public Claim(String claimReason, String accidentLocation, String accidentCity, String accidentState,
			int accidentZipCode, String claimType, long policyNumber) {
		super();
		this.claimReason = claimReason;
		this.accidentLocation = accidentLocation;
		this.accidentCity = accidentCity;
		this.accidentState = accidentState;
		this.accidentZipCode = accidentZipCode;
		this.claimType = claimType;
		this.policyNumber = policyNumber;
	}

	public long getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(long claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getClaimReason() {
		return claimReason;
	}

	public void setClaimReason(String claimReason) {
		this.claimReason = claimReason;
	}

	public String getAccidentLocation() {
		return accidentLocation;
	}

	public void setAccidentLocation(String accidentLocation) {
		this.accidentLocation = accidentLocation;
	}

	public String getAccidentCity() {
		return accidentCity;
	}

	public void setAccidentCity(String accidentCity) {
		this.accidentCity = accidentCity;
	}

	public String getAccidentState() {
		return accidentState;
	}

	public void setAccidentState(String accidentState) {
		this.accidentState = accidentState;
	}

	public int getAccidentZipCode() {
		return accidentZipCode;
	}

	public void setAccidentZipCode(int accidentZipCode) {
		this.accidentZipCode = accidentZipCode;
	}

	public String getClaimType() {
		return claimType;
	}

	public void setClaimType(String claimType) {
		this.claimType = claimType;
	}

	public long getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(long policyNumber) {
		this.policyNumber = policyNumber;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Claim [claimNumber=").append(claimNumber).append(", claimReason=").append(claimReason)
				.append(", accidentLocation=").append(accidentLocation).append(", accidentCity=").append(accidentCity)
				.append(", accidentState=").append(accidentState).append(", accidentZipCode=").append(accidentZipCode)
				.append(", claimType=").append(claimType).append(", policyNumber=").append(policyNumber).append("]");
		return builder.toString();
	}
	
	
	
	
	



}
