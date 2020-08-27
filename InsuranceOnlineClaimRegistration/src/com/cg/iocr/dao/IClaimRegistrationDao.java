package com.cg.iocr.dao;

import java.util.List;

import com.cg.iocr.dto.Accounts;
import com.cg.iocr.dto.Claim;
import com.cg.iocr.dto.ClaimDetails;
import com.cg.iocr.dto.ClaimQuestions;
import com.cg.iocr.dto.Policy;
import com.cg.iocr.dto.UserRole;
import com.cg.iocr.exception.ClaimRegException;

public interface IClaimRegistrationDao {

	int validateUser(String userName) throws ClaimRegException;

	int validatePasswordInDatabase(String userName, String password) throws ClaimRegException;

	String getRoleCode(String userName) throws ClaimRegException;

	int insertNewProfile(UserRole userRole) throws ClaimRegException;

	List<Policy> getAllPolicyBasedOnAgent(String agentUserName) throws ClaimRegException;

	List<Policy> getPolicyList(String username) throws ClaimRegException;

	long insertNewClaim(Claim claim) throws ClaimRegException;

	int insertClaimDetails(ClaimDetails claimDetails) throws ClaimRegException;

	List<ClaimQuestions> getQuestionsBasedOnClaimType(String claimType) throws ClaimRegException;

	Claim getClaim(long claimNumber) throws ClaimRegException;

	List<Claim> getAllClaimsOnPolicyNumber(long policyNumber)throws ClaimRegException;

	List<Policy> getAllPolicyList() throws ClaimRegException;

	List<ClaimDetails> getClaimDetails(long claimNumber)throws ClaimRegException;
	
	String getName(String userName) throws ClaimRegException;
	
	List<UserRole> getAllUser(String adminRoleCode) throws ClaimRegException;
	
	int deleteAUser(String userNameToDelete) throws ClaimRegException;

	List<Accounts> getAllAccounts(String adminRoleCode) throws ClaimRegException;
	
	int deleteAAccount(long accountNumber) throws ClaimRegException;

	int deleteAPolicy(long policyNumber) throws ClaimRegException;

	int deleteAClaim(long claimNumber) throws ClaimRegException;
	
	
	
	
}
