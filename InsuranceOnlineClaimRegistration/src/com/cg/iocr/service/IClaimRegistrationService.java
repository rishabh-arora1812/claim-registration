package com.cg.iocr.service;

import java.util.List;

import com.cg.iocr.dto.Accounts;
import com.cg.iocr.dto.Claim;
import com.cg.iocr.dto.ClaimDetails;
import com.cg.iocr.dto.ClaimQuestions;
import com.cg.iocr.dto.Policy;
import com.cg.iocr.dto.UserRole;
import com.cg.iocr.exception.ClaimRegException;

public interface IClaimRegistrationService {

	boolean validateUserNameExistInDatabase(String userName) throws ClaimRegException;

	boolean validatePasswordInDatabase(String userName, String password) throws ClaimRegException;

	String getRoleCode(String userName) throws ClaimRegException;
	
	String getName(String userName) throws ClaimRegException;

	int insertNewProfile(UserRole userRole) throws ClaimRegException;

	List<Policy> getAllPolicyBasedOnAgent(String agentUserName) throws ClaimRegException;

	List<Policy> getPolicyList(String userName) throws ClaimRegException;

	boolean validateNewUserName(String userName) throws ClaimRegException;

	boolean validatePassword(String password) throws ClaimRegException;

	boolean validateClaimReason(String claim) throws ClaimRegException;

	boolean validateAccidentLocation(String accident) throws ClaimRegException;

	boolean validateAccidentCity(String city) throws ClaimRegException;

	boolean validateAccidentState(String state) throws ClaimRegException;

	boolean validateAccidentZip(int zip) throws ClaimRegException;

	boolean validateYear(int year)throws ClaimRegException;
	
	boolean validateMoneyValue(long value)throws ClaimRegException;
	
	boolean validateDate(String date) throws ClaimRegException;
	
	boolean validateChassisNumber(String chassisNo) throws ClaimRegException;

	long insertNewClaim(Claim claim) throws ClaimRegException;

	int insertClaimDetails(ClaimDetails claimDetails) throws ClaimRegException;

	List<ClaimQuestions> getQuestionsBasedOnClaimType(String claimType) throws ClaimRegException;

	Claim getClaim(long claimNumber) throws ClaimRegException;


	List<Claim> getAllClaimsOnPolicyNumber(long policyNumber)throws ClaimRegException;

	List<Policy> getAllPolicyList() throws ClaimRegException;

	List<ClaimDetails> getClaimDetails(long claimNumber)throws ClaimRegException;
	
	List<UserRole> getAllUser(String adminRoleCode) throws ClaimRegException;

	int deleteAUser(String userNameToDelete) throws ClaimRegException;
	
	List<Accounts> getAllAccounts(String adminRoleCode) throws ClaimRegException;
	
	int deleteAAccount(long accountNumber) throws ClaimRegException;
	
	int deleteAPolicy(long policyNumber) throws ClaimRegException;

	int deleteAClaim(long claimNumber) throws ClaimRegException;

	

	

	

	

	
	
}
