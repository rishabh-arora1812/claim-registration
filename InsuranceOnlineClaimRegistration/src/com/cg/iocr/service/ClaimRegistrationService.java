package com.cg.iocr.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.cg.iocr.dao.ClaimRegistrationDao;
import com.cg.iocr.dao.IClaimRegistrationDao;
import com.cg.iocr.dto.Accounts;
import com.cg.iocr.dto.Claim;
import com.cg.iocr.dto.ClaimDetails;
import com.cg.iocr.dto.ClaimQuestions;
import com.cg.iocr.dto.Policy;
import com.cg.iocr.dto.UserRole;
import com.cg.iocr.exception.ClaimRegException;

public class ClaimRegistrationService implements IClaimRegistrationService {
	static Logger logger = Logger.getLogger(ClaimRegistrationService.class);

	IClaimRegistrationDao claimDao = new ClaimRegistrationDao();

	
	/**
	 * METHOD NAME: validateUserNameExistInDatabase 
	 * RETURN TYPE: boolean 
	 * ARGUMENT:String,user name 
	 * DESCRIPTION: It takes user name,checks if  user name exist in data base with same user name 
	 * AUTHOR:CAPGEMINI 
	 * DATE:25-09-2019
	 */

	@Override
	public boolean validateUserNameExistInDatabase(String userName) throws ClaimRegException {
		boolean userFlag = false;
		String userNameRegEx = "[a-zA-Z0-9]{1,20}";

		if (Pattern.matches(userNameRegEx, userName)) {
			int existingUser = claimDao.validateUser(userName);
			if (existingUser == 0) {
				throw new ClaimRegException("Username does not exist");
			} else
				userFlag = true;
		} else {
			throw new ClaimRegException("username should be alphanumeric and not more than 20 characters");
		}

		return userFlag;
	}
	
	/**
	 * METHOD NAME: validateNewUserName
	 * RETURN TYPE: boolean 
	 * ARGUMENT:String,user name 
	 * DESCRIPTION: It takes user name,checks if  user name already exist in data base with same user name 
	 * AUTHOR:CAPGEMINI 
	 * DATE:25-09-2019
	 */

	@Override
	public boolean validateNewUserName(String userName) throws ClaimRegException {
		boolean userFlag = false;
		String userNameRegEx = "[a-zA-Z0-9]{1,20}";

		if (Pattern.matches(userNameRegEx, userName)) {
			int existingUser = claimDao.validateUser(userName);
			if (existingUser == 1) {
				throw new ClaimRegException("Username already exist in data base");
			} else
				userFlag = true;
		} else {
			throw new ClaimRegException("username should be alphanumeric and not more than 20 characters");
		}

		return userFlag;
	}
	
	/**
	 * METHOD NAME: validatePasswordInDatabase
	 * RETURN TYPE: boolean 
	 * ARGUMENT:String,String (user name,password)
	 * DESCRIPTION: It takes user name and password and checks password if it is in data base or not.
	 * AUTHOR:CAPGEMINI 
	 * DATE:25-09-2019
	 */
	@Override
	public boolean validatePasswordInDatabase(String userName, String password) throws ClaimRegException {
		boolean passFlag = false;
		String passwordRegEx = "[a-zA-Z0-9]{1,12}";

		if (Pattern.matches(passwordRegEx, password)) {
			int existingPassword = claimDao.validatePasswordInDatabase(userName, password);
			if (existingPassword == 0) {
				throw new ClaimRegException("Password is incorrect. Please enter again");
			} else
				passFlag = true;
		} else {
			throw new ClaimRegException("password should be alphanumeric and not more than 12 characters");
		}

		return passFlag;
	}
	
	/**
	 * METHOD NAME: getRoleCode RETURN TYPE: 
	 * String, returns role code
	 * ARGUMENT:String,user name 
	 * DESCRIPTION: It takes user name and returns role code of that user. 
	 * AUTHOR:CAPGEMINI
	 * DATE:25-09-2019
	 */
	@Override
	public String getRoleCode(String userName) throws ClaimRegException {

		return claimDao.getRoleCode(userName);
	}

	
	/**
	 * METHOD NAME: insertNewProfile 
	 * RETURN TYPE: Integer, returns 0 or 1
	 * ARGUMENT:Object of UserRole 
	 * DESCRIPTION: It takes new UserRole object and insert same into database 
	 * AUTHOR:CAPGEMINI 
	 * DATE:25-09-2019
	 */
	@Override
	public int insertNewProfile(UserRole userRole) throws ClaimRegException {
		return claimDao.insertNewProfile(userRole);
	}

	
	/**
	 * METHOD NAME: getAllPolicyBasedOnAgent 
	 * RETURN TYPE: List of Policy object
	 * ARGUMENT:String, agent user name 
	 * DESCRIPTION: It takes agent user name,returns all policy associated with that agent. 
	 * AUTHOR:CAPGEMINI
	 * DATE:25-09-2019
	 */
	@Override
	public List<Policy> getAllPolicyBasedOnAgent(String agentUserName) throws ClaimRegException {

		return claimDao.getAllPolicyBasedOnAgent(agentUserName);
	}

	/**
	 * METHOD NAME: getPolicyList 
	 * RETURN TYPE: List of Policy object
	 * ARGUMENT:String, Insured user name 
	 * DESCRIPTION: It takes agent user name,returns all policy associated with that insured person. 
	 * AUTHOR:CAPGEMINI
	 * DATE:26-09-2019
	 */
	
	@Override
	public List<Policy> getPolicyList(String userName) throws ClaimRegException {
		List<Policy> list = new ArrayList<>();

		list = claimDao.getPolicyList(userName);
		return list;
	}
	
	/**
	 * METHOD NAME:validatePassword 
	 * RETURN TYPE: boolean
	 * ARGUMENT:String (password)
	 * DESCRIPTION: It takes password, and validate if it is alphanumeric or not.
	 * AUTHOR:CAPGEMINI
	 * DATE:26-09-2019
	 */
	
	@Override
	public boolean validatePassword(String password) throws ClaimRegException {
		boolean passFlag = false;
		String passwordRegEx = "[a-zA-Z0-9]{1,12}";

		if (Pattern.matches(passwordRegEx, password)) {
			passFlag = true;
		} else {
			throw new ClaimRegException("password should be alphanumeric and not more than 12 characters");
		}

		return passFlag;
	}

	/**
	 * METHOD NAME:validateClaimReason 
	 * RETURN TYPE: boolean
	 * ARGUMENT:String (claim)
	 * DESCRIPTION: It takes claim, and validate  if it is alphanumeric or not.
	 * AUTHOR:CAPGEMINI
	 * DATE:26-09-2019
	 */

	@Override
	public boolean validateClaimReason(String claim) throws ClaimRegException {
		String RegEx = "[a-zA-Z0-9\\s]{5,30}";
		boolean match = Pattern.matches(RegEx, claim);

		if (match == false) {
			throw new ClaimRegException(
					"Only AlphaNumeric characters are allowed with length in the range of 5-30 characters.");
		}
		return match;
	}

	/**
	 * METHOD NAME:validateAccidentLocation 
	 * RETURN TYPE: boolean
	 * ARGUMENT:String (accident location)
	 * DESCRIPTION: It takes claim, and validate  if it is alphanumeric or not.
	 * AUTHOR:CAPGEMINI
	 * DATE:26-09-2019
	 */
	@Override
	public boolean validateAccidentLocation(String accidentLocation) throws ClaimRegException {
		String RegEx = "[a-zA-Z0-9\\s]{4,40}";
		boolean match = Pattern.matches(RegEx, accidentLocation);
		if (match == false) {
			throw new ClaimRegException(
					"Only AlphaNumeric characters are allowed with length in the range of 4-40 characters.");
		}
		return match;
	}
	

	/**
	 * METHOD NAME:validateAccidentCity
	 * RETURN TYPE: boolean
	 * ARGUMENT:String (accident city)
	 * DESCRIPTION: It takes accident city, and validate  if it is alphanumeric or not.
	 * AUTHOR:CAPGEMINI
	 * DATE:26-09-2019
	 */
	@Override
	public boolean validateAccidentCity(String city) throws ClaimRegException {
		String RegEx = "[a-zA-Z0-9\\s]{4,15}";
		boolean match = Pattern.matches(RegEx, city);
		if (match == false) {
			throw new ClaimRegException(
					"Only AlphaNumeric characters are allowed with length in the range of 4-40 characters. ");
		}
		return match;
	}

	/**
	 * METHOD NAME:validateAccidentState
	 * RETURN TYPE: boolean
	 * ARGUMENT:String (accident state)
	 * DESCRIPTION: It takes accident state, and validate  if it is alphanumeric or not.
	 * AUTHOR:CAPGEMINI
	 * DATE:26-09-2019
	 */
	@Override
	public boolean validateAccidentState(String state) throws ClaimRegException {
		String RegEx = "[a-zA-Z0-9\\s]{4,15}";
		boolean match = Pattern.matches(RegEx, state);
		if (match == false) {
			throw new ClaimRegException(
					"Only AlphaNumeric characters are allowed with length in the range of 4-15 characters. ");
		}
		return match;
	}
	
	/**
	 * METHOD NAME:validateAccidentZip
	 * RETURN TYPE: boolean
	 * ARGUMENT:Integer (zip code)
	 * DESCRIPTION: It takes zip code, and check if it is 6 digit number  is not.
	 * AUTHOR:CAPGEMINI
	 * DATE:26-09-2019
	 */
	@Override
	public boolean validateAccidentZip(int zip) throws ClaimRegException {
		String RegEx = "[1-9]{1}[0-9]{5}";
		String zipCode = Integer.toString(zip);
		boolean match = Pattern.matches(RegEx, zipCode);
		if (match == false) {
			throw new ClaimRegException("Only Numeric characters are allowed with length of 6 characters. ");
		}
		return match;
	}
	
	/**
	 * METHOD NAME:validateYear
	 * RETURN TYPE: boolean
	 * ARGUMENT:Integer (year)
	 * DESCRIPTION: It takes year, check if valid past year or not.
	 * AUTHOR:CAPGEMINI
	 * DATE:26-09-2019
	 */

	@Override
	public boolean validateYear(int year) throws ClaimRegException {
		LocalDate date = LocalDate.now();

		String yearString = Integer.toString(year);
		String yearRegEx = "[1|2]{1}[8|9|0]{1}[0-9]{2}";
		boolean match = Pattern.matches(yearRegEx, yearString);
		if (match == false) {
			throw new ClaimRegException("Kindly enter valid year");
		}
		if (year > date.getYear()) {
			throw new ClaimRegException("Kindly enter valid year");
		} else {
			match = true;
		}
		return match;
	}
	
	/**
	 * METHOD NAME:validateMoneyValue
	 * RETURN TYPE: boolean
	 * ARGUMENT:Long(value)
	 * DESCRIPTION: It takes money value, check if it is greater than zero.
	 * AUTHOR:CAPGEMINI
	 * DATE:26-09-2019
	 */

	@Override
	public boolean validateMoneyValue(long value) throws ClaimRegException {

		boolean valueFlag = false;
		if (value <= 0) {
			throw new ClaimRegException("Kindly enter valid value");
		} else {
			valueFlag = true;
		}
		return valueFlag;
	}

	
	/**
	 * METHOD NAME:validateDate
	 * RETURN TYPE: boolean
	 * ARGUMENT:String(date)
	 * DESCRIPTION: It takes date, check if it is past date or not and prescribed format or not.
	 * AUTHOR:CAPGEMINI
	 * DATE:26-09-2019
	 */
	@Override
	public boolean validateDate(String date) throws ClaimRegException {
		boolean dateFlag = false;
		LocalDate localDate = null;
		LocalDate todayDate = LocalDate.now();
		try {
			localDate = LocalDate.parse(date);
		} catch (DateTimeParseException e) {
			throw new ClaimRegException("Kinldy enter date in prescribed format only");
		}

		if (localDate.isAfter(todayDate)) {
			throw new ClaimRegException("Enter valid past date");
		} else {
			dateFlag = true;
		}

		return dateFlag;
	}
	
	/**
	 * METHOD NAME:validateChassisNumber
	 * RETURN TYPE: boolean
	 * ARGUMENT:String(chassis Number)
	 * DESCRIPTION: It takes chassis number, check if it is minimum 8 letter and alphanumeric or not.
	 * AUTHOR:CAPGEMINI
	 * DATE:26-09-2019
	 */
	@Override
	public boolean validateChassisNumber(String chassisNo) throws ClaimRegException {
		String RegEx = "[a-zA-Z0-9]{8,15}";
		boolean match = Pattern.matches(RegEx, chassisNo);
		if (match == false) {
			throw new ClaimRegException(
					"Only AlphaNumeric characters are allowed with length in the range of 8-15 characters.");
		}
		return match;

	}

	
	/**
	 * METHOD NAME: insertNewClaim 
	 * RETURN TYPE: long 
	 * ARGUMENT:Object of Claim
	 * DESCRIPTION: It takes Claim object, insert into data base and returns new claim number. 
	 * AUTHOR:CAPGEMINI DATE:26-09-2019
	 */
	@Override
	public long insertNewClaim(Claim claim) throws ClaimRegException {

		return claimDao.insertNewClaim(claim);
	}

	
	/**
	 * METHOD NAME: getQuestionsBasedOnClaimType 
	 * RETURN TYPE: Object of ClaimQuestions ARGUMENT:String (claim types) DESCRIPTION: It takes claim
	 * type, returns all questions associated with that claim type. AUTHOR:CAPGEMINI
	 * DATE:26-09-2019
	 */
	@Override
	public List<ClaimQuestions> getQuestionsBasedOnClaimType(String claimType) throws ClaimRegException {
		return claimDao.getQuestionsBasedOnClaimType(claimType);
	}
	
	
	/**
	 * METHOD NAME: insertClaimDetails RETURN TYPE: Integer (0 or 1) ARGUMENT:Object
	 * of ClaimDetails DESCRIPTION: It takes object of ClaimDetails which contains
	 * details claim question and answer. Object is inserted into database.
	 * AUTHOR:CAPGEMINI DATE:26-09-2019
	 */
	@Override
	public int insertClaimDetails(ClaimDetails claimDetails) throws ClaimRegException {
		
		return claimDao.insertClaimDetails(claimDetails);
	}

	/**
	 * METHOD NAME: getClaim RETURN TYPE: Claim Object ARGUMENT:Long (claim number)
	 * DESCRIPTION: It takes object claim number, and returns claim object
	 * associated with that claim number. AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public Claim getClaim(long claimNumber) throws ClaimRegException {

		return claimDao.getClaim(claimNumber);
	}

	
	/**
	 * METHOD NAME: getAllClaimsOnPolicyNumber RETURN TYPE: List of Claim
	 * ARGUMENT:Long (policy number) DESCRIPTION: It takes object policy number, and
	 * returns all claims object associated with that policy number.
	 * AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public List<Claim> getAllClaimsOnPolicyNumber(long policyNumber) throws ClaimRegException {

		return claimDao.getAllClaimsOnPolicyNumber(policyNumber);
	}

	
	/**
	 * METHOD NAME: getAllPolicyList RETURN TYPE: List of Policy ARGUMENT:Void
	 * DESCRIPTION: Returns all policy stored in data base. AUTHOR:CAPGEMINI
	 * DATE:27-09-2019
	 */
	@Override
	public List<Policy> getAllPolicyList() throws ClaimRegException {

		return claimDao.getAllPolicyList();
	}

	
	/**
	 * METHOD NAME:getClaimDetails RETURN TYPE: List of ClaimDetails ARGUMENT: Long
	 * (claim number) DESCRIPTION: It takes claim number, returns claim details
	 * associated with that claim number. AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public List<ClaimDetails> getClaimDetails(long claimNumber) throws ClaimRegException {

		return claimDao.getClaimDetails(claimNumber);
	}

	/**
	 * METHOD NAME:getName RETURN TYPE: String (insured name) ARGUMENT: String (user
	 * name of insured person) DESCRIPTION: It takes insured user name, returns
	 * insured person name. AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public String getName(String userName) throws ClaimRegException {
		
		return claimDao.getName(userName);
	}

	/**
	 * METHOD NAME:getAllUser RETURN TYPE: List of UserRole Object ARGUMENT: String
	 * (role code) DESCRIPTION: It returns all user data(user name, role code).
	 * AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public List<UserRole> getAllUser(String adminRoleCode) throws ClaimRegException {
		
		return claimDao.getAllUser(adminRoleCode);
	}

	/**
	 * METHOD NAME:deleteAUser RETURN TYPE: integer 0 or 1 ARGUMENT: String (user
	 * name to delete) DESCRIPTION: It deletes user which is passed to it.
	 * AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public int deleteAUser(String userNameToDelete) throws ClaimRegException {
		
		return claimDao.deleteAUser(userNameToDelete);
	}

	
	/**
	 * METHOD NAME:getAllAccounts RETURN TYPE: List of Accounts Object ARGUMENT:
	 * String (role code) DESCRIPTION: It returns all accounts details.
	 * AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public List<Accounts> getAllAccounts(String adminRoleCode) throws ClaimRegException {
		
		return claimDao.getAllAccounts(adminRoleCode);
	}

	
	/**
	 * METHOD NAME:deleteAAccount RETURN TYPE: Integer 0 or 1 ARGUMENT: long
	 * (account number) DESCRIPTION: It deletes account having account number passed
	 * to it. AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public int deleteAAccount(long accountNumber) throws ClaimRegException {
		
		return claimDao.deleteAAccount(accountNumber);
	}

	
	/**
	 * METHOD NAME:deleteAPolicy RETURN TYPE: Integer 0 or 1 ARGUMENT: long (policy
	 * number) DESCRIPTION: It deletes policy having policy number passed to it.
	 * AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public int deleteAPolicy(long policyNumber) throws ClaimRegException {
		
		return claimDao.deleteAPolicy(policyNumber);
	}
	
	/**
	 * METHOD NAME:deleteAClaim RETURN TYPE: Integer 0 or 1 ARGUMENT: long (claim
	 * number) DESCRIPTION: It deletes claim having claim number passed to it.
	 * AUTHOR:CAPGEMINI DATE:27-09-2019
	 */
	@Override
	public int deleteAClaim(long claimNumber) throws ClaimRegException {
		
		return claimDao.deleteAClaim(claimNumber);
	}

}
